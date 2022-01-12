package com.isa.isa.controller;

import java.security.Principal;

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
import com.isa.isa.DTO.ClientPasswordDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.AccountDeleteRequest;
import com.isa.isa.model.Client;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.service.AccountDeleteRequestService;
import com.isa.isa.service.BoatOwnerService;
import com.isa.isa.service.CottageOwnerService;
import com.isa.isa.service.InstructorService;

@RestController
@RequestMapping("/api/cottageowner")
public class CottageOwnerController {

	@Autowired
    private InstructorService instructorService;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private AccountDeleteRequestService accountDeleteRequestService; 
    
    @GetMapping("/profileInfo")
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")	
	public CottageOwner getInfo(Principal user) {
		return this.cottageOwnerService.findByEmail(user.getName());
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PutMapping("/updateProfile")
	public CottageOwner updateClient(@RequestBody ClientDto clientDto) {
		Client client= new Client(clientDto);
		return this.cottageOwnerService.update(client);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PutMapping("/updatePassword")
	public Boolean updateClientPassword(@RequestBody ClientPasswordDto passwordDto, Principal user) {
		CottageOwner client= this.cottageOwnerService.findByEmail(user.getName());
		return this.cottageOwnerService.updatePassword(client, passwordDto);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PutMapping("/deleteAccount")
	public ResponseEntity<String> deleteAccount(Principal user) {
		CottageOwner client= this.cottageOwnerService.findByEmail(user.getName());
		if(accountDeleteRequestService.getByUsername(client.getEmail())!=null)
		{
			return new ResponseEntity("Request already submitted.", HttpStatus.OK);
		}
		accountDeleteRequestService.save(new AccountDeleteRequest(client.getEmail(),OwnerType.COTTAGE_OWNER));
		
		return new ResponseEntity("Request submitted.", HttpStatus.OK);
	}
	
	
}
