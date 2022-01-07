package com.isa.isa.model.termins.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.repository.BoatReservationRepository;

@Service
public class BoatReservationService {

	@Autowired
    private BoatReservationRepository boatReservationRepository;
}
