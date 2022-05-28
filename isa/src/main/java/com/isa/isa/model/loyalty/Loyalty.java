package com.isa.isa.model.loyalty;


import javax.persistence.Embeddable;

@Embeddable
public class Loyalty {
    private int score;
    private LoyaltyType loyaltyType;

    public Loyalty(){
        score = 0;
        loyaltyType = LoyaltyType.REGULAR;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LoyaltyType getLoyaltyType() {
        return loyaltyType;
    }

    public void setLoyaltyType(LoyaltyType loyaltyType) {
        this.loyaltyType = loyaltyType;
    }

    public Boolean update(int minimumScoreForSILVER, int minimumScoreForGOLD){
        if(score >= minimumScoreForGOLD){
            loyaltyType = LoyaltyType.GOLD;
            return true;
        }
        if(score >= minimumScoreForSILVER){
            loyaltyType = LoyaltyType.SILVER;
            return true;
        }
        return false;   // nije bilo potrebno menjati :)
    }
}
