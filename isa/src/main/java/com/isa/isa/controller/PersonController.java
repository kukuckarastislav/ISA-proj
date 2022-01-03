package com.isa.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.service.BoatOwnerService;
import com.isa.isa.service.CottageOwnerService;
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

}
