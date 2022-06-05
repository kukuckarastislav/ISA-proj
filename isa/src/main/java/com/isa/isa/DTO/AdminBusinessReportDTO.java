package com.isa.isa.DTO;

import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.loyalty.Loyalty;

public class AdminBusinessReportDTO {
    private UserTypeISA userTypeISA;
    private Loyalty loyalty;
    private String email;
    private String fullName;
    private double income;

    public AdminBusinessReportDTO(){}

    public AdminBusinessReportDTO(UserTypeISA userTypeISA, Loyalty loyalty, String email, String fullName, double income) {
        this.userTypeISA = userTypeISA;
        this.loyalty = loyalty;
        this.email = email;
        this.fullName = fullName;
        this.income = income;
    }

    public AdminBusinessReportDTO(Instructor instructor, double allIncome) {
        this.userTypeISA = UserTypeISA.INSTRUCTOR;
        this.loyalty = instructor.getLoyalty();
        this.email = instructor.getEmail();
        this.fullName = instructor.getFullName();
        this.income = allIncome;
    }

    public AdminBusinessReportDTO(BoatOwner boatOwner, double allIncome) {
        this.userTypeISA = UserTypeISA.BOAT_OWNER;
        this.loyalty = boatOwner.getLoyalty();
        this.email = boatOwner.getEmail();
        this.fullName = boatOwner.getFullName();
        this.income = allIncome;
    }

    public AdminBusinessReportDTO(CottageOwner cottageOwner, double allIncome) {
        this.userTypeISA = UserTypeISA.COTTAGE_OWNER;
        this.loyalty = cottageOwner.getLoyalty();
        this.email = cottageOwner.getEmail();
        this.fullName = cottageOwner.getFullName();
        this.income = allIncome;
    }

    public UserTypeISA getUserTypeISA() {
        return userTypeISA;
    }

    public void setUserTypeISA(UserTypeISA userTypeISA) {
        this.userTypeISA = userTypeISA;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
