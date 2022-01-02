package com.isa.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.StuffDTO;
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
    public ResponseEntity<StuffDTO> registrationStuff(@RequestBody StuffDTO stuffDTO){

        System.out.println("[api/person/registration] stuffDTO: " + stuffDTO.toString());

        if(stuffDTO.isAdmin()){

        }else if(stuffDTO.isCottageOwner()){
        	
        	CottageOwner owner = cottageOwnerService.register(stuffDTO);
        	if(owner==null)
        		return new ResponseEntity(stuffDTO,HttpStatus.BAD_REQUEST);//promeniti da vraca userexists	

        }else if(stuffDTO.isBoatOwner()){
        	
        	BoatOwner owner = boatOwnerService.register(stuffDTO);
        	if(owner==null)
        		return new ResponseEntity(stuffDTO,HttpStatus.BAD_REQUEST);//promeniti da vraca userexists	


        }else if(stuffDTO.isInstrucotr()){
            System.out.println("Registracija instruktora");
            instructorService.save(stuffDTO);
            System.out.println("Registracija uspesna");
        }else{
            return new ResponseEntity(stuffDTO, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<StuffDTO>(stuffDTO, HttpStatus.OK);
    }

}
