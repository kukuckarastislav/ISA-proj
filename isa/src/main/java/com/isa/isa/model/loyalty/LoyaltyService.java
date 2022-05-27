package com.isa.isa.model.loyalty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyService {

    @Autowired
    private LoyaltyRepository loyaltyRepository;

    public LoyaltySettings getLoyaltySettings() {
        var loyaltySettings = loyaltyRepository.findById(1);
        if(loyaltySettings.isPresent()){
            return loyaltySettings.get();
        }
        return new LoyaltySettings();
    }

    public LoyaltySettings setLoyaltySettings(LoyaltySettings newLoyaltySettings) {
        var loyaltySettingsOptional = loyaltyRepository.findById(1);
        if(loyaltySettingsOptional.isPresent()){
            LoyaltySettings loyaltySettings = loyaltySettingsOptional.get();
            //izvrsiti azuriranje i proveriti sta se promenilo i onda pozvati service
            if(loyaltySettings.getClientScoreForReservation() != newLoyaltySettings.getClientScoreForReservation()){
                loyaltySettings.setClientScoreForReservation(newLoyaltySettings.getClientScoreForReservation());
            }
            if(loyaltySettings.getOwnerScoreForReservation() != newLoyaltySettings.getOwnerScoreForReservation()){
                loyaltySettings.setOwnerScoreForReservation(newLoyaltySettings.getOwnerScoreForReservation());
            }
            if(loyaltySettings.getMinimumScoreForSILVER() != newLoyaltySettings.getMinimumScoreForSILVER()){
                loyaltySettings.setMinimumScoreForSILVER(newLoyaltySettings.getMinimumScoreForSILVER());
            }
            if(loyaltySettings.getMinimumScoreForGOLD() != newLoyaltySettings.getMinimumScoreForGOLD()){
                loyaltySettings.setMinimumScoreForGOLD(newLoyaltySettings.getMinimumScoreForGOLD());
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
            return loyaltySettings;
        }
        return newLoyaltySettings;
    }
}
