package com.isa.isa.model.loyalty;

import com.isa.isa.DTO.InstructorBusinessReportDTO;
import com.isa.isa.model.*;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.service.BoatOwnerService;
import com.isa.isa.service.ClientService;
import com.isa.isa.service.CottageOwnerService;
import com.isa.isa.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class LoyaltyService {

    @Autowired
    private LoyaltyRepository loyaltyRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CottageOwnerService cottageOwnerService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    public LoyaltySettings getLoyaltySettings() {
        var loyaltySettings = loyaltyRepository.findById(1);
        if(loyaltySettings.isPresent()){
            return loyaltySettings.get();
        }
        return new LoyaltySettings();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public LoyaltySettings setLoyaltySettings(LoyaltySettings newLoyaltySettings) {
        if(!isValid(newLoyaltySettings)) return null;
        try {
            LoyaltySettings loyaltySettings = loyaltyRepository.getLoyaltySettings(1);
            if(loyaltySettings != null){
                Boolean updateUsers = false;

                if(loyaltySettings.getClientScoreForReservation() != newLoyaltySettings.getClientScoreForReservation()){
                    loyaltySettings.setClientScoreForReservation(newLoyaltySettings.getClientScoreForReservation());
                }
                if(loyaltySettings.getOwnerScoreForReservation() != newLoyaltySettings.getOwnerScoreForReservation()){
                    loyaltySettings.setOwnerScoreForReservation(newLoyaltySettings.getOwnerScoreForReservation());
                }
                if(loyaltySettings.getMinimumScoreForSILVER() != newLoyaltySettings.getMinimumScoreForSILVER()){
                    loyaltySettings.setMinimumScoreForSILVER(newLoyaltySettings.getMinimumScoreForSILVER());
                    // izvrsiti izmenu na osobama
                    updateUsers = true;
                }
                if(loyaltySettings.getMinimumScoreForGOLD() != newLoyaltySettings.getMinimumScoreForGOLD()){
                    loyaltySettings.setMinimumScoreForGOLD(newLoyaltySettings.getMinimumScoreForGOLD());
                    // izvrsiti izmenu na osobama
                    updateUsers = true;
                }
                if(loyaltySettings.getClientDiscountPercentageSILVER() != newLoyaltySettings.getClientDiscountPercentageSILVER()){
                    loyaltySettings.setClientDiscountPercentageSILVER(newLoyaltySettings.getClientDiscountPercentageSILVER());
                }
                if(loyaltySettings.getClientDiscountPercentageGOLD() != newLoyaltySettings.getClientDiscountPercentageGOLD()){
                    loyaltySettings.setClientDiscountPercentageGOLD(newLoyaltySettings.getClientDiscountPercentageGOLD());
                }
                if(loyaltySettings.getOwnerDiscountPercentageSILVER() != newLoyaltySettings.getOwnerDiscountPercentageSILVER()){
                    loyaltySettings.setOwnerDiscountPercentageSILVER(newLoyaltySettings.getOwnerDiscountPercentageSILVER());
                }
                if(loyaltySettings.getOwnerDiscountPercentageGOLD() != newLoyaltySettings.getOwnerDiscountPercentageGOLD()){
                    loyaltySettings.setOwnerDiscountPercentageGOLD(newLoyaltySettings.getOwnerDiscountPercentageGOLD());
                }
                if(loyaltySettings.getSystemPercentage() != newLoyaltySettings.getSystemPercentage()){
                    loyaltySettings.setSystemPercentage(newLoyaltySettings.getSystemPercentage());
                }

                loyaltyRepository.saveAndFlush(loyaltySettings);

                if(updateUsers){
                    instructorService.updateLoyaltyForAll(loyaltySettings);
                    boatOwnerService.updateLoyaltyForAll(loyaltySettings);
                    cottageOwnerService.updateLoyaltyForAll(loyaltySettings);
                    clientService.updateLoyaltyForAll(loyaltySettings);
                }

                return loyaltySettings;
            }

        }catch (PessimisticLockingFailureException e){
            System.out.println("error PessimisticLockingFailureException");
        }
        return null;
    }

    private Boolean isValid(LoyaltySettings newLoyaltySettings){
        if(newLoyaltySettings.getClientScoreForReservation() < 0) return false;
        if(newLoyaltySettings.getOwnerScoreForReservation() < 0) return false;
        if(newLoyaltySettings.getSystemPercentage() < 0 || newLoyaltySettings.getSystemPercentage() > 99) return false;
        if(newLoyaltySettings.getMinimumScoreForSILVER() < 0) return false;
        if(newLoyaltySettings.getMinimumScoreForGOLD() < 0) return false;
        if(newLoyaltySettings.getMinimumScoreForSILVER() >= newLoyaltySettings.getMinimumScoreForGOLD()) return false;
        if(newLoyaltySettings.getClientDiscountPercentageSILVER() < 0) return false;
        if(newLoyaltySettings.getClientDiscountPercentageGOLD() < 0 || newLoyaltySettings.getClientDiscountPercentageGOLD() > 99) return false;
        if(newLoyaltySettings.getClientDiscountPercentageSILVER() >= newLoyaltySettings.getClientDiscountPercentageGOLD()) return false;
        if(newLoyaltySettings.getOwnerDiscountPercentageSILVER() < 0) return false;
        if(newLoyaltySettings.getOwnerDiscountPercentageGOLD() < 0) return false;
        if(newLoyaltySettings.getOwnerDiscountPercentageSILVER() >= newLoyaltySettings.getOwnerDiscountPercentageGOLD()) return false;
        if(newLoyaltySettings.getOwnerDiscountPercentageSILVER() > newLoyaltySettings.getSystemPercentage()) return false;
        if(newLoyaltySettings.getOwnerDiscountPercentageGOLD() > newLoyaltySettings.getSystemPercentage()) return false;
        return true;
    }

    public double applyDiscount(Client client, double price) {
        LoyaltySettings loyaltySettings = getLoyaltySettings();
        if(client.getLoyalty().getLoyaltyType() == LoyaltyType.GOLD){
            price = price - (price*loyaltySettings.getClientDiscountPercentageGOLD())/100;
        }else if(client.getLoyalty().getLoyaltyType() == LoyaltyType.SILVER){
            price = price - (price*loyaltySettings.getClientDiscountPercentageSILVER())/100;
        }

        return price;
    }

    public void applyReward(Client client) {
        LoyaltySettings loyaltySettings = getLoyaltySettings();
        client.getLoyalty().addScore(loyaltySettings.getClientScoreForReservation());
        client.getLoyalty().update(loyaltySettings.getMinimumScoreForSILVER(), loyaltySettings.getMinimumScoreForGOLD());
    }

    public void applyReward(Instructor instructor) {
        applyRewardOwner(instructor.getLoyalty());
    }

    public void applyReward(BoatOwner boatOwner) {
        applyRewardOwner(boatOwner.getLoyalty());
    }

    public void applyReward(CottageOwner cottageOwner) {
        applyRewardOwner(cottageOwner.getLoyalty());
    }

    public void applyRewardOwner(Loyalty loyalty) {
        LoyaltySettings loyaltySettings = getLoyaltySettings();
        loyalty.addScore(loyaltySettings.getOwnerScoreForReservation());
        loyalty.update(loyaltySettings.getMinimumScoreForSILVER(), loyaltySettings.getMinimumScoreForGOLD());
    }

    public double calculateIncome(Instructor instructor, double price) {
        return calculateIncomeOwner(instructor.getLoyalty(), price);
    }

    public double calculateIncome(BoatOwner boatOwner, double price) {
        return calculateIncomeOwner(boatOwner.getLoyalty(), price);
    }

    public double calculateIncome(CottageOwner cottageOwner, double price) {
        return calculateIncomeOwner(cottageOwner.getLoyalty(), price);
    }

    public double calculateIncomeOwner(Loyalty loyalty, double price) {
        LoyaltySettings loyaltySettings = getLoyaltySettings();
        double income = 0;
        if(loyalty.getLoyaltyType() == LoyaltyType.GOLD){
            income = price - price * (loyaltySettings.getSystemPercentage()-loyaltySettings.getOwnerDiscountPercentageGOLD())/100;
        }else if(loyalty.getLoyaltyType() == LoyaltyType.SILVER){
            income = price - price * (loyaltySettings.getSystemPercentage()-loyaltySettings.getOwnerDiscountPercentageSILVER())/100;
        }else{
            income = price - price * loyaltySettings.getSystemPercentage()/100;
        }
        return income;
    }

}
