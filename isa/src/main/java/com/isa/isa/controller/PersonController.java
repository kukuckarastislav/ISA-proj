package com.isa.isa.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.DTO.AdventureViewDTO;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.Instructor;
import com.isa.isa.service.AdventureService;
import com.isa.isa.service.BoatOwnerService;
import com.isa.isa.service.BoatService;
import com.isa.isa.service.CottageOwnerService;
import com.isa.isa.service.CottageService;
import com.isa.isa.service.InstructorService;


@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private InstructorService instructorService;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private CottageService cottageService;
    @Autowired
    private BoatService boatService;
    @Autowired
    private AdventureService adventureService;
    
    @PutMapping("/registration")
    public ResponseEntity<UserDTO> registrationStuff(@RequestBody UserDTO userDTO){

        System.out.println("[api/person/registration] stuffDTO: " + userDTO.toString());

        if(userDTO.isAdmin()){

        }else if(userDTO.isCottageOwner()){
        	
        	CottageOwner owner = cottageOwnerService.register(userDTO);
        	if(owner==null)
        		return new ResponseEntity(userDTO,HttpStatus.BAD_REQUEST);//promeniti da vraca userexists

        }else if(userDTO.isBoatOwner()){
        	
        	BoatOwner owner = boatOwnerService.register(userDTO);
        	if(owner==null)
        		return new ResponseEntity(userDTO,HttpStatus.BAD_REQUEST);//promeniti da vraca userexists


        }else if(userDTO.isInstructor()){
            System.out.println("Registracija instruktora");
            instructorService.save(userDTO);
            System.out.println("Registracija uspesna");
        }else{
            return new ResponseEntity(userDTO, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
    
    @GetMapping("/instructors")
    public ResponseEntity<ArrayList<Instructor>> getAllInstructorsWithAdventures() {
        ArrayList<Instructor> instructors = instructorService.getAllInstructorsWithAdventures();
        return new ResponseEntity<ArrayList<Instructor>>(instructors, HttpStatus.OK);
    }
    
    @GetMapping("/instructors/{instructorId}")
    public ResponseEntity<Instructor> getAdventureByInstructorAndName(@PathVariable Integer instructorId) {
        return new ResponseEntity<Instructor>(instructorService.getInstructorById(instructorId), HttpStatus.OK);
    }
    
    @GetMapping("/cottages")
    public ResponseEntity<ArrayList<Cottage>> getAllCottages() {
        return new ResponseEntity<ArrayList<Cottage>>(cottageService.getAllCottages(), HttpStatus.OK);
    }
    
    @GetMapping("/cottages/{cottageId}")
    public ResponseEntity<Cottage> getCottageById(@PathVariable Integer cottageId) {
        return new ResponseEntity<Cottage>(cottageService.getCottageWithOwner(cottageId), HttpStatus.OK);
    }
    
    @GetMapping("/boats")
    public ResponseEntity<ArrayList<Boat>> getAllBoats() {
        return  new ResponseEntity<ArrayList<Boat>>(boatService.getAllBoats(), HttpStatus.OK);
    }
    
    @GetMapping("/adventures")
    public ResponseEntity<ArrayList<Adventure>> getAllAdventures() {
        return new ResponseEntity<ArrayList<Adventure>>(adventureService.getAllAdventures(), HttpStatus.OK);
    }
    
    @GetMapping("/adventures/{adventureId}")
    public ResponseEntity<Adventure> getAdventureById(@PathVariable Integer adventureId) {
        return new ResponseEntity<Adventure>(adventureService.getAdventureWithInstructor(adventureId), HttpStatus.OK);
    }

}
