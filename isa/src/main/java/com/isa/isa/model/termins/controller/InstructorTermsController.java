package com.isa.isa.model.termins.controller;

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.model.revisions.DTO.RevisionAdminViewDTO;
import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.DTO.InstructorReservationDTO;
import com.isa.isa.model.termins.DTO.NewInstructorFastReservationDTO;
import com.isa.isa.model.termins.DTO.NewInstructorTermDTO;
import com.isa.isa.model.termins.model.TermType;
import com.isa.isa.model.termins.service.InstructorTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public ResponseEntity<ArrayList<EventDTO>> getTermForInstructorCalendar(Principal user) {
        return new ResponseEntity<ArrayList<EventDTO>>(instructorTermService.getTermForInstructorCalendar(user.getName()), HttpStatus.OK);
    }

    @PostMapping("/term")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public ResponseEntity<Boolean> createTermForInstructor(Principal user, @RequestBody NewInstructorTermDTO newInstructorTermDTO) {
        return new ResponseEntity<Boolean>(instructorTermService.createTermForInstructor(user.getName(), newInstructorTermDTO), HttpStatus.OK);
    }

    @GetMapping("/reservation")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public ResponseEntity<ArrayList<InstructorReservationDTO>> getReservationForInstructor(Principal user){
        return new ResponseEntity<ArrayList<InstructorReservationDTO>>(instructorTermService.getReservationForInstructor(user.getName()), HttpStatus.OK);
    }

    @GetMapping("/reservation/{type}/{id}")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public ResponseEntity<InstructorReservationDTO> getReservationForInstructorById(Principal user, @PathVariable TermType type, @PathVariable int id){
        return new ResponseEntity<InstructorReservationDTO>(instructorTermService.getReservationForInstructorById(user.getName(),type,id), HttpStatus.OK);
    }

    @GetMapping("/adventure/{idAdventure}")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public ResponseEntity<ArrayList<EventDTO>> getTermForAdventureCalendar(Principal user, @PathVariable int idAdventure) {
        return new ResponseEntity<ArrayList<EventDTO>>(instructorTermService.getTermForAdventureCalendar(user.getName(), idAdventure), HttpStatus.OK);
    }

    @PostMapping("/fast-reservation")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public ResponseEntity<String> createFastReservation(Principal user, @RequestBody NewInstructorFastReservationDTO newInstructorFastReservationDTO) {
        return new ResponseEntity<String>(instructorTermService.createFastReservation(user.getName(), newInstructorFastReservationDTO), HttpStatus.OK);
    }


}
