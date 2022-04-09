package com.isa.isa.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
import com.isa.isa.model.Client;
import com.isa.isa.model.termins.DTO.ClientAdventureFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsAdventureDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.service.InsFastResHistoryService;
import com.isa.isa.model.termins.service.InstructorFastReservationService;
import com.isa.isa.model.termins.service.InstructorReservationService;
import com.isa.isa.security.service.EmailService;
import com.isa.isa.service.AdventureService;
import com.isa.isa.service.ClientService;
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
		instructorReservetionService.reserveAdventureByClient(clientAdventureReservationDTO, client);
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
		insFastResHistoryService.makeReservation(client, instructorFastReservation);
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

}
