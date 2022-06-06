package com.isa.isa.model.complaints;

import com.isa.isa.model.complaints.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Complaint r WHERE r.id=?1")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    Complaint getComplaintById(int id);
}
