package com.isa.isa.model.termins.controller;

import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.DTO.NewEntityTermDTO;
import com.isa.isa.model.termins.service.CottageTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/cottage-term")
public class CottageTermController {

    @Autowired
    private CottageTermService cottageTermService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<ArrayList<EventDTO>> getTermForCottageOwnerCalendar(Principal user) {
        return new ResponseEntity<ArrayList<EventDTO>>(cottageTermService.getTermForCottageOwnerCalendar(user.getName()), HttpStatus.OK);
    }

    @GetMapping("/{idCottage}")
    @PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<ArrayList<EventDTO>> getTermForCottageCalendar(Principal user, @PathVariable int idCottage) {
        return new ResponseEntity<ArrayList<EventDTO>>(cottageTermService.getTermForCottageCalendar(user.getName(), idCottage), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<Boolean> createTermForCottage(Principal user, @RequestBody NewEntityTermDTO newEntityTermDTO){
        return new ResponseEntity<Boolean>(cottageTermService.createTermForCottage(user.getName(), newEntityTermDTO), HttpStatus.OK);
    }



}
