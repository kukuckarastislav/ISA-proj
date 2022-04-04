package com.isa.isa.model.termins.repository;

import com.isa.isa.model.termins.model.InsFastResHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InsFastResHistoryRepository extends JpaRepository<InsFastResHistory, Integer> {
	List<InsFastResHistory> findAllByClientId(int id);
}
