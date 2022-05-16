package com.isa.isa.model.revisions.repository;

import com.isa.isa.model.termins.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository  extends JpaRepository<Revision, Integer> {

}
