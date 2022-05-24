package com.isa.isa.model.termins.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Client;
import com.isa.isa.model.EntityImage;
import com.isa.isa.model.ItemPrice;
import com.isa.isa.model.termins.DTO.ClientCottageReservationDTO;
import com.isa.isa.model.termins.DTO.ClientMadeReservationsCottageDTO;
import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.model.CottageReservations;
import com.isa.isa.model.termins.model.StatusOfReservation;
import com.isa.isa.model.termins.repository.CottageReservationRepository;

@Service
public class CottageReservationService {

	@Autowired
    private CottageReservationRepository cottageReservationRepository;
	
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
		CottageReservations cottageReservations = new CottageReservations(client, dto.getCottage(), dto.getStartTime(), dto.getEndTime());
		cottageReservations.setAdditionalServices(dto.getAdditionalServices());
		cottageReservations.setStatusOfReservation(StatusOfReservation.ACTIVE);
		cottageReservations.setPrice(calculatePrice(dto));
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
	
	private double calculatePrice(ClientCottageReservationDTO dto) {
    	double price=0;
    	Long days = ChronoUnit.DAYS.between(dto.getStartTime(),dto.getEndTime());
    	price += days * dto.getCottage().getPrice().getPrice();
    	for(ItemPrice itemprice : dto.getAdditionalServices()) {
    		price+= itemprice.getPrice();
    	}
    	return price;
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
}
