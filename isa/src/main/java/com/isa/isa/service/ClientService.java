package com.isa.isa.service;

import com.isa.isa.model.loyalty.LoyaltySettings;
import com.isa.isa.model.termins.model.BoatFastReservation;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.model.InstructorFastReservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;
import com.isa.isa.security.service.EmailService;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Client save(UserDTO userDTO) {
		Client newClient = new Client(userDTO);
		return clientRepository.saveAndFlush(newClient);
    }
	
	public Client findByEmail(String email) {
		return clientRepository.findByEmail(email);
	}
	
	public Client update(Client client) {
		Client oldClient = findByEmail(client.getEmail());
		oldClient.setAddress(client.getAddress());
		oldClient.setFirstName(client.getFirstName());
		oldClient.setLastName(client.getLastName());
		oldClient.setPhoneNumber(client.getPhoneNumber());
		return clientRepository.save(oldClient);
	}
	
	public Boolean updatePassword(Client client, PasswordDto passwordDto) {
		if (!passwordEncoder.matches(passwordDto.getOldPassword(), client.getPassword())) return false;
		if (!passwordDto.getNewPassword().equals(passwordDto.getNewPasswordRepeated())) return false;
		client.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		clientRepository.save(client);
		User user = userRepository.findByUsername(client.getEmail());
		user.setPassword(client.getPassword());
		userRepository.save(user);
		return true;
	}
	
	public void addCottageSubscription(String email, Cottage cottage) {
		Client oldClient = findByEmail(email);
		oldClient.getCottageSubscriptions().add(cottage);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void addBoatSubscription(String email, Boat boat) {
		Client oldClient = findByEmail(email);
		oldClient.getBoatSubscriptions().add(boat);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void addAdventureSubscription(String email, Adventure adventure) {
		Client oldClient = findByEmail(email);
		oldClient.getAdventureSubscriptions().add(adventure);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void removeCottageSubscription(String email, Cottage cottage) {
		Client oldClient = findByEmail(email);
		oldClient.getCottageSubscriptions().remove(cottage);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void removeBoatSubscription(String email, Boat boat) {
		Client oldClient = findByEmail(email);
		oldClient.getBoatSubscriptions().remove(boat);
		clientRepository.saveAndFlush(oldClient);
	}
	
	public void removeAdventureSubscription(String email, Adventure adventure) {
		Client oldClient = findByEmail(email);
		oldClient.getAdventureSubscriptions().remove(adventure);
		clientRepository.saveAndFlush(oldClient);
	}

    public void updateLoyaltyForAll(LoyaltySettings loyaltySettings) {
		for(Client client : clientRepository.findAll()){
			if(client.getLoyalty().update(loyaltySettings.getMinimumScoreForSILVER(), loyaltySettings.getMinimumScoreForGOLD())){
				clientRepository.saveAndFlush(client);
			}
		}
    }
    
    public void notifyAdventureSubscribedClients(InstructorFastReservation fastReservation){
    	for(Client client : clientRepository.findAll()){
			if(client.getAdventureSubscriptions().contains(fastReservation.getAdventure())) {
				try {
					emailService.sendNewAdventureActionNotification(client, fastReservation);
				} catch (MailException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    public void notifyCottageSubscribedClients(CottageFastReservation fastReservation){
    	for(Client client : clientRepository.findAll()){
			if(client.getCottageSubscriptions().contains(fastReservation.getCottage())) {
				try {
					emailService.sendNewCottageActionNotification(client, fastReservation);
				} catch (MailException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    public void notifyBoatSubscribedClients(BoatFastReservation fastReservation){
    	for(Client client : clientRepository.findAll()){
			if(client.getBoatSubscriptions().contains(fastReservation.getBoat())) {
				try {
					emailService.sendNewBoatActionNotification(client, fastReservation);
				} catch (MailException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
}
