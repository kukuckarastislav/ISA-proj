package com.isa.isa.controller;

import java.security.Principal;

import com.isa.isa.model.enums.UserTypeISA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.model.AccountDeleteRequest.model.AccountDeleteRequest;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.Client;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.AccountDeleteRequest.service.AccountDeleteRequestService;
import com.isa.isa.service.BoatOwnerService;
import com.isa.isa.service.CottageOwnerService;
import com.isa.isa.service.InstructorService;

@RestController
@RequestMapping("/api/boatowner")
public class BoatOwnerController {

	@Autowired
    private InstructorService instructorService;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private AccountDeleteRequestService accountDeleteRequestService; 
    
    
    @GetMapping("/profileInfo")
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")	
	public BoatOwner getInfo(Principal user) {
		return this.boatOwnerService.findByEmail(user.getName());
	}
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@PutMapping("/updateProfile")
	public BoatOwner updateClient(@RequestBody ClientDto clientDto) {
		Client client= new Client(clientDto);
		return this.boatOwnerService.update(client);
	}
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@PutMapping("/updatePassword")
	public Boolean updateClientPassword(@RequestBody PasswordDto passwordDto, Principal user) {
		BoatOwner client= this.boatOwnerService.findByEmail(user.getName());
		return this.boatOwnerService.updatePassword(client, passwordDto);
	}
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@PutMapping("/deleteAccount")
	public ResponseEntity<String> deleteAccount(Principal user) {
		CottageOwner client= this.cottageOwnerService.findByEmail(user.getName());
		if(accountDeleteRequestService.getByUsername(client.getEmail())!=null)
		{
			return new ResponseEntity("Request already submitted.", HttpStatus.OK);
		}
		//TODO: Sa fronta napraviti slanje razloga, nema potrebe da se salje tip korisnika
		accountDeleteRequestService.save(new AccountDeleteRequest(client.getEmail(),"TODO sa fronta napraviti slanje razloga", UserTypeISA.BOAT_OWNER));
		
		return new ResponseEntity("Request submitted.", HttpStatus.OK);
	}
}
