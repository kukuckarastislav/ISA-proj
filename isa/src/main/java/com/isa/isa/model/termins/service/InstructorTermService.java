package com.isa.isa.model.termins.service;


import com.isa.isa.model.Adventure;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.complaints.ComplaintRepository;
import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.DTO.InstructorReservationDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.DTO.NewInstructorTermDTO;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private ComplaintRepository complaintRepository;


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
		boolean retVal = false;
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

    public Boolean createTermForInstructor(String username, NewInstructorTermDTO newInstructorTermDTO) {
        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor == null) return false;

        if(overlap(newInstructorTermDTO.getStartTime(), newInstructorTermDTO.getEndTime(), instructor)){
            return false;
        }

        InstructorTerms instructorTerm = new InstructorTerms(instructor, newInstructorTermDTO.getTermAvailability(), newInstructorTermDTO.getStartTime(), newInstructorTermDTO.getEndTime());
        instructorTermRepository.saveAndFlush(instructorTerm);

        return true;
    }



    private Boolean overlap(LocalDateTime startTime, LocalDateTime endTime, Instructor instructor){
        for(InstructorTerms instructorTerm : instructorTermRepository.findAllByInstructorId(instructor.getId())){
            if(instructorTerm.isOverlap(startTime, endTime)){
                System.out.println("Overlaping with Instructor term");
                return true;
            }
        }

        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsername(instructor.getEmail())){
            if(instructorReservation.getStatusOfReservation() == StatusOfReservation.ACTIVE){
                if(instructorReservation.isOverlap(startTime, endTime)){
                    System.out.println("Overlaping with Instructor Reservation");
                    return true;
                }
            }
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsernameWithHistory(instructor.getEmail())){
            if(instructorFastReservation.isOverlap(startTime, endTime)){
                System.out.println("Overlaping with Instructor FAST Reservation");
                return true;
            }
        }

        return false;
    }

    public ArrayList<InstructorReservationDTO> getReservationForInstructor(String username) {
        //TODO: dodati complaintRepository get complaint
        ArrayList<InstructorReservationDTO> instructorReservations = new ArrayList<>();

        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor == null) return instructorReservations;

        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsername(username)){
            instructorReservations.add(new InstructorReservationDTO(instructorReservation));
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsernameWithHistory(username)){
            instructorReservations.add(new InstructorReservationDTO(instructorFastReservation));
        }

        return instructorReservations;
    }

    public InstructorReservationDTO getReservationForInstructorById(String username, TermType termType, int idReservation) {
        //TODO: dodati complaintRepository get complaint
        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor == null) return null;

        if(termType == TermType.RESERVATION){
            Optional<InstructorReservation> instructorReservationOptional = instructorReservationRepository.findById(idReservation);
            if(instructorReservationOptional.isPresent()){
                return new InstructorReservationDTO(instructorReservationOptional.get());
            }
        }else if(termType == TermType.FAST_RESERVATION){
            for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsernameWithHistory(username)){
                if(instructorFastReservation.getInstructorUsername().equals(username)){
                    return new InstructorReservationDTO(instructorFastReservation);
                }
            }
        }

        return null;
    }

    public ArrayList<EventDTO> getTermForAdventureCalendar(String username, int idAdventure) {
        ArrayList<EventDTO> events = new ArrayList<>();

        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor == null) return events;

        for(InstructorTerms instructorTerm : instructorTermRepository.findAllByInstructorId(instructor.getId())){
            events.add(new EventDTO(instructorTerm));
        }

        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsername(username)){
            EventDTO e = new EventDTO(instructorReservation, instructorReservation.getAdventure().getName());
            if(instructorReservation.getAdventure().getId() != idAdventure){
                e.hideData();
            }
            events.add(e);
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsernameWithHistory(username)){
            EventDTO e = new EventDTO(instructorFastReservation, instructorFastReservation.getAdventure().getName());
            if(instructorFastReservation.getAdventure().getId() != idAdventure){
                e.hideData();
            }
            events.add(e);
        }


        return events;
    }
}
