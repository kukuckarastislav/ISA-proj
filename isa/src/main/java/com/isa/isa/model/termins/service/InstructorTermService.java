package com.isa.isa.model.termins.service;


import com.isa.isa.model.Adventure;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.model.InstructorTerms;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.InstructorReservationRepository;
import com.isa.isa.model.termins.repository.InstructorTermRepository;
import com.isa.isa.repository.AdventureRepository;
import com.isa.isa.repository.InstructorRepository;
import com.isa.isa.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorTermService {

    @Autowired
    private InstructorTermRepository instructorTermRepository;

    @Autowired
    private InstructorReservationRepository instructorReservationRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AdventureService adventureService;


    public ArrayList<EventDTO> getAdventureTerms(String instructorname, String adventurename) {
        ArrayList<EventDTO> instructorAndAdventureTerm = new ArrayList<EventDTO>();
        Instructor instructor = instructorRepository.getByEmail(instructorname);
        if(instructor != null){
            List<InstructorTerms> instructorTerms = instructorTermRepository.findAllByInstructorId(instructor.getId());
            for (InstructorTerms insTerm : instructorTerms) {
                instructorAndAdventureTerm.add(new EventDTO(insTerm.getStartTime(), insTerm.getEndTime(), insTerm.getTermAvailability()));
            }
        }
        Adventure adventure = adventureService.getAdventureByInstructorAndAdventureName(instructorname, adventurename);
        if(adventure != null){
            List<InstructorReservation> instructorReservations = instructorReservationRepository.findAllByAdventureId(adventure.getId());
            for (InstructorReservation insRes : instructorReservations) {
                if(insRes.getStatusOfReservation() == StatusOfReservation.ACTIVE)
                    instructorAndAdventureTerm.add(new EventDTO(insRes.getStartTime(), insRes.getEndTime()));
            }
        }

        return instructorAndAdventureTerm;
    }
}
