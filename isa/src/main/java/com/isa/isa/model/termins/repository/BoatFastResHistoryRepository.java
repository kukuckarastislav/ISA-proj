package com.isa.isa.model.termins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.model.termins.model.BoatFastResHistory;

public interface BoatFastResHistoryRepository extends JpaRepository<BoatFastResHistory, Integer>{
	
	List<BoatFastResHistory> findAllByClientId(int id);
	List<BoatFastResHistory> findAllByBoatFastReservationId(int boatFastResId);
	BoatFastResHistory findByClientIdAndBoatFastReservationId(int clientId, int boatFastReservationId);

}
