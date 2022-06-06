package com.isa.isa.model.loyalty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface LoyaltyRepository extends JpaRepository<LoyaltySettings, Integer> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM LoyaltySettings r WHERE r.id=?1")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    LoyaltySettings getLoyaltySettings(int id);
}
