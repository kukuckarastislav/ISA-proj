package com.isa.isa.controller;


import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.model.Client;
import com.isa.isa.security.model.User;
import com.isa.isa.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/adventure")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @GetMapping("/byinstructor/{username}")
    public ResponseEntity<ArrayList<AdventureDTO>> getAdventureByInstructor(@PathVariable String username) {
        ArrayList<AdventureDTO> adventuresByInstructor = adventureService.getAdventureDTOByInstructor(username);
        return new ResponseEntity<ArrayList<AdventureDTO>>(adventuresByInstructor, HttpStatus.OK);
    }



}
