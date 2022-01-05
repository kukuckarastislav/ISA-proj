package com.isa.isa.model.termins.service;

import com.isa.isa.model.termins.repository.InsFastResHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsFastResHistoryService {

    @Autowired
    private InsFastResHistoryRepository insFastResHistoryRepository;
}
