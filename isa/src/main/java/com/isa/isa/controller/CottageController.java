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

import com.isa.isa.DTO.AddNewCottageDTO;
import com.isa.isa.DTO.CottageDTO;
import com.isa.isa.model.termins.DTO.NewCottageTermDto;
import com.isa.isa.model.termins.service.CottageTermService;
import com.isa.isa.service.CottageService;

@RestController
@RequestMapping("/api/cottages")
public class CottageController {
	
	@Autowired
    private CottageService cottageService;
	@Autowired
    private CottageTermService cottageTermService;
	
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
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
    @PostMapping("/addnewcottage")
    public ResponseEntity<CottageDTO> addNewCottage(@RequestBody AddNewCottageDTO addNewCoattageDTO, Principal user) {
        System.out.println("Creating new Cottage");
        CottageDTO cottageDTO = cottageService.addNewCottage(addNewCoattageDTO, user);
        if(cottageDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<CottageDTO>(cottageDTO, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PostMapping("/defineCottageTerms")
    public ResponseEntity<String> defineNewTermForBoat(@RequestBody NewCottageTermDto newCottageTermDto, Principal user) {
		String response = this.cottageTermService.defineNewTermForCottage(newCottageTermDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
