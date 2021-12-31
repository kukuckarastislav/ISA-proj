package com.isa.isa.controller;

import com.isa.isa.DTO.StuffDTO;
import com.isa.isa.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private InstructorService instructorService;

    @PutMapping("/registration")
    public ResponseEntity<StuffDTO> registrationStuff(@RequestBody StuffDTO stuffDTO){

        System.out.println("[api/person/registration] stuffDTO: " + stuffDTO.toString());

        if(stuffDTO.isAdmin()){

        }else if(stuffDTO.isCottageOwner()){

        }else if(stuffDTO.isBoatOwner()){

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
