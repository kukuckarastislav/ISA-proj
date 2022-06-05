package com.isa.isa.DTO;

import com.isa.isa.model.loyalty.BusinessStatistic;
import com.isa.isa.model.loyalty.LoyaltySettings;

import java.util.ArrayList;

public class InstructorBusinessReportDTO {

    private InstructorDTO instructorDTO;
    private ArrayList<BusinessStatistic> businessStatistics;
    private LoyaltySettings loyaltySettings;

    public InstructorBusinessReportDTO(){
        this.businessStatistics = new ArrayList<>();
    }

    public InstructorDTO getInstructorDTO() {
        return instructorDTO;
    }

    public void setInstructorDTO(InstructorDTO instructorDTO) {
        this.instructorDTO = instructorDTO;
    }

    public ArrayList<BusinessStatistic> getBusinessStatistics() {
        return businessStatistics;
    }

    public void setBusinessStatistics(ArrayList<BusinessStatistic> businessStatistics) {
        this.businessStatistics = businessStatistics;
    }

    public boolean addBusinessStatistic(BusinessStatistic businessStatistic){
        for(BusinessStatistic bs : businessStatistics){
            if(bs.getEntityName().equals(businessStatistic.getEntityName()))
                return false;
        }
        businessStatistics.add(businessStatistic);
        return true;
    }

    public BusinessStatistic getBusinessStatisticByEntityName(String entityName) {
        for(BusinessStatistic businessStatistic : businessStatistics){
            if(businessStatistic.getEntityName().equals(entityName))
                return businessStatistic;
        }
        return null;
    }

    public double getAllIncome(){
        double sumIncome = 0;
        for(BusinessStatistic businessStatistic : businessStatistics){
           sumIncome += businessStatistic.getIncome();
        }
        return sumIncome;
    }

    public LoyaltySettings getLoyaltySettings() {
        return loyaltySettings;
    }

    public void setLoyaltySettings(LoyaltySettings loyaltySettings) {
        this.loyaltySettings = loyaltySettings;
    }
}
