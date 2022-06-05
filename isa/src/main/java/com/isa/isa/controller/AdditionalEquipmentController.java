package com.isa.isa.controller;

import com.isa.isa.DTO.AdventureDTO;
import com.isa.isa.model.AdditionalEquipment;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.service.AdditionalEquipmentService;
import com.isa.isa.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<AdditionalEquipment> addAdditionalEquipment(@RequestBody AdditionalEquipment additionalEquipment) {
        return new ResponseEntity<AdditionalEquipment>(additionalEquipmentService.addAdditionalEquipment(additionalEquipment), HttpStatus.OK);
    }
}
