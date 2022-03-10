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

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.DTO.AdventureViewDTO;
import com.isa.isa.DTO.CottageDTO;
import com.isa.isa.service.CottageService;

@RestController
@RequestMapping("/api/cottages")
public class CottageController {
	
	@Autowired
    private CottageService cottageService;
	
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
    @GetMapping("/byowner")
    public ResponseEntity<ArrayList<CottageDTO>> getAdventureByOwner(Principal user) {
        ArrayList<CottageDTO> cottagesByOwner = cottageService.getCottageDTOByCottageOwner(user.getName());
        return new ResponseEntity<ArrayList<CottageDTO>>(cottagesByOwner, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
    @GetMapping("/byowner/{cottagename}")
    public ResponseEntity<CottageDTO> getCottageByOwnerAndName(Principal user, @PathVariable String cottagename) {
		CottageDTO cottageDto = cottageService.getCottageDTOByOwnerAndName(user.getName(), cottagename);
        return new ResponseEntity<CottageDTO>(cottageDto, HttpStatus.OK);
    }

}
