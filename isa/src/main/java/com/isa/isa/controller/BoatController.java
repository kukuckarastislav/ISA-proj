package com.isa.isa.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.BoatDTO;
import com.isa.isa.service.BoatService;

@RestController
@RequestMapping("/api/boats")
public class BoatController {
	
	@Autowired
    private BoatService boatService;
	
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
}
