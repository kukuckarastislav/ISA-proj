package com.isa.isa.model.termins.service;


import com.isa.isa.model.Adventure;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.model.*;
import com.isa.isa.model.termins.repository.InsFastResHistoryRepository;
import com.isa.isa.model.termins.repository.InstructorFastReservationRepository;
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
    private InstructorFastReservationRepository instructorFastReservationRepository;

    @Autowired
    private InsFastResHistoryRepository insFastResHistoryRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AdventureService adventureService;


    public ArrayList<EventDTO> getAdventureTerms(String instructorname, String adventurename) {
        ArrayList<EventDTO> instructorAndAdventureTerm = new ArrayList<EventDTO>();
        /*
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
        */
        return instructorAndAdventureTerm;
    }
    
public Boolean isInstructorFree(InstructorTermsDTO dto) {
		ArrayList<InstructorTerms> terms = (ArrayList<InstructorTerms>) instructorTermRepository.findAllByInstructorId(dto.getId());
		Boolean retVal = false;
		for(InstructorTerms term : terms) {
			if(term.getTermAvailability()==TermAvailability.AVAILABILE && term.getStartTime().isBefore(dto.getStartTime()) && term.getEndTime().isAfter( dto.getEndTime())) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}

    public ArrayList<EventDTO> getTermForInstructorCalendar(String username) {
        ArrayList<EventDTO> events = new ArrayList<>();

        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor == null) return events;

        for(InstructorTerms instructorTerm : instructorTermRepository.findAllByInstructorId(instructor.getId())){
            events.add(new EventDTO(instructorTerm));
        }

        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsername(username)){
            events.add(new EventDTO(instructorReservation, instructorReservation.getAdventure().getName()));
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsernameWithHistory(username)){
            events.add(new EventDTO(instructorFastReservation, instructorFastReservation.getAdventure().getName()));
        }

        return events;
    }
}
