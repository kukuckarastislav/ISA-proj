package com.isa.isa.model.termins.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.isa.isa.model.*;
import com.isa.isa.model.termins.DTO.EventDTO;
import com.isa.isa.model.termins.DTO.NewEntityTermDTO;
import com.isa.isa.model.termins.model.*;
import com.isa.isa.repository.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.DTO.NewCottageTermDto;
import com.isa.isa.model.termins.repository.CottageFastReservationRepository;
import com.isa.isa.model.termins.repository.CottageReservationRepository;
import com.isa.isa.model.termins.repository.CottageTermRepository;
import com.isa.isa.repository.CottageRepository;

@Service
public class CottageTermService {

	@Autowired
	private CottageOwnerRepository cottageOwnerRepository;

	@Autowired
    private CottageTermRepository cottageTermRepository;
	@Autowired
	private CottageRepository cottageRepository;
	@Autowired
	private CottageReservationRepository cottageReservationRepository;
	@Autowired
	private CottageFastReservationRepository cottageFastReservationRepository;
	
	public Boolean isCottageFree(CottageTermsDTO dto) {
		ArrayList<CottageTerms> terms = (ArrayList<CottageTerms>) cottageTermRepository.findAllByCottageId(dto.getId());
		Boolean retVal = false;
		for(CottageTerms term : terms) {
			if(term.getTermAvailability()==TermAvailability.AVAILABILE && term.getStartTime().isBefore(dto.getStartTime()) && term.getEndTime().isAfter( dto.getEndTime())) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
	
	
	public String defineNewTermForCottage(NewCottageTermDto newCottageTermDto) {
        CottageTerms newTerm = new CottageTerms(newCottageTermDto.getStartTime(), newCottageTermDto.getEndTime());
        Cottage cottage;
        
        cottage = cottageRepository.getById(newCottageTermDto.getBoatId());
        newTerm.setCottage(cottage);
        newTerm.setStartTime(newCottageTermDto.getStartTime());
        newTerm.setEndTime(newCottageTermDto.getEndTime());
        newTerm.setTermAvailability(newCottageTermDto.getTermAvailability());
        
        if(isCottageTermOverlapedWithExistingCottageTerms(getAllTermsForCottage(cottage.getId()),newTerm))
        {
        	return "Cannot define new term because it is overlaped with another term.";
        }
        if(isCottageTermOverlapedWithExistingCottageReservation(cottageReservationRepository.findAllByCottageId(cottage.getId()),newTerm))
        {
        	return "Cannot define new term because it is overlaped with another reservation.";
        }
        if(isCottageTermOverlapedWithExistingCottageFastReservation(cottageFastReservationRepository.findAllByCottageId(cottage.getId()),newTerm))
        {
        	return "Cannot define new term because it is overlaped with another reservation.";
        }
        
        cottageTermRepository.save(newTerm);
        
        return "Cottage term successfully defined.";
	}

	public List<CottageTerms> getAllTermsForCottage(int cottageId) {
		List<CottageTerms> allCottageTerms = cottageTermRepository.findAllByCottageId(cottageId);
	    return allCottageTerms;
	}
	
	public boolean isCottageTermOverlapedWithExistingCottageTerms(List<CottageTerms> cottageTerms, CottageTerms newCottageTerm) {
	      for (CottageTerms term : cottageTerms)
	         if (term.getStartTime().isBefore(newCottageTerm.getStartTime()) && term.getEndTime().isAfter(newCottageTerm.getEndTime()))
	            return true;
	
	      return false;
	}
	
	public boolean isCottageTermOverlapedWithExistingCottageReservation(List<CottageReservations> cottageReservations, CottageTerms newCottageTerm) {
	      for (CottageReservations reservation : cottageReservations)
	         if (reservation.getStartTime().isBefore(newCottageTerm.getStartTime()) && reservation.getEndTime().isAfter(newCottageTerm.getEndTime()))
	            return true;
	
	      return false;
	}
	
	public boolean isCottageTermOverlapedWithExistingCottageFastReservation(List<CottageFastReservation> cottageReservations, CottageTerms newCottageTerm) {
	      for (CottageFastReservation reservation : cottageReservations)
	         if (reservation.getStartTime().isBefore(newCottageTerm.getStartTime()) && reservation.getEndTime().isAfter(newCottageTerm.getEndTime()))
	            return true;
	
	      return false;
	}

    public ArrayList<EventDTO> getTermForCottageCalendar(String username, int idCottage) {
		ArrayList<EventDTO> events = new ArrayList<>();

		CottageOwner cottageOwner = cottageOwnerRepository.getByEmail(username);
		if(cottageOwner == null) return events;

		for(CottageTerms cottageTerm : cottageTermRepository.findAllByCottageId(idCottage)){
			events.add(new EventDTO(cottageTerm));
		}

		for(CottageReservations cottageReservation : cottageReservationRepository.findAllByCottageId(idCottage)){
			events.add(new EventDTO(cottageReservation, cottageReservation.getClient().getFullName()));
		}

		for(CottageFastReservation cottageFastReservation : cottageFastReservationRepository.findAllByCottageId(idCottage)){
			String title = "";
			Client client = cottageFastReservation.getClientWhoTake();
			if(client != null)
				title = client.getFullName();
			events.add(new EventDTO(cottageFastReservation,title));
		}

		return events;
    }

	public ArrayList<EventDTO> getTermForCottageOwnerCalendar(String username) {
		ArrayList<EventDTO> events = new ArrayList<>();

		CottageOwner cottageOwner = cottageOwnerRepository.getByEmailWithCottages(username);
		if(cottageOwner == null) return events;

		for(Cottage cottage : cottageOwner.getCottages()){
			for(CottageReservations cottageReservation : cottageReservationRepository.findAllByCottageId(cottage.getId())){
				String title = cottageReservation.getCottage().getName() + " - " + cottageReservation.getClient().getFullName();
				events.add(new EventDTO(cottageReservation, title));
			}

			for(CottageFastReservation cottageFastReservation : cottageFastReservationRepository.findAllByCottageId(cottage.getId())){
				String title = cottageFastReservation.getCottage().getName();
				Client client = cottageFastReservation.getClientWhoTake();
				if(client != null)
					title += " - " + client.getFullName();
				events.add(new EventDTO(cottageFastReservation,title));
			}

		}



		return events;
	}

	public Boolean createTermForCottage(String username, NewEntityTermDTO newEntityTermDTO) {
		CottageOwner cottageOwner = cottageOwnerRepository.getByEmail(username);
		if(cottageOwner == null) return false;

		Optional<Cottage> cottageOptional = cottageRepository.findById(newEntityTermDTO.getIdEntity());
		if(cottageOptional.isEmpty()) return false;
		Cottage cottage = cottageOptional.get();

		if(overlap(newEntityTermDTO.getStartTime(), newEntityTermDTO.getEndTime(), cottage)){
			return false;
		}

		CottageTerms cottageTerm = new CottageTerms(cottage, newEntityTermDTO.getTermAvailability(), newEntityTermDTO.getStartTime(), newEntityTermDTO.getEndTime());
		cottageTermRepository.saveAndFlush(cottageTerm);

		return true;
	}

	private Boolean overlap(LocalDateTime startTime, LocalDateTime endTime, Cottage cottage){
		for(CottageTerms cottageTerm : cottageTermRepository.findAllByCottageId(cottage.getId())){
			if(cottageTerm.isOverlap(startTime, endTime)){
				System.out.println("Overlaping with Cottage term");
				return true;
			}
		}

		for(CottageReservations cottageReservation : cottageReservationRepository.findAllByCottageId(cottage.getId())){
			if(cottageReservation.getStatusOfReservation() == StatusOfReservation.ACTIVE){
				if(cottageReservation.isOverlap(startTime, endTime)){
					System.out.println("Overlaping with Cottage Reservation");
					return true;
				}
			}
		}

		for(CottageFastReservation cottageFastReservation : cottageFastReservationRepository.findAllByCottageId(cottage.getId())){
			if(cottageFastReservation.isOverlap(startTime, endTime)){
				System.out.println("Overlaping with Cottage FAST Reservation");
				return true;
			}
		}


		return false;
	}

    public double getSystemIncome(CottageOwner cottageOwner, TimeStamp timeStamp) {
		double systemIncome = 0;

		if(cottageOwner == null) return 0;

		CottageOwner cottageOwnerWithBoats = cottageOwnerRepository.getByEmailWithCottages(cottageOwner.getEmail());
		if(cottageOwnerWithBoats == null) return 0;

		for(Cottage cottage : cottageOwnerWithBoats.getCottages()){
			for(CottageReservations cottageReservations : cottageReservationRepository.findAllByCottageId(cottage.getId())){
				if(cottageReservations.isSuccessfullyFinished()){
					if(timeStamp.inStamp(cottageReservations.getStartTime(), cottageReservations.getEndTime())){
						systemIncome += cottageReservations.getPrice() - cottageReservations.getIncome();
					}
				}
			}

			for(CottageFastReservation cottageFastReservation : cottageFastReservationRepository.findAllByCottageId(cottage.getId())){
				CottageFastResHistory cottageFastResHistory = cottageFastReservation.getSuccessfullyFinishedHistory();
				if(cottageFastResHistory != null){
					if(timeStamp.inStamp(cottageFastReservation.getStartTime(), cottageFastReservation.getEndTime())){
						systemIncome += cottageFastResHistory.getPrice() - cottageFastResHistory.getIncome();
					}
				}
			}
		}

		return systemIncome;
    }
}
