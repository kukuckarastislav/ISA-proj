package com.isa.isa.model.termins.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.repository.CottageFastResHistoryRepository;

@Service
public class CottageFastResHistoryService {

	@Autowired
    private CottageFastResHistoryRepository cottageFastResHistoryRepository;
}
