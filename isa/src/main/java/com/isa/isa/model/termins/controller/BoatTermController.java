package com.isa.isa.model.termins.controller;


import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.DTO.NewEntityTermDTO;
import com.isa.isa.model.termins.DTO.NewInstructorTermDTO;
import com.isa.isa.model.termins.repository.BoatReservationRepository;
import com.isa.isa.model.termins.service.BoatTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/boat-term")
public class BoatTermController {

    @Autowired
    private BoatTermService boatTermService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<ArrayList<EventDTO>> getTermForBoatOwnerCalendar(Principal user) {
        return new ResponseEntity<ArrayList<EventDTO>>(boatTermService.getTermForBoatOwnerCalendar(user.getName()), HttpStatus.OK);
    }

    @GetMapping("/{idBoat}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<ArrayList<EventDTO>> getTermForBoatCalendar(Principal user, @PathVariable int idBoat) {
        return new ResponseEntity<ArrayList<EventDTO>>(boatTermService.getTermForBoatCalendar(user.getName(), idBoat), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<Boolean> createTermForBoat(Principal user, @RequestBody NewEntityTermDTO newEntityTermDTO){
        return new ResponseEntity<Boolean>(boatTermService.createTermForBoat(user.getName(), newEntityTermDTO), HttpStatus.OK);
    }


}
