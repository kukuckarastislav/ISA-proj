package com.isa.isa.service;

import com.isa.isa.model.AdditionalEquipment;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.repository.AdditionalEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdditionalEquipmentService {

    @Autowired
    private AdditionalEquipmentRepository additionalEquipmentRepository;

    public ArrayList<AdditionalEquipment> getAllAdditionalEquipment() {
        return (ArrayList<AdditionalEquipment>)additionalEquipmentRepository.findAll();
    }

    public AdditionalEquipment addAdditionalEquipment(AdditionalEquipment additionalEquipment) {
        AdditionalEquipment newAdditionalEquipment = new AdditionalEquipment();
        newAdditionalEquipment.setName(additionalEquipment.getName());
        return additionalEquipmentRepository.saveAndFlush(newAdditionalEquipment);
    }
}
