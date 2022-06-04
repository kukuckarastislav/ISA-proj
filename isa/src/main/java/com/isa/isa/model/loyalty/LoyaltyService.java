package com.isa.isa.model.loyalty;

import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.Client;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.Instructor;
import com.isa.isa.service.BoatOwnerService;
import com.isa.isa.service.ClientService;
import com.isa.isa.service.CottageOwnerService;
import com.isa.isa.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public LoyaltySettings setLoyaltySettings(LoyaltySettings newLoyaltySettings) {
        if(!isValid(newLoyaltySettings)) return null;
        var loyaltySettingsOptional = loyaltyRepository.findById(1);
        if(loyaltySettingsOptional.isPresent()){
            LoyaltySettings loyaltySettings = loyaltySettingsOptional.get();
            //izvrsiti azuriranje i proveriti sta se promenilo i onda pozvati service

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
        LoyaltySettings loyaltySettings = getLoyaltySettings();
        instructor.getLoyalty().addScore(loyaltySettings.getOwnerScoreForReservation());
        instructor.getLoyalty().update(loyaltySettings.getMinimumScoreForSILVER(), loyaltySettings.getMinimumScoreForGOLD());
    }

    public void applyReward(BoatOwner boatOwner) {
        LoyaltySettings loyaltySettings = getLoyaltySettings();
        boatOwner.getLoyalty().addScore(loyaltySettings.getOwnerScoreForReservation());
        boatOwner.getLoyalty().update(loyaltySettings.getMinimumScoreForSILVER(), loyaltySettings.getMinimumScoreForGOLD());
    }

    public void applyReward(CottageOwner cottageOwner) {
        LoyaltySettings loyaltySettings = getLoyaltySettings();
        cottageOwner.getLoyalty().addScore(loyaltySettings.getOwnerScoreForReservation());
        cottageOwner.getLoyalty().update(loyaltySettings.getMinimumScoreForSILVER(), loyaltySettings.getMinimumScoreForGOLD());
    }
}
