package com.isa.isa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.model.Client;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.service.InstructorReservationService;
import com.isa.isa.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private InstructorReservationService instructorReservetionService;
	
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
		Client client= this.clientService.findByEmail(user.getName());
		instructorReservetionService.reserveAdventureByClient(clientAdventureReservationDTO, client);
		return new ResponseEntity<>(true,HttpStatus.OK);
	}

}
