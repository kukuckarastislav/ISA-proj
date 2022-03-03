package com.isa.isa.controller;


import com.isa.isa.DTO.AddNewAdventureDTO;
import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.DTO.AdventureViewDTO;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Client;
import com.isa.isa.security.model.User;
import com.isa.isa.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/adventure")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @GetMapping("/byinstructor")
    public ResponseEntity<ArrayList<AdventureDTO>> getAdventureByInstructor(Principal user) {
        ArrayList<AdventureDTO> adventuresByInstructor = adventureService.getAdventureDTOByInstructor(user.getName());
        return new ResponseEntity<ArrayList<AdventureDTO>>(adventuresByInstructor, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @GetMapping("/byinstructor/{adventurename}")
    public ResponseEntity<AdventureViewDTO> getAdventureByInstructorAndName(Principal user, @PathVariable String adventurename) {
        AdventureViewDTO adventureViewDTO = adventureService.getAdventureDTOByInstructorAndName(user.getName(), adventurename);
        return new ResponseEntity<AdventureViewDTO>(adventureViewDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @PutMapping("/addnewadventure")
    public ResponseEntity<AdventureViewDTO> addNewAdventure(@RequestBody AddNewAdventureDTO addNewAdventureDTO, Principal user) {
        System.out.println("Creating New Adventure");
        AdventureViewDTO adventureViewDTO = adventureService.addNewAdventure(addNewAdventureDTO, user);
        if(adventureViewDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<AdventureViewDTO>(adventureViewDTO, HttpStatus.OK);
    }







}
