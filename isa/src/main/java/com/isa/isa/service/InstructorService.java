package com.isa.isa.service;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Instructor;
import com.isa.isa.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public Instructor save(UserDTO userDTO) {
        Instructor instructor = new Instructor(userDTO);
        return instructorRepository.saveAndFlush(instructor);
    }
}
