package com.isa.isa.model.loyalty;

public class LoyaltySettings {

    private int clientScoreForReservation;      // broj poena koji dobija Client kad napravi rezervaciju
    private int ownerScoreForReservation;       // broj poena koji dobija Instructor, Boat owner, cottage owner

    private int minimumScoreForSILVER;
    private int minimumScoreForGOLD;

    private double clientDiscountPercentageSILVER;    // popust koji klijent ostvarjue kada je silver npr 5%
    private double clientDiscountPercentageGOLD;    // popust koji klijent ostvarjue kada je gold         15%

    private double ownerDiscountPercentageSILVER;    // popust koji klijent ostvarjue kada je silver      3%
    private double ownerDiscountPercentageGOLD;    // popust koji klijent ostvarjue kada je gold          10%

    private double systemPercentage;            // procenat koji sajt uzima od ownera npr 30% uzima sajt

    public LoyaltySettings(){}

    public LoyaltySettings(int clientScoreForReservation, int ownerScoreForReservation, int minimumScoreForSILVER, int minimumScoreForGOLD, double clientDiscountPercentageSILVER, double clientDiscountPercentageGOLD, double ownerDiscountPercentageSILVER, double ownerDiscountPercentageGOLD, double systemPercentage) {
        this.clientScoreForReservation = clientScoreForReservation;
        this.ownerScoreForReservation = ownerScoreForReservation;
        this.minimumScoreForSILVER = minimumScoreForSILVER;
        this.minimumScoreForGOLD = minimumScoreForGOLD;
        this.clientDiscountPercentageSILVER = clientDiscountPercentageSILVER;
        this.clientDiscountPercentageGOLD = clientDiscountPercentageGOLD;
        this.ownerDiscountPercentageSILVER = ownerDiscountPercentageSILVER;
        this.ownerDiscountPercentageGOLD = ownerDiscountPercentageGOLD;
        this.systemPercentage = systemPercentage;
    }

    public int getClientScoreForReservation() {
        return clientScoreForReservation;
    }

    public void setClientScoreForReservation(int clientScoreForReservation) {
        this.clientScoreForReservation = clientScoreForReservation;
    }

    public int getOwnerScoreForReservation() {
        return ownerScoreForReservation;
    }

    public void setOwnerScoreForReservation(int ownerScoreForReservation) {
        this.ownerScoreForReservation = ownerScoreForReservation;
    }

    public int getMinimumScoreForSILVER() {
        return minimumScoreForSILVER;
    }

    public void setMinimumScoreForSILVER(int minimumScoreForSILVER) {
        this.minimumScoreForSILVER = minimumScoreForSILVER;
    }

    public int getMinimumScoreForGOLD() {
        return minimumScoreForGOLD;
    }

    public void setMinimumScoreForGOLD(int minimumScoreForGOLD) {
        this.minimumScoreForGOLD = minimumScoreForGOLD;
    }

    public double getClientDiscountPercentageSILVER() {
        return clientDiscountPercentageSILVER;
    }

    public void setClientDiscountPercentageSILVER(double clientDiscountPercentageSILVER) {
        this.clientDiscountPercentageSILVER = clientDiscountPercentageSILVER;
    }

    public double getClientDiscountPercentageGOLD() {
        return clientDiscountPercentageGOLD;
    }

    public void setClientDiscountPercentageGOLD(double clientDiscountPercentageGOLD) {
        this.clientDiscountPercentageGOLD = clientDiscountPercentageGOLD;
    }

    public double getOwnerDiscountPercentageSILVER() {
        return ownerDiscountPercentageSILVER;
    }

    public void setOwnerDiscountPercentageSILVER(double ownerDiscountPercentageSILVER) {
        this.ownerDiscountPercentageSILVER = ownerDiscountPercentageSILVER;
    }

    public double getOwnerDiscountPercentageGOLD() {
        return ownerDiscountPercentageGOLD;
    }

    public void setOwnerDiscountPercentageGOLD(double ownerDiscountPercentageGOLD) {
        this.ownerDiscountPercentageGOLD = ownerDiscountPercentageGOLD;
    }

    public double getSystemPercentage() {
        return systemPercentage;
    }

    public void setSystemPercentage(double systemPercentage) {
        this.systemPercentage = systemPercentage;
    }
}
