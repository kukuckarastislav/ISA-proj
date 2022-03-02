package com.isa.isa.controller;

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.model.AdditionalEquipment;
import com.isa.isa.service.AdditionalEquipmentService;
import com.isa.isa.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/additionalequipment")
public class AdditionalEquipmentController {

    @Autowired
    private AdditionalEquipmentService additionalEquipmentService;

    @GetMapping("/getadditionalequipment")
    public ResponseEntity<ArrayList<AdditionalEquipment>> getAllAdditionalEquipment() {
        ArrayList<AdditionalEquipment> allAdditionalEquipment = additionalEquipmentService.getAllAdditionalEquipment();
        return new ResponseEntity<ArrayList<AdditionalEquipment>>(allAdditionalEquipment, HttpStatus.OK);
    }

}
