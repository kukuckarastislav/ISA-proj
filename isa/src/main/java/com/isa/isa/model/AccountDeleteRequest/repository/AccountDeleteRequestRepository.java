package com.isa.isa.model.AccountDeleteRequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.AccountDeleteRequest.model.AccountDeleteRequest;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface AccountDeleteRequestRepository extends JpaRepository<AccountDeleteRequest, Integer> {
	
	AccountDeleteRequest getByUsername(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM AccountDeleteRequest r WHERE r.id=?1")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    AccountDeleteRequest getDelReqById(int idRequest);
}
