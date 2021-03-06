package com.isa.isa.model.termins.service;


import com.isa.isa.DTO.InstructorBusinessReportDTO;
import com.isa.isa.DTO.InstructorDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Client;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.TimeStamp;
import com.isa.isa.model.complaints.ComplaintRepository;
import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.enums.IsaEntityType;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.loyalty.BusinessStatistic;
import com.isa.isa.model.loyalty.LoyaltyService;
import com.isa.isa.model.termins.DTO.*;
import com.isa.isa.model.termins.model.*;
import com.isa.isa.model.termins.repository.InsFastResHistoryRepository;
import com.isa.isa.model.termins.repository.InstructorFastReservationRepository;
import com.isa.isa.model.termins.repository.InstructorReservationRepository;
import com.isa.isa.model.termins.repository.InstructorTermRepository;
import com.isa.isa.repository.AdventureRepository;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.InstructorRepository;
import com.isa.isa.security.service.EmailService;
import com.isa.isa.service.AdventureService;
import com.isa.isa.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Autowired
    private ClientService clientService;

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
            String title = instructorReservation.getAdventure().getName() + " - " + instructorReservation.getClient().getFullName();
            events.add(new EventDTO(instructorReservation, title));
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsername(username)){
            String title = instructorFastReservation.getAdventure().getName();
            Client client = instructorFastReservation.getClientWhoTake();
            if(client != null)
                title += " - " + client.getFullName();
            events.add(new EventDTO(instructorFastReservation, title));
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
        if (overlapWithInstructorTerms(startTime, endTime, instructor)) return true;
        if (overlapWithReservation(startTime, endTime, instructor)) return true;
        if (overlapWithFastReservation(startTime, endTime, instructor)) return true;
        return false;
    }

    private boolean overlapWithInstructorTerms(LocalDateTime startTime, LocalDateTime endTime, Instructor instructor) {
        for(InstructorTerms instructorTerm : instructorTermRepository.findAllByInstructorId(instructor.getId())){
            if(instructorTerm.isOverlap(startTime, endTime)){
                System.out.println("Overlaping with Instructor term");
                return true;
            }
        }
        return false;
    }

    private boolean overlapWithReservation(LocalDateTime startTime, LocalDateTime endTime, Instructor instructor) {
        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsername(instructor.getEmail())){
            if(instructorReservation.getStatusOfReservation() == StatusOfReservation.ACTIVE){
                if(instructorReservation.isOverlap(startTime, endTime)){
                    System.out.println("Overlaping with Instructor Reservation");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean overlapWithFastReservation(LocalDateTime startTime, LocalDateTime endTime, Instructor instructor) {
        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsername(instructor.getEmail())){
            if(instructorFastReservation.isOverlap(startTime, endTime)){
                System.out.println("Overlaping with Instructor FAST Reservation");
                return true;
            }
        }
        return false;
    }

    private boolean isReservationPossible(LocalDateTime startTime, LocalDateTime endTime, Instructor instructor){
        return isFastReservationPossible(startTime, endTime, instructor);
    }

    private boolean isFastReservationPossible(LocalDateTime startTime, LocalDateTime endTime, Instructor instructor){
        if(overlapWithInstructorTermsUNAVAILABLE(startTime, endTime, instructor)) return false;
        if (overlapWithReservation(startTime, endTime, instructor)) return false;
        if (overlapWithFastReservation(startTime, endTime, instructor)) return false;
        return true;
    }

    private boolean overlapWithInstructorTermsUNAVAILABLE(LocalDateTime startTime, LocalDateTime endTime, Instructor instructor) {
        for(InstructorTerms instructorTerm : instructorTermRepository.findAllByInstructorId(instructor.getId())){
            if(instructorTerm.getTermAvailability() == TermAvailability.UNAVAILABLE){
                if(instructorTerm.isOverlap(startTime, endTime)){
                    System.out.println("Overlaping with Instructor UNAVAILABLE term");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean overlapWithAVAILABLEAndNotWithUNAVAILABLE(LocalDateTime startTime, LocalDateTime endTime, Instructor instructor) {
        boolean overlapWithAVAILABLE = false;
        for(InstructorTerms instructorTerm : instructorTermRepository.findAllByInstructorId(instructor.getId())){
            if(instructorTerm.isOverlap(startTime, endTime)){
                if(instructorTerm.getTermAvailability() == TermAvailability.UNAVAILABLE){
                    System.out.println("Overlaping with Instructor term");
                    return true;
                }else{
                    overlapWithAVAILABLE = true;
                }
            }
        }
        return overlapWithAVAILABLE;
    }

    @Autowired
    private ClientRepository clientRepository;

    public ArrayList<InstructorReservationDTO> getReservationForInstructor(String username) {
        //TODO: dodati complaintRepository get complaint
        ArrayList<InstructorReservationDTO> instructorReservations = new ArrayList<>();

        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor == null) return instructorReservations;

        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsername(username)){
            Client client = clientRepository.findByEmail(instructorReservation.getClient().getEmail());
            Complaint complaint = client.getComplaintByReservationId(instructorReservation.getId(), UserTypeISA.INSTRUCTOR, false);
            InstructorReservationDTO instructorReservationDTO = new InstructorReservationDTO(instructorReservation);
            instructorReservationDTO.setInstructorComplaint(complaint);
            instructorReservations.add(instructorReservationDTO);
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsername(username)){
            InstructorReservationDTO instructorReservationDTO = new InstructorReservationDTO(instructorFastReservation);
            Client client = instructorFastReservation.getClientWhoTake();
            if(client != null){
                Complaint complaint = client.getComplaintByReservationId(instructorFastReservation.getId(), UserTypeISA.INSTRUCTOR, true);
                instructorReservationDTO.setInstructorComplaint(complaint);
            }
            instructorReservations.add(instructorReservationDTO);
        }

        return instructorReservations;
    }

    public InstructorReservationDTO getReservationForInstructorById(String username, TermType termType, int idReservation) {
        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor == null) return null;

        if(termType == TermType.RESERVATION){
            Optional<InstructorReservation> instructorReservationOptional = instructorReservationRepository.findById(idReservation);
            if(instructorReservationOptional.isPresent()){
                InstructorReservation instructorReservation = instructorReservationOptional.get();
                //return new InstructorReservationDTO(instructorReservationOptional.get());
                Client client = clientRepository.findByEmail(instructorReservation.getClient().getEmail());
                Complaint complaint = client.getComplaintByReservationId(instructorReservation.getId(), UserTypeISA.INSTRUCTOR, false);
                InstructorReservationDTO instructorReservationDTO = new InstructorReservationDTO(instructorReservation);
                instructorReservationDTO.setInstructorComplaint(complaint);
                return instructorReservationDTO;
            }
        }else if(termType == TermType.FAST_RESERVATION){
            Optional<InstructorFastReservation> instructorFastReservationOptional = instructorFastReservationRepository.findById(idReservation);
            if(instructorFastReservationOptional.isEmpty()) return null;
            InstructorFastReservation instructorFastReservation = instructorFastReservationOptional.get();
            InstructorReservationDTO instructorReservationDTO = new InstructorReservationDTO(instructorFastReservation);
            Client client = instructorFastReservation.getClientWhoTake();
            if(client != null){
                Complaint complaint = client.getComplaintByReservationId(instructorFastReservation.getId(), UserTypeISA.INSTRUCTOR, true);
                instructorReservationDTO.setInstructorComplaint(complaint);
            }
            return instructorReservationDTO;
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
            String title = instructorReservation.getAdventure().getName() + " - " + instructorReservation.getClient().getFullName();
            EventDTO e = new EventDTO(instructorReservation, title);
            if(instructorReservation.getAdventure().getId() != idAdventure){
                e.hideData();
            }
            events.add(e);
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsername(username)){
            String title = instructorFastReservation.getAdventure().getName();
            Client client = instructorFastReservation.getClientWhoTake();
            if(client != null)
                title += " - " + client.getFullName();
            EventDTO e = new EventDTO(instructorFastReservation, title);
            if(instructorFastReservation.getAdventure().getId() != idAdventure){
                e.hideData();
            }
            events.add(e);
        }


        return events;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String createFastReservation(String username, NewInstructorFastReservationDTO newInstructorFastReservationDTO) {

        Instructor instructor = null;
        try {
            instructor = instructorRepository.getByUsername(username);
            if(instructor == null) return "error: instructor null";
        }catch (PessimisticLockingFailureException e){
            System.out.println("error PessimisticLockingFailureException");
            return "error PessimisticLockingFailureException";
        }

        Adventure adventure = adventureRepository.getAdventureById(newInstructorFastReservationDTO.getIdAdventure());
        if(adventure == null) return "error: adventure does not exists";

        if(isFastReservationPossible(newInstructorFastReservationDTO.getStartTime(), newInstructorFastReservationDTO.getEndTime(), instructor)){
            InstructorFastReservation ifr = new InstructorFastReservation(adventure, newInstructorFastReservationDTO, username);
            instructorFastReservationRepository.saveAndFlush(ifr);
            clientService.notifyAdventureSubscribedClients(ifr);
            return "ok";
        }else{
            return "error: overlaping with other terms";
        }
    }

    public boolean updatePossible(Adventure adventure, String username) {
        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsernameAndAdventureId(username, adventure.getId())){
            if(instructorReservation.getStatusOfReservation() == StatusOfReservation.ACTIVE){
                if(instructorReservation.getEndTime().isAfter(LocalDateTime.now())){
                    return false;
                }
            }
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsernameAndAdventureId(username, adventure.getId())){
            if(instructorFastReservation.getEndTime().isAfter(LocalDateTime.now())){
                return false;
            }
        }

        return true;
    }

    @Autowired
    private LoyaltyService loyaltyService;

    public InstructorBusinessReportDTO getBusinessReport(String username, TimeStamp timeStamp) {
        InstructorBusinessReportDTO instructorBusinessReport = new InstructorBusinessReportDTO();

        Instructor instructor = instructorRepository.getByEmail(username);
        if(instructor == null) return null;

        instructorBusinessReport.setInstructorDTO(new InstructorDTO(instructor));
        instructorBusinessReport.setLoyaltySettings(loyaltyService.getLoyaltySettings());

        for(Adventure adventure : adventureRepository.getByInstructorId(instructor.getId())){
            instructorBusinessReport.addBusinessStatistic(new BusinessStatistic(adventure.getName(), adventure.getAverageGrade(), timeStamp.getStartTime(), timeStamp.getEndTime()));
        }

        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsername(username)){
            if(instructorReservation.isSuccessfullyFinished()){
                BusinessStatistic businessStatistic = instructorBusinessReport.getBusinessStatisticByEntityName(instructorReservation.getAdventure().getName());
                if(businessStatistic == null){
                    businessStatistic = new BusinessStatistic(instructorReservation.getAdventure().getName(), instructorReservation.getAdventure().getAverageGrade(), timeStamp.getStartTime(), timeStamp.getEndTime());
                }
                if(timeStamp.inStamp(instructorReservation.getStartTime(), instructorReservation.getEndTime())){
                    businessStatistic.addIncome(instructorReservation.getIncome());
                }
                if(instructorReservation.getEndTime().isAfter(LocalDateTime.now().minusDays(7))){
                    businessStatistic.incNumOfResInWeek();
                }
                if(instructorReservation.getEndTime().isAfter(LocalDateTime.now().minusMonths(1))){
                    businessStatistic.incNumOfResInMonth();
                }
                if(instructorReservation.getEndTime().isAfter(LocalDateTime.now().minusMonths(12))){
                    businessStatistic.incNumOfResInYear();
                }

                instructorBusinessReport.addBusinessStatistic(businessStatistic);
            }
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsername(username)){
            InsFastResHistory insFastResHistory = instructorFastReservation.getSuccessfullyFinishedHistory();
            if(insFastResHistory != null){
                BusinessStatistic businessStatistic = instructorBusinessReport.getBusinessStatisticByEntityName(instructorFastReservation.getAdventure().getName());
                if(businessStatistic == null){
                    businessStatistic = new BusinessStatistic(instructorFastReservation.getAdventure().getName(), instructorFastReservation.getAdventure().getAverageGrade(), timeStamp.getStartTime(), timeStamp.getEndTime());
                }
                if(timeStamp.inStamp(instructorFastReservation.getStartTime(), instructorFastReservation.getEndTime())){
                    businessStatistic.addIncome(insFastResHistory.getIncome());
                }
                if(instructorFastReservation.getEndTime().isAfter(LocalDateTime.now().minusDays(7))){
                    businessStatistic.incNumOfResInWeek();
                }
                if(instructorFastReservation.getEndTime().isAfter(LocalDateTime.now().minusMonths(1))){
                    businessStatistic.incNumOfResInMonth();
                }
                if(instructorFastReservation.getEndTime().isAfter(LocalDateTime.now().minusMonths(12))){
                    businessStatistic.incNumOfResInYear();
                }

                instructorBusinessReport.addBusinessStatistic(businessStatistic);
            }
        }

        return instructorBusinessReport;
    }

    public double getSystemIncome(Instructor instructor, TimeStamp timeStamp) {
        double systemIncome = 0;

        if(instructor == null) return 0;

        for(InstructorReservation instructorReservation : instructorReservationRepository.getByInstructorUsername(instructor.getEmail())){
            if(instructorReservation.isSuccessfullyFinished()){
                if(timeStamp.inStamp(instructorReservation.getStartTime(), instructorReservation.getEndTime())){
                    systemIncome += instructorReservation.getPrice() - instructorReservation.getIncome();
                }
            }
        }

        for(InstructorFastReservation instructorFastReservation : instructorFastReservationRepository.getByInstructorUsername(instructor.getEmail())){
            InsFastResHistory insFastResHistory = instructorFastReservation.getSuccessfullyFinishedHistory();
            if(insFastResHistory != null){
                if(timeStamp.inStamp(instructorFastReservation.getStartTime(), instructorFastReservation.getEndTime())){
                    systemIncome += insFastResHistory.getPrice() - insFastResHistory.getIncome();
                }
            }
        }

        return systemIncome;
    }

    @Autowired
    private EmailService emailService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String createReservationAgain(String username, ReservationAgainDTO reservationAgainDTO) {

        Instructor instructor = null;
        try {
            instructor = instructorRepository.getByUsername(username);
            if(instructor == null) return "error: instructor null";
        }catch (PessimisticLockingFailureException e){
            System.out.println("error PessimisticLockingFailureException");
            return "error PessimisticLockingFailureException";
        }


        Adventure adventure = adventureRepository.getAdventureById(reservationAgainDTO.getIdEntity());
        if(adventure == null) return "error: adventure does not exists";

        Client client = clientRepository.findByEmail(reservationAgainDTO.getClientEmail());
        if(client == null) return "error client not found";

        if(reservationAgainDTO.isFast()){
            Optional<InstructorFastReservation> instructorFastReservationOptional = instructorFastReservationRepository.findById(reservationAgainDTO.getIdReservation());
            if(instructorFastReservationOptional.isEmpty()) return "error reservation do not exist";
            InstructorFastReservation instructorFastReservation = instructorFastReservationOptional.get();
            if(!instructorFastReservation.inThisMoment()) return "error not in period of reservation";
        }else{
            Optional<InstructorReservation> instructorReservationOptional = instructorReservationRepository.findById(reservationAgainDTO.getIdReservation());
            if(instructorReservationOptional.isEmpty()) return "error reservation do not exist";
            InstructorReservation instructorReservation = instructorReservationOptional.get();
            if(!instructorReservation.inThisMoment()) return "error not in period of reservation";
        }

        if(!isReservationPossible(reservationAgainDTO.getStartTime(), reservationAgainDTO.getEndTime(), instructor)) return "error overlap with instructor terms";

        InstructorReservation newInstructorReservation = new InstructorReservation();
        newInstructorReservation.setClient(client);
        newInstructorReservation.setAdventure(adventure);
        newInstructorReservation.setAdditionalServices(new HashSet<>(reservationAgainDTO.getItemPrices()));
        newInstructorReservation.setStartTime(reservationAgainDTO.getStartTime());
        newInstructorReservation.setEndTime(reservationAgainDTO.getEndTime());
        newInstructorReservation.setStatusOfReservation(StatusOfReservation.ACTIVE);
        newInstructorReservation.setInstructorUsername(instructor.getEmail());
        newInstructorReservation.setPrice(loyaltyService.applyDiscount(client, reservationAgainDTO.getPrice()));
        newInstructorReservation.setIncome(loyaltyService.calculateIncome(instructor, newInstructorReservation.getPrice()));
        newInstructorReservation.setIsRevised(false);
        newInstructorReservation.setIsComplainedOf(false);
        loyaltyService.applyReward(client);
        loyaltyService.applyReward(instructor);
        clientRepository.saveAndFlush(client);
        instructorRepository.saveAndFlush(instructor);
        instructorReservationRepository.saveAndFlush(newInstructorReservation);


        emailService.sendAgainReservationConfirmation(client, adventure.getName(), IsaEntityType.ADVENTURE, instructor.getEmail(),reservationAgainDTO.getStartTime(), reservationAgainDTO.getEndTime() );


        return "successfully rebooked";
    }
}
