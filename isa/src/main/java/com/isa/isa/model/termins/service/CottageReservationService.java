package com.isa.isa.model.termins.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.isa.isa.model.*;
import com.isa.isa.model.loyalty.LoyaltyService;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.termins.DTO.ClientCottageReservationDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsCottageDTO;
import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.DTO.NewCottageFastReservationDto;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.model.CottageReservations;
import com.isa.isa.model.termins.model.CottageTerms;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.CottageFastReservationRepository;
import com.isa.isa.model.termins.repository.CottageReservationRepository;
import com.isa.isa.model.termins.repository.CottageTermRepository;
import com.isa.isa.repository.CottageRepository;

@Service
public class CottageReservationService {

	@Autowired
    private CottageReservationRepository cottageReservationRepository;
	@Autowired
    private CottageTermRepository cottageTermRepository;
	@Autowired
	private CottageRepository cottageRepository;
	@Autowired
	private CottageOwnerRepository cottageOwnerRepository;
	@Autowired
	private CottageFastReservationRepository cottageFastReservationRepository;
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private LoyaltyService loyaltyService;
	
	public Boolean isCottageFree(CottageTermsDTO dto) {
		ArrayList<CottageReservations> reservations =  (ArrayList<CottageReservations>) cottageReservationRepository.findAllByCottageId(dto.getId());
		Boolean retVal = true;
		for(CottageReservations reserv : reservations) {
			if(reserv.getStatusOfReservation()==StatusOfReservation.ACTIVE && (reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}

	public CottageReservations reserveCottageByClient(ClientCottageReservationDTO dto, Client client) {
    	if(hasClientAlreadyCancelledCottage(dto, client)) return null;
		CottageOwner cottageOwner = cottageOwnerRepository.getByEmail(dto.getCottage().getOwner().getEmail());
		CottageReservations cottageReservations = new CottageReservations(client, dto.getCottage(), dto.getStartTime(), dto.getEndTime());
		cottageReservations.setAdditionalServices(dto.getAdditionalServices());
		cottageReservations.setStatusOfReservation(StatusOfReservation.ACTIVE);
		cottageReservations.setPrice(calculatePrice(dto, client));
		cottageReservations.setIncome(loyaltyService.calculateIncome(cottageOwner, cottageReservations.getPrice()));
		loyaltyService.applyReward(client);
		clientRepository.saveAndFlush(client);
		loyaltyService.applyReward(cottageOwner);
		cottageOwnerRepository.saveAndFlush(cottageOwner);
    	return cottageReservationRepository.save(cottageReservations);
	}
	
	private Boolean hasClientAlreadyCancelledCottage(ClientCottageReservationDTO dto, Client client) {
    	List<CottageReservations> reservations = cottageReservationRepository.findAllByClientIdAndCottageId(client.getId(),dto.getCottage().getId());
    	for(CottageReservations reserv : reservations) {
			if(reserv.getStatusOfReservation()==StatusOfReservation.CANCELLED && (reserv.getStartTime().isBefore(dto.getEndTime()) && reserv.getEndTime().isAfter( dto.getStartTime()))) {
				return true;
			}
		}
		
    	return false;
    }

	private double calculatePrice(ClientCottageReservationDTO dto, Client client) {
    	double price=0;
    	Long days = ChronoUnit.DAYS.between(dto.getStartTime(),dto.getEndTime());
    	price += days * dto.getCottage().getPrice().getPrice();
    	for(ItemPrice itemprice : dto.getAdditionalServices()) {
    		price+= itemprice.getPrice();
    	}
    	return loyaltyService.applyDiscount(client, price);
    }
	
	
	public List<ClientMadeReservationsCottageDTO> getCottageReservationByClient(int clientId){	
		List<CottageReservations> cottageReservations = cottageReservationRepository.findAllByClientId(clientId);
		List<ClientMadeReservationsCottageDTO>retVal = new ArrayList<ClientMadeReservationsCottageDTO>();
    	for(CottageReservations reservation:cottageReservations) {
    		List<EntityImage>images = new ArrayList<> (reservation.getCottage().getImages());
    		retVal.add(new ClientMadeReservationsCottageDTO(reservation.getStartTime(),reservation.getEndTime(),reservation.getCottage().getId(),reservation.getAdditionalServices(),reservation.getPrice(),reservation.getCottage().getName(),images.get(0).getPath(),reservation.getStatusOfReservation().name(),false,reservation.getId(),reservation.getIsRevised(),reservation.getIsComplainedOf()));
    	}
    	return retVal;
    }
	
	public void cancelReservation(int reservationId) {
    	Optional<CottageReservations> cottageReservationType= cottageReservationRepository.findById(reservationId);
    	if(cottageReservationType.isPresent()) {
    		CottageReservations cottageReservations = cottageReservationType.get();
    		cottageReservations.setStatusOfReservation(StatusOfReservation.CANCELLED);
    		cottageReservationRepository.save(cottageReservations);
    	}
    }
	
	 public void addRevision(int reservationId) {
		 CottageReservations cottageReservations = cottageReservationRepository.findById(reservationId).get();
		 cottageReservations.setIsRevised(true);
		 cottageReservationRepository.saveAndFlush(cottageReservations);
	 }
	 
	 public void addComplaint(int reservationId) {
		 CottageReservations cottageReservations = cottageReservationRepository.findById(reservationId).get();
		 cottageReservations.setIsComplainedOf(true);
		 cottageReservationRepository.saveAndFlush(cottageReservations);
	 }
	 
	 public String defineNewFastReservationForCottage(NewCottageFastReservationDto dto) {
			
			CottageFastReservation newReservation = new CottageFastReservation();
	        Cottage cottage;
	        
	        cottage = cottageRepository.getById(dto.getCottageId());
	        newReservation.setCottage(cottage);
	        newReservation.setStartTime(dto.getStartTime());
	        newReservation.setEndTime(dto.getEndTime());
	        Set<ItemPrice> hSet = new HashSet<ItemPrice>();
	        for (ItemPrice x : dto.getAdditionalServices())
	            hSet.add(x);
	        newReservation.setAdditionalServices(hSet);
	        newReservation.setAddress(dto.getAddress());
	        newReservation.setMaxNumberOfPeople(dto.getCapasity());
	        newReservation.setPrice(dto.getPrice());
	        
	        if(isCottageFastReservationOverlapedWithExistingCottageTerms(getAllTermsForCottage(cottage.getId()),newReservation))
	        {
	        	return "Cannot define new fast reseravation because it is overlaped with another term.";
	        }
	        if(isCottageFastReservationOverlapedWithExistingCottageReservation(cottageReservationRepository.findAllByCottageId(cottage.getId()),newReservation))
	        {
	        	return "Cannot define new fast reseravation because it is overlaped with another reservation.";
	        }
	        if(isCottageFastReservationOverlapedWithExistingCottageFastReservation(cottageFastReservationRepository.findAllByCottageId(cottage.getId()),newReservation))
	        {
	        	return "Cannot define new fast reseravation because it is overlaped with another reservation.";
	        }
	        
	        cottageFastReservationRepository.save(newReservation);
	        
	        return "Cottage reservation successfully defined.";
		}
		
		public List<CottageTerms> getAllTermsForCottage(int cottageId) {
			List<CottageTerms> allCottageTerms = cottageTermRepository.findAllByCottageId(cottageId);
	        return allCottageTerms;
	    }
		
		public boolean isCottageFastReservationOverlapedWithExistingCottageTerms(List<CottageTerms> cottageTerms, CottageFastReservation newReservation) {
		      for (CottageTerms term : cottageTerms)
		         if (term.getStartTime().isBefore(newReservation.getStartTime()) && term.getEndTime().isAfter(newReservation.getEndTime()))
		            return true;

		      return false;
		}
		
		public boolean isCottageFastReservationOverlapedWithExistingCottageReservation(List<CottageReservations> cottageReservations, CottageFastReservation newReservation) {
		      for (CottageReservations reservation : cottageReservations)
		         if (reservation.getStartTime().isBefore(newReservation.getStartTime()) && reservation.getEndTime().isAfter(newReservation.getEndTime()))
		            return true;

		      return false;
		}
		
		public boolean isCottageFastReservationOverlapedWithExistingCottageFastReservation(List<CottageFastReservation> cottageReservations, CottageFastReservation newReservation) {
		      for (CottageFastReservation reservation : cottageReservations)
		         if (reservation.getStartTime().isBefore(newReservation.getStartTime()) && reservation.getEndTime().isAfter(newReservation.getEndTime()))
		            return true;
		
		      return false;
		}
}
