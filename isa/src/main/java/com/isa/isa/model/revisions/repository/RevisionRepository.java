package com.isa.isa.model.revisions.repository;

import com.isa.isa.model.revisions.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository  extends JpaRepository<Revision, Integer> {

}
