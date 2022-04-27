package com.isa.isa.security.service;

import com.isa.isa.DTO.UserApproveDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Client;
import com.isa.isa.model.termins.DTO.ClientAdventureFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageReservationDTO;

import org.springframework.mail.MailException;

import com.isa.isa.security.model.User;

public interface EmailService {
	void sendNotificaition(User user) throws MailException, InterruptedException;

    void sendNotificaitionApproved(User user) throws MailException, InterruptedException;

    void sendNotificaitionRejected(User user, UserApproveDTO userApproveDTO) throws MailException, InterruptedException;
    
    void sendReservationConfirmation(Client client, ClientAdventureReservationDTO clientAdventureReservationDTO) throws MailException, InterruptedException;
    
    public void sendAdventureActionReservationConfirmation(Client client, Adventure adventure, ClientAdventureFastReservationDTO clientAdventureReservationDTO) throws MailException, InterruptedException;
    
    void sendCottageReservationConfirmation(Client client, ClientCottageReservationDTO clientCottageReservationDTO) throws MailException, InterruptedException;
}
