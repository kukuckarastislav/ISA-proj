package com.isa.isa.security.service;

import com.isa.isa.DTO.UserApproveDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.report.model.Report;
import com.isa.isa.model.revisions.model.Revision;
import com.isa.isa.model.termins.DTO.ClientAdventureFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.DTO.ClientBoatFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientBoatReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageFastReservationDTO;
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
    
    void sendCottageActionReservationConfirmation(Client client, Cottage cottage, ClientCottageFastReservationDTO clientCottageFastReservationDTO) throws MailException, InterruptedException;
    
    void sendBoatReservationConfirmation(Client client, ClientBoatReservationDTO clientBoatReservationDTO) throws MailException, InterruptedException;
    
    void sendBoatActionReservationConfirmation(Client client, Boat boat, ClientBoatFastReservationDTO clientBoatFastReservationDTO) throws MailException, InterruptedException;

    void sendNotificationNewRevisionEntity(String email, String clientEmail, Revision revision, String offerName);

    void sendNotificationNewRevisionOwnerInstructor(String email, String clientEmail, Revision revision);

    void sendAdminComplaintResponseToClient(Complaint complaint, String offerName);

    void sendAdminComplaintResponseToProvider(Complaint complaint, String offerName);

    void sendReportNotificationToClient(Report report, String reservationName);

    void sendReportNotificationToOwner(Report report, String reservationName);
}
