package com.isa.isa.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.AccountDeleteRequest.DTO.AccountDeleteRequestFromFrontDTO;
import com.isa.isa.model.AccountDeleteRequest.service.AccountDeleteRequestService;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.ClientAdventureFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.DTO.ClientBoatFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientBoatReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageReservationDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsAdventureDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsBoatDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsCottageDTO;
import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.DTO.RevisionClientDTO;
import com.isa.isa.model.termins.model.BoatFastReservation;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.service.BoatFastResHistoryService;
import com.isa.isa.model.termins.service.BoatFastReservationService;
import com.isa.isa.model.termins.service.BoatReservationService;
import com.isa.isa.model.termins.service.CottageFastResHistoryService;
import com.isa.isa.model.termins.service.CottageFastReservationService;
import com.isa.isa.model.termins.service.CottageReservationService;
import com.isa.isa.model.termins.service.InsFastResHistoryService;
import com.isa.isa.model.termins.service.InstructorFastReservationService;
import com.isa.isa.model.termins.service.InstructorReservationService;
import com.isa.isa.security.service.EmailService;
import com.isa.isa.service.AdventureService;
import com.isa.isa.service.BoatOwnerService;
import com.isa.isa.service.BoatService;
import com.isa.isa.service.ClientService;
import com.isa.isa.service.CottageOwnerService;
import com.isa.isa.service.CottageService;
import com.isa.isa.service.InstructorService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private InstructorReservationService instructorReservetionService;
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private InsFastResHistoryService insFastResHistoryService;
	
	@Autowired
	private InstructorFastReservationService instructorFastReservationService;
	
	@Autowired
	private AdventureService adventureService;
	 
	@Autowired
	private CottageService cottageService;
	 
	@Autowired
	private CottageReservationService cottageReservationService;
	
	@Autowired
	private CottageFastResHistoryService cottageFastResHistoryService;
	
	@Autowired
	private CottageFastReservationService cottageFastReservationService;
	
	@Autowired
	private BoatService boatService;
	
	@Autowired
	private BoatReservationService boatReservationService;
	
	@Autowired
	private BoatFastResHistoryService boatFastResHistoryService;
	
	@Autowired
	private BoatFastReservationService boatFastReservationService;
	
	@Autowired
	private AccountDeleteRequestService accountDeleteRequestService;
	
	@Autowired
	private CottageOwnerService cottageOwnerService;
	
	@Autowired
	private BoatOwnerService boatOwnerService;
	
	@GetMapping("/profileInfo")
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")	
	public ClientDto getInfo(Principal user) {
		return new ClientDto(this.clientService.findByEmail(user.getName()));
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PutMapping("/updateProfile")
	public ClientDto updateClient(@RequestBody ClientDto clientDto) {
		Client client= new Client(clientDto);
		return new ClientDto(this.clientService.update(client));
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PutMapping("/updatePassword")
	public Boolean updateClientPassword(@RequestBody PasswordDto passwordDto, Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		return this.clientService.updatePassword(client, passwordDto);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/reserveAdventure")
	public ResponseEntity<Boolean> reserveAdventure(@RequestBody ClientAdventureReservationDTO clientAdventureReservationDTO, Principal user) {
		if(!instructorService.isInstructorFree(new InstructorTermsDTO(clientAdventureReservationDTO.getAdventure().getInstructor().getId(), clientAdventureReservationDTO.getStartTime(), clientAdventureReservationDTO.getEndTime(), clientAdventureReservationDTO.getAdventure().getInstructor().getEmail()))) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		Client client= this.clientService.findByEmail(user.getName());
		if(instructorReservetionService.reserveAdventureByClient(clientAdventureReservationDTO, client) == null) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		try {
			emailService.sendReservationConfirmation(client, clientAdventureReservationDTO);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/getAdventures")
	public ResponseEntity<List<ClientMadeReservationsAdventureDTO>> getAdventures(Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		List<ClientMadeReservationsAdventureDTO> retVal = new ArrayList<ClientMadeReservationsAdventureDTO>();
		retVal.addAll(instructorReservetionService.getAdventureReservationByClient(client.getId()));
		retVal.addAll(insFastResHistoryService.getFastResevationByClient(client.getId()));
		return new ResponseEntity<>(retVal,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PutMapping("/cancelAdventureReservation")
	public ResponseEntity<Boolean> cancelAdventureReservation(@RequestBody ClientMadeReservationsAdventureDTO clientMadeReservationsAdventureDTO, Principal user) {
		if(!ThreeDayDifference(clientMadeReservationsAdventureDTO.getStartTime())) return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		if(clientMadeReservationsAdventureDTO.getIsFast()) {
			insFastResHistoryService.cancelReservation(clientMadeReservationsAdventureDTO.getReservationId());
		}
		else {
			instructorReservetionService.cancelReservation(clientMadeReservationsAdventureDTO.getReservationId());
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	private Boolean ThreeDayDifference(LocalDateTime startDate) {
		Long period = ChronoUnit.DAYS.between(LocalDateTime.now(), startDate);
		if(period<3) return false;
		return true;
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/reserveAction")
	public ResponseEntity<Boolean> reserveAction(@RequestBody ClientAdventureFastReservationDTO clientAdventureFastReservationDTO, Principal user) {
		
		Client client= this.clientService.findByEmail(user.getName());
		Adventure adventure = adventureService.getAdventureWithInstructor(clientAdventureFastReservationDTO.getIdAdventure());
		InstructorFastReservation instructorFastReservation = instructorFastReservationService.getById(clientAdventureFastReservationDTO.getIdFastReservation());
		if(!insFastResHistoryService.makeReservation(client, instructorFastReservation)) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		try {
			emailService.sendAdventureActionReservationConfirmation(client,adventure, clientAdventureFastReservationDTO);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/reserveCottage")
	public ResponseEntity<Boolean> reserveCottage(@RequestBody ClientCottageReservationDTO clientCottageReservationDTO, Principal user) {
		if(!cottageService.isCottageFree(new CottageTermsDTO(clientCottageReservationDTO.getCottage().getId(),clientCottageReservationDTO.getStartTime(),clientCottageReservationDTO.getEndTime()))) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		Client client= this.clientService.findByEmail(user.getName());
		if(cottageReservationService.reserveCottageByClient(clientCottageReservationDTO, client) == null) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		try {
			emailService.sendCottageReservationConfirmation(client, clientCottageReservationDTO);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/getCottages")
	public ResponseEntity<List<ClientMadeReservationsCottageDTO>> getCottages(Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		List<ClientMadeReservationsCottageDTO> retVal = new ArrayList<ClientMadeReservationsCottageDTO>();
		retVal.addAll(cottageReservationService.getCottageReservationByClient(client.getId()));
		retVal.addAll(cottageFastResHistoryService.getFastResevationByClient(client.getId()));
		return new ResponseEntity<>(retVal,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PutMapping("/cancelCottageReservation")
	public ResponseEntity<Boolean> cancelCottageReservation(@RequestBody ClientMadeReservationsCottageDTO clientMadeReservationsCottageDTO, Principal user) {
		if(!ThreeDayDifference(clientMadeReservationsCottageDTO.getStartTime())) return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		if(clientMadeReservationsCottageDTO.getIsFast()) {
			cottageFastResHistoryService.cancelReservation(clientMadeReservationsCottageDTO.getReservationId());
		}
		else {
			cottageReservationService.cancelReservation(clientMadeReservationsCottageDTO.getReservationId());
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/reserveCottageAction")
	public ResponseEntity<Boolean> reserveCottageAction(@RequestBody ClientCottageFastReservationDTO clientCottageFastReservationDTO, Principal user) {
		
		Client client= this.clientService.findByEmail(user.getName());
		Cottage cottage = cottageService.getCottageWithOwner(clientCottageFastReservationDTO.getIdCottage());
		CottageFastReservation cottageFastReservation = cottageFastReservationService.getById(clientCottageFastReservationDTO.getIdFastReservation());
		if(!cottageFastResHistoryService.makeReservation(client, cottageFastReservation)) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		try {
			emailService.sendCottageActionReservationConfirmation(client,cottage, clientCottageFastReservationDTO);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/reserveBoat")
	public ResponseEntity<Boolean> reserveBoat(@RequestBody ClientBoatReservationDTO clientBoatReservationDTO, Principal user) {
		if(!boatService.isBoatFree(new BoatTermsDTO(clientBoatReservationDTO.getBoat().getId(),clientBoatReservationDTO.getStartTime(),clientBoatReservationDTO.getEndTime()))) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		Client client= this.clientService.findByEmail(user.getName());
		if(boatReservationService.reserveBoatByClient(clientBoatReservationDTO, client) == null) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		try {
			emailService.sendBoatReservationConfirmation(client, clientBoatReservationDTO);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/getBoats")
	public ResponseEntity<List<ClientMadeReservationsBoatDTO>> getBoats(Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		List<ClientMadeReservationsBoatDTO> retVal = new ArrayList<ClientMadeReservationsBoatDTO>();
		retVal.addAll(boatReservationService.getBoatReservationByClient(client.getId()));
		retVal.addAll(boatFastResHistoryService.getFastResevationByClient(client.getId()));
		return new ResponseEntity<>(retVal,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/reserveBoatAction")
	public ResponseEntity<Boolean> reserveBoatAction(@RequestBody ClientBoatFastReservationDTO clientBoatFastReservationDTO, Principal user) {
		
		Client client= this.clientService.findByEmail(user.getName());
		Boat boat = boatService.getBoatWithOwner(clientBoatFastReservationDTO.getIdBoat());
		BoatFastReservation boatFastReservation = boatFastReservationService.getById(clientBoatFastReservationDTO.getIdFastReservation());
		if(!boatFastResHistoryService.makeReservation(client, boatFastReservation)) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		try {
			emailService.sendBoatActionReservationConfirmation(client,boat, clientBoatFastReservationDTO);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PutMapping("/cancelBoatReservation")
	public ResponseEntity<Boolean> cancelBoatReservation(@RequestBody ClientMadeReservationsBoatDTO clientMadeReservationsBoatDTO, Principal user) {
		if(!ThreeDayDifference(clientMadeReservationsBoatDTO.getStartTime())) return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		if(clientMadeReservationsBoatDTO.getIsFast()) {
			boatFastResHistoryService.cancelReservation(clientMadeReservationsBoatDTO.getReservationId());
		}
		else {
			boatReservationService.cancelReservation(clientMadeReservationsBoatDTO.getReservationId());
		}
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PutMapping("/deleteRequest")
    public ResponseEntity<Boolean> deleteRequest(@RequestBody AccountDeleteRequestFromFrontDTO reasonDTO, Principal user) {
        return new ResponseEntity<Boolean>(accountDeleteRequestService.createAccDeleteRequest(reasonDTO, user, UserTypeISA.CLIENT), HttpStatus.OK);
    }
	
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/addRevision")
	public ResponseEntity<Boolean> addRevision(@RequestBody RevisionClientDTO revisionClientDTO, Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		revisionClientDTO.setUserId(client.getId());
		if(revisionClientDTO.getType().equals("cottage")) {
			Cottage cottage = cottageService.getCottageWithOwner(revisionClientDTO.getEntityId());
			revisionClientDTO.setOverseerId(cottage.getOwner().getId());
			cottageService.AddRevision(revisionClientDTO);
			cottageOwnerService.AddRevision(revisionClientDTO);
		} else if(revisionClientDTO.getType().equals("boat")) {
			Boat boat = boatService.getBoatWithOwner(revisionClientDTO.getEntityId());
			revisionClientDTO.setOverseerId(boat.getOwner().getId());
			boatService.AddRevision(revisionClientDTO);
			boatOwnerService.AddRevision(revisionClientDTO);
		} else {
			Adventure adventure = adventureService.getAdventureWithInstructor(revisionClientDTO.getEntityId());
			revisionClientDTO.setOverseerId(adventure.getInstructor().getId());
			adventureService.AddRevision(revisionClientDTO);
			instructorService.AddRevision(revisionClientDTO);
		}
		
		return new ResponseEntity<>(true,HttpStatus.OK);
	}

}
