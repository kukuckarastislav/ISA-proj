package com.isa.isa.model.termins.controller;

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.service.InstructorTermService;
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
@RequestMapping("/api/instructorterms")
public class InstructorTermsController {

    @Autowired
    private InstructorTermService instructorTermService;

    //@PreAuthorize("hasRole(permitAll())")
    @GetMapping("/{instructorname}/{adventurename}")
    public ResponseEntity<ArrayList<EventDTO>> getAdventureByInstructor(@PathVariable String instructorname, @PathVariable String adventurename) {
        System.out.println("Instructor: " + instructorname);
        System.out.println("adventurename: " + adventurename);
        ArrayList<EventDTO> instructorAndAdventureTerms = instructorTermService.getAdventureTerms(instructorname, adventurename);
        return new ResponseEntity<ArrayList<EventDTO>>(instructorAndAdventureTerms, HttpStatus.OK);
    }

}
