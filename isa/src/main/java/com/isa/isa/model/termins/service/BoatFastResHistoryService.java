package com.isa.isa.model.termins.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.repository.BoatFastResHistoryRepository;

@Service
public class BoatFastResHistoryService {

	@Autowired
    private BoatFastResHistoryRepository boatFastResHistoryRepository;
}
