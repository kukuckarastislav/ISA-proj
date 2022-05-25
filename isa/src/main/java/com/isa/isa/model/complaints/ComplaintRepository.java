package com.isa.isa.model.complaints;

import com.isa.isa.model.complaints.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {



}
