package com.isa.isa.service;

import com.isa.isa.model.ItemPrice;
import com.isa.isa.repository.ItemPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemPriceService {

    @Autowired
    private ItemPriceRepository itemPriceRepository;

    public ArrayList<ItemPrice> getAllItemPrices(){
        return (ArrayList<ItemPrice>) itemPriceRepository.findAll();
    }

    public ItemPrice addItemPrice(ItemPrice itemPrice) {
        ItemPrice newItemPrice = new ItemPrice();
        newItemPrice.setPrice(itemPrice.getPrice());
        newItemPrice.setDescription(itemPrice.getDescription());
        newItemPrice.setName(itemPrice.getName());
        return itemPriceRepository.saveAndFlush(newItemPrice);
    }
}
