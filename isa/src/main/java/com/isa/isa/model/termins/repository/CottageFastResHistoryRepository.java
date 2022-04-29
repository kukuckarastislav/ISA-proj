package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.CottageFastResHistory;

public interface CottageFastResHistoryRepository extends JpaRepository<CottageFastResHistory, Integer>{
	
	List<CottageFastResHistory> findAllByClientId(int id);
	List<CottageFastResHistory> findAllByCottageFastReservationId(int cottageFastResId);
	CottageFastResHistory findByClientIdAndCottageFastReservationId(int clientId, int cottageFastReservationId);
}
