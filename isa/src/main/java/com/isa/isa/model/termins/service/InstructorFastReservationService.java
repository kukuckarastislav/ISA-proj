package com.isa.isa.model.termins.service;

import com.isa.isa.model.termins.repository.InstructorFastReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorFastReservationService {

    @Autowired
    private InstructorFastReservationRepository instructorFastReservationRepository;
}
