package com.isa.isa.model.termins.service;

import com.isa.isa.model.termins.repository.InstructorReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorReservationService {

    @Autowired
    private InstructorReservationRepository instructorReservationRepository;
}
