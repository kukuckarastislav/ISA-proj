package com.isa.isa.service;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Instructor;
import com.isa.isa.repository.AdventureRepository;
import com.isa.isa.repository.InstructorRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private AdventureRepository adventureRepository;

    public Instructor save(UserDTO userDTO) {
        Instructor instructor = new Instructor(userDTO);
        return instructorRepository.saveAndFlush(instructor);
    }
    
    public ArrayList<Instructor> getAllInstructorsWithAdventures(){
    	ArrayList<Instructor> instructors = new ArrayList<Instructor>(instructorRepository.findAll());
    	for(Instructor i : instructors){
    		//i.setAdventures(new HashSet<>(adventureRepository.getByInstructorId(i.getId())));
    		Set<Adventure>adventures = new HashSet<>(adventureRepository.getByInstructorId(i.getId()));
    		for (Adventure a : adventures) {
    			a.setInstructor(null);
    		}
    		i.setAdventures(adventures);
    	}
    	return instructors;
    }
    
    public Instructor getInstructorById(int id) {
    	Instructor instructor = instructorRepository.getById(id);
    	Set<Adventure>adventures = new HashSet<>(adventureRepository.getByInstructorId(instructor.getId()));
		for (Adventure a : adventures) {
			a.setInstructor(null);
		}
		instructor.setAdventures(adventures);
    	return instructor;
    }
}
