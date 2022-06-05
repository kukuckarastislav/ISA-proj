package com.isa.isa.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.isa.isa.model.loyalty.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.ClientSubscriptionDTO;
import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.Penalty;
import com.isa.isa.model.AccountDeleteRequest.DTO.AccountDeleteRequestFromFrontDTO;
import com.isa.isa.model.AccountDeleteRequest.service.AccountDeleteRequestService;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.loyalty.LoyaltyService;
import com.isa.isa.model.loyalty.LoyaltySettings;
import com.isa.isa.model.loyalty.LoyaltyType;
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
import com.isa.isa.model.termins.DTO.ComplaintClientDTO;
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

	@Autowired
	private LoyaltyService loyaltyService;
	
	@Autowired
	private LoyaltyService loyaltyService;
	
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

		//TODO: loyalty
		if(!instructorService.isInstructorFree(new InstructorTermsDTO(clientAdventureReservationDTO.getAdventure().getInstructor().getId(), clientAdventureReservationDTO.getStartTime(), clientAdventureReservationDTO.getEndTime(), clientAdventureReservationDTO.getAdventure().getInstructor().getEmail()))) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		Client client= this.clientService.findByEmail(user.getName());
		if(client.getPenalties().size()>2) return new ResponseEntity<>(false,HttpStatus.OK);
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
		//TODO: loyalty
		Client client= this.clientService.findByEmail(user.getName());
		if(client.getPenalties().size()>2) return new ResponseEntity<>(false,HttpStatus.OK);
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
		//TODO: loyalty
		if(!cottageService.isCottageFree(new CottageTermsDTO(clientCottageReservationDTO.getCottage().getId(),clientCottageReservationDTO.getStartTime(),clientCottageReservationDTO.getEndTime()))) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		Client client= this.clientService.findByEmail(user.getName());
		if(client.getPenalties().size()>2) return new ResponseEntity<>(false,HttpStatus.OK);
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
		//TODO: loyalty
		Client client= this.clientService.findByEmail(user.getName());
		if(client.getPenalties().size()>2) return new ResponseEntity<>(false,HttpStatus.OK);
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
		//TODO: loyalty
		if(!boatService.isBoatFree(new BoatTermsDTO(clientBoatReservationDTO.getBoat().getId(),clientBoatReservationDTO.getStartTime(),clientBoatReservationDTO.getEndTime()))) {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
		Client client= this.clientService.findByEmail(user.getName());
		if(client.getPenalties().size()>2) return new ResponseEntity<>(false,HttpStatus.OK);
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
		if(client.getPenalties().size()>2) return new ResponseEntity<>(false,HttpStatus.OK);
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


	//TODO: rastereteti ovaj controller prebacivanjem poziva u vezi revizija u controller namenjen revizijama
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/addRevision")
	public ResponseEntity<Boolean> addRevision(@RequestBody RevisionClientDTO revisionClientDTO, Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		revisionClientDTO.setUserId(client.getId());
		if(revisionClientDTO.getType().equals("cottage")) {
			Cottage cottage = cottageService.getCottageWithOwner(revisionClientDTO.getEntityId());
			revisionClientDTO.setOverseerId(cottage.getOwner().getId());
			cottageService.addRevision(revisionClientDTO);
			cottageOwnerService.addRevision(revisionClientDTO);
			if(revisionClientDTO.getIsFast()) cottageFastResHistoryService.addRevision(revisionClientDTO.getReservationId());
			else cottageReservationService.addRevision(revisionClientDTO.getReservationId());
		} else if(revisionClientDTO.getType().equals("boat")) {
			Boat boat = boatService.getBoatWithOwner(revisionClientDTO.getEntityId());
			revisionClientDTO.setOverseerId(boat.getOwner().getId());
			boatService.addRevision(revisionClientDTO);
			boatOwnerService.addRevision(revisionClientDTO);
			if(revisionClientDTO.getIsFast()) boatFastResHistoryService.addRevision(revisionClientDTO.getReservationId());
			else boatReservationService.addRevision(revisionClientDTO.getReservationId());
		} else {
			Adventure adventure = adventureService.getAdventureWithInstructor(revisionClientDTO.getEntityId());
			revisionClientDTO.setOverseerId(adventure.getInstructor().getId());
			adventureService.addRevision(revisionClientDTO);
			instructorService.addRevision(revisionClientDTO);
			if(revisionClientDTO.getIsFast()) insFastResHistoryService.addRevision(revisionClientDTO.getEntityId());
			else instructorReservetionService.addRevision(revisionClientDTO.getEntityId());
		}
		
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/addComplaint")
	public ResponseEntity<Boolean> addComplaint(@RequestBody ComplaintClientDTO complaintClientDTO, Principal user) {
		complaintClientDTO.setUserEmail(user.getName());
		if(complaintClientDTO.getType().equals("cottage")) {
			Cottage cottage = cottageService.getCottageWithOwner(complaintClientDTO.getEntityId());
			complaintClientDTO.setOverseerId(cottage.getOwner().getId());
			cottageService.addComplaint(complaintClientDTO);
			cottageOwnerService.addComplaint(complaintClientDTO);
			if(complaintClientDTO.getIsFast()) cottageFastResHistoryService.addComplaint(complaintClientDTO.getReservationId());
			else cottageReservationService.addComplaint(complaintClientDTO.getReservationId());
		} else if(complaintClientDTO.getType().equals("boat")) {
			Boat boat = boatService.getBoatWithOwner(complaintClientDTO.getEntityId());
			complaintClientDTO.setOverseerId(boat.getOwner().getId());
			boatService.addComplaint(complaintClientDTO);
			boatOwnerService.addComplaint(complaintClientDTO);
			if(complaintClientDTO.getIsFast()) boatFastResHistoryService.addComplaint(complaintClientDTO.getReservationId());
			else boatReservationService.addComplaint(complaintClientDTO.getReservationId());
		} else {
			Adventure adventure = adventureService.getAdventureWithInstructor(complaintClientDTO.getEntityId());
			complaintClientDTO.setOverseerId(adventure.getInstructor().getId());
			adventureService.addComplaint(complaintClientDTO);
			instructorService.addComplaint(complaintClientDTO);
			if(complaintClientDTO.getIsFast()) insFastResHistoryService.addComplaint(complaintClientDTO.getEntityId());
			else instructorReservetionService.addComplaint(complaintClientDTO.getEntityId());
		}
		
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody ClientSubscriptionDTO clientSubscriptionDTO, Principal user) {
		if(clientSubscriptionDTO.getType().equals("cottage")) {
			Cottage cottage = cottageService.getCottageWithOwner(clientSubscriptionDTO.getId());
			clientService.addCottageSubscription(user.getName(), cottage);
		} else if(clientSubscriptionDTO.getType().equals("boat")) {
			Boat boat = boatService.getBoatWithOwner(clientSubscriptionDTO.getId());
			clientService.addBoatSubscription(user.getName(), boat);
		} else {
			Adventure adventure = adventureService.getAdventureWithInstructor(clientSubscriptionDTO.getId());
			clientService.addAdventureSubscription(user.getName(), adventure);
		}
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/getSubscriptionsCottage")
	public ResponseEntity<List<Cottage>> getSubscriptionsCottage(Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		List<Cottage> cottages = new ArrayList<Cottage>();
		for(Cottage cottage:  client.getCottageSubscriptions()) {
			cottages.add(cottageService.getCottageWithOwner(cottage.getId()));
		}
		return new ResponseEntity<List<Cottage>>(cottages, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/getSubscriptionsBoat")
	public ResponseEntity<List<Boat>> getSubscriptionsBoat(Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		List<Boat> boats = new ArrayList<Boat>();
		for(Boat boat:  client.getBoatSubscriptions()) {
			boats.add(boatService.getBoatWithOwner(boat.getId()));
		}
		return new ResponseEntity<List<Boat>>(boats, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/getSubscriptionsAdventure")
	public ResponseEntity<List<Adventure>> getSubscriptionsAdventure(Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		List<Adventure> adventures = new ArrayList<Adventure>();
		for(Adventure adventure:  client.getAdventureSubscriptions()) {
			adventures.add(adventureService.getAdventureWithInstructor(adventure.getId()));
		}
		return new ResponseEntity<List<Adventure>>(adventures, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@PostMapping("/isSubscribed")
	public ResponseEntity<Boolean> isSubscribed(@RequestBody ClientSubscriptionDTO clientSubscriptionDTO, Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		if(clientSubscriptionDTO.getType().equals("cottage")) {
			for (Cottage cottage : client.getCottageSubscriptions()) {
				if(cottage.getId()==clientSubscriptionDTO.getId()) return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		} else if(clientSubscriptionDTO.getType().equals("boat")) {
			for (Boat boat : client.getBoatSubscriptions()) {
				if(boat.getId()==clientSubscriptionDTO.getId()) return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		} else {
			for (Adventure adventure : client.getAdventureSubscriptions()) {
				if(adventure.getId()==clientSubscriptionDTO.getId()) return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@DeleteMapping("/unsubscribe")
	public ResponseEntity<?> unsubscribe(@RequestBody ClientSubscriptionDTO clientSubscriptionDTO, Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		if(clientSubscriptionDTO.getType().equals("cottage")) {
			Cottage cottage = cottageService.getCottageWithOwner(clientSubscriptionDTO.getId());
			clientService.removeCottageSubscription(user.getName(), cottage);
		} else if(clientSubscriptionDTO.getType().equals("boat")) {
			Boat boat = boatService.getBoatWithOwner(clientSubscriptionDTO.getId());
			clientService.removeBoatSubscription(user.getName(), boat);
		} else {
			Adventure adventure = adventureService.getAdventureWithInstructor(clientSubscriptionDTO.getId());
			clientService.removeAdventureSubscription(user.getName(), adventure);
		}
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/getPenalties")
	public ResponseEntity<Set<Penalty>> getPenalties(Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		return new ResponseEntity<Set<Penalty>>(client.getPenalties(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/getLoyaltyDiscount")
	public ResponseEntity<Double> getLoyaltyDiscount(Principal user) {
		Client client= this.clientService.findByEmail(user.getName());
		Double retVal=0.0;
		LoyaltySettings loyaltySettings= loyaltyService.getLoyaltySettings();
		if(client.getLoyalty().getLoyaltyType()==LoyaltyType.SILVER) {
			retVal=loyaltySettings.getClientDiscountPercentageSILVER();
		}
		else if(client.getLoyalty().getLoyaltyType()==LoyaltyType.GOLD) {
			retVal=loyaltySettings.getClientDiscountPercentageGOLD();
		}
		return new ResponseEntity<Double>(retVal,HttpStatus.OK);
	}

}
