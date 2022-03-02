package com.isa.isa.controller;

import com.isa.isa.model.ItemPrice;
import com.isa.isa.service.ItemPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/itemprice")
public class ItemPriceController {

    @Autowired
    private ItemPriceService itemPriceService;

    @GetMapping("/getitemprices")
    public ResponseEntity<ArrayList<ItemPrice>> getAllItemPrices() {
        ArrayList<ItemPrice> allAdditionalEquipment = itemPriceService.getAllItemPrices();
        return new ResponseEntity<ArrayList<ItemPrice>>(allAdditionalEquipment, HttpStatus.OK);
    }
}
