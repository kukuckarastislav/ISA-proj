package com.isa.isa.model.termins.service;

import com.isa.isa.model.termins.repository.InstructorTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorTermService {

    @Autowired
    private InstructorTermRepository instructorTermRepository;
}
