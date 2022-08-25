package com.isa.isa.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.AddNewBoatDTO;
import com.isa.isa.DTO.BoatDTO;

import com.isa.isa.model.termins.DTO.NewBoatFastReservationDto;
import com.isa.isa.model.termins.DTO.NewBoatTermDto;
import com.isa.isa.model.termins.service.BoatReservationService;
import com.isa.isa.model.termins.service.BoatTermService;

import com.isa.isa.DTO.UpdateBoatDTO;

import com.isa.isa.service.BoatService;

@RestController
@RequestMapping("/api/boats")
public class BoatController {
	
	@Autowired
    private BoatService boatService;
	@Autowired
	private BoatTermService boatTermService;
	@Autowired
	private BoatReservationService boatReservationService;
	
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @GetMapping("/byowner")
    public ResponseEntity<ArrayList<BoatDTO>> getBoatsByOwner(Principal user) {
        ArrayList<BoatDTO> boatsByOwner = boatService.getBoatDTOByBoatOwner(user.getName());
        return new ResponseEntity<ArrayList<BoatDTO>>(boatsByOwner, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @GetMapping("/byowner/{boatname}")
    public ResponseEntity<BoatDTO> getBoatByOwnerAndName(Principal user, @PathVariable String boatname) {
		BoatDTO boatDto = boatService.getBoatDTOByOwnerAndName(user.getName(), boatname);
        return new ResponseEntity<BoatDTO>(boatDto, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @PostMapping("/addnewboat")
    public ResponseEntity<BoatDTO> addNewBoat(@RequestBody AddNewBoatDTO addNewBoatDTO, Principal user) {
        System.out.println("Creating new Boat");
        BoatDTO boatDTO = boatService.addNewBoat(addNewBoatDTO, user);
        if(boatDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<BoatDTO>(boatDTO, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@PostMapping("/defineBoatTerms")
    public ResponseEntity<String> defineNewTermForBoat(@RequestBody NewBoatTermDto newBoatTermDto, Principal user) {
		String response = this.boatTermService.defineNewTermForBoat(newBoatTermDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@PostMapping(value = "/addFastBoatReservationTerm")
    public ResponseEntity<String> defineFastReservationTerm(@RequestBody NewBoatFastReservationDto dto) {
		String response = this.boatReservationService.defineNewFastReservationForBoat(dto);
		
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @PostMapping("/updateboat")
    public ResponseEntity<BoatDTO> updateBoat(@RequestBody UpdateBoatDTO updateBoatDTO, Principal user) {
        System.out.println("Updating boat");
        BoatDTO boatDTO = boatService.updateBoat(updateBoatDTO, user);
        if(boatDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<BoatDTO>(boatDTO, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @GetMapping("/boats/calculate/{boatname}")
    public ResponseEntity<BoatDTO> getCalculationsForBoat(Principal user, @PathVariable String boatname) {
		BoatDTO boatDto = boatService.getCalculationsForBoatReservations(boatname);
        return new ResponseEntity<BoatDTO>(boatDto, HttpStatus.OK);
    }

}
