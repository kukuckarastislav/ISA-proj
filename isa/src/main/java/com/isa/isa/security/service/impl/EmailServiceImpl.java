package com.isa.isa.security.service.impl;

import com.isa.isa.DTO.UserApproveDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.revisions.model.Revision;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.termins.DTO.ClientAdventureFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
import com.isa.isa.model.termins.DTO.ClientBoatFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientBoatReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageReservationDTO;
import com.isa.isa.security.model.User;
import com.isa.isa.security.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EmailServiceImpl implements EmailService{
	
	 @Autowired
	 private JavaMailSender javaMailSender;


	 @Autowired
	 private Environment env;
	    
	@Override
	public void sendNotificaition(User user) throws MailException, InterruptedException{
	    SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getUsername());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Confirm you acc ISA Proj");
        String link = "<a href=\"http://localhost:8180/auth/confirm/" + URLEncoder.encode(user.getUsername(), StandardCharsets.UTF_8) + "\">CONFIRM</a>";
        mail.setText("Molimo vas potvrdite registraciju na sajtu " + link);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
	}



	@Override
	public void sendNotificaitionApproved(User user)  throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getUsername());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Fishing Acc information");
		mail.setText("Your " + user.getRole().getName() + " is approved. Now you can use your acc. :)");
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

	@Override
	public void sendNotificaitionRejected(User user, UserApproveDTO userApproveDTO)  throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getUsername());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Fishing Acc information");
		mail.setText("We are sad but your acc is not approved: " + userApproveDTO.getMessage());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	
	@Override
	public void sendReservationConfirmation(Client client, ClientAdventureReservationDTO clientAdventureReservationDTO) throws MailException, InterruptedException{
	    SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(client.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Successful reservation");
        mail.setText("You've successfully reserved " + clientAdventureReservationDTO.getAdventure().getName()+" from: "+clientAdventureReservationDTO.getStartTime()+ " to: "+clientAdventureReservationDTO.getEndTime());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
	}
	
	@Override
	public void sendAdventureActionReservationConfirmation(Client client, Adventure adventure, ClientAdventureFastReservationDTO clientAdventureFastReservationDTO) throws MailException, InterruptedException{
	    SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(client.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Successful reservation");
        mail.setText("You've successfully reserved " + adventure.getName()+" from: "+clientAdventureFastReservationDTO.getStartTime()+ " to: "+clientAdventureFastReservationDTO.getEndTime());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
	}
	
	@Override
	public void sendCottageReservationConfirmation(Client client, ClientCottageReservationDTO clientCottageReservationDTO) throws MailException, InterruptedException{
	    SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(client.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Successful reservation");
        mail.setText("You've successfully reserved " + clientCottageReservationDTO.getCottage().getName()+" from: "+clientCottageReservationDTO.getStartTime()+ " to: "+clientCottageReservationDTO.getEndTime());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
	}
	
	@Override
	public void sendCottageActionReservationConfirmation(Client client, Cottage cottage, ClientCottageFastReservationDTO clientCottageFastReservationDTO) throws MailException, InterruptedException{
	    SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(client.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Successful reservation");
        mail.setText("You've successfully reserved " + cottage.getName()+" from: "+clientCottageFastReservationDTO.getStartTime()+ " to: "+clientCottageFastReservationDTO.getEndTime());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
	}
	
	@Override
	public void sendBoatReservationConfirmation(Client client, ClientBoatReservationDTO clientBoatReservationDTO) throws MailException, InterruptedException{
	    SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(client.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Successful reservation");
        mail.setText("You've successfully reserved " + clientBoatReservationDTO.getBoat().getName()+" from: "+clientBoatReservationDTO.getStartTime()+ " to: "+clientBoatReservationDTO.getEndTime());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
	}
	
	@Override
	public void sendBoatActionReservationConfirmation(Client client, Boat boat, ClientBoatFastReservationDTO clientBoatFastReservationDTO) throws MailException, InterruptedException{
	    SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(client.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Successful reservation");
        mail.setText("You've successfully reserved " + boat.getName()+" from: "+clientBoatFastReservationDTO.getStartTime()+ " to: "+clientBoatFastReservationDTO.getEndTime());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
	}

	@Override
	public void sendNotificationNewRevisionEntity(String email, String clientEmail, Revision revision, String offerName) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("New Revision!");
		String text = "Hi, you have new revision for your service:`"+offerName+"` by " +clientEmail+" ["+revision.getGrade()+"]"+" `"+revision.getComment()+"` on date:"+revision.getCreatedAt();
		mail.setText(text);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

	@Override
	public void sendNotificationNewRevisionOwnerInstructor(String email, String clientEmail, Revision revision) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("New Revision!");
		String text = "Hi, you have new revision by " +clientEmail+" ["+revision.getGrade()+"]"+" `"+revision.getComment()+"` on date:"+revision.getCreatedAt();
		mail.setText(text);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

	@Override
	public void sendAdminComplaintResponseToClient(Complaint complaint, String offerName) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(complaint.getUserEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Complaint answer");
		String text = "";
		if(complaint.getForClient()){
			text = "Hi, our team answered complaint about you \n"+
					"complaint: `"+complaint.getComment()+"` by owner "+complaint.getProviderEmail()+" "+complaint.getProviderType()+" on date: "+complaint.getCreatedAt()+"\n"+
					"response: `"+complaint.getAdminResponse()+"` on date: " + complaint.getAdminResponsDate();
		}else{
			if(complaint.getRevisionType() == RevisionType.OWNER){
				text = "Hi, our team answered your complaint about owner `"+complaint.getProviderEmail()+"`\n"+
						"Your complaint: `"+complaint.getComment()+"` on date: "+complaint.getCreatedAt()+"\n"+
						"response: `"+complaint.getAdminResponse()+"` on date: " + complaint.getAdminResponsDate();
			}else{
				text = "Hi, our team answered your complaint about service`"+offerName+"` from `"+complaint.getProviderEmail()+"` \n"+
						"complaint: `"+complaint.getComment()+"` by client "+complaint.getUserEmail()+" on date: "+complaint.getCreatedAt()+"\n"+
						"response: `"+complaint.getAdminResponse()+"` on date: " + complaint.getAdminResponsDate();
			}
		}
		mail.setText(text);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

	@Override
	public void sendAdminComplaintResponseToProvider(Complaint complaint, String offerName) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(complaint.getProviderEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Complaint answer");
		String text = "";
		if(complaint.getForClient()){
			text = "Hi, our team answered your complaint about client `"+complaint.getUserEmail()+"`\n"+
					"Your complaint: `"+complaint.getComment()+"` on date: "+complaint.getCreatedAt()+"\n"+
					"response: `"+complaint.getAdminResponse()+"` on date: " + complaint.getAdminResponsDate();
		}else{
			if(complaint.getRevisionType() == RevisionType.OWNER){
				text = "Hi, our team answered complaint about you \n"+
						"complaint: `"+complaint.getComment()+"` by client "+complaint.getUserEmail()+" on date: "+complaint.getCreatedAt()+"\n"+
						"response: `"+complaint.getAdminResponse()+"` on date: " + complaint.getAdminResponsDate();
			}else{
				text = "Hi, our team answered complaint about your service `"+offerName+"` \n"+
						"complaint: `"+complaint.getComment()+"` by client "+complaint.getUserEmail()+" on date: "+complaint.getCreatedAt()+"\n"+
						"response: `"+complaint.getAdminResponse()+"` on date: " + complaint.getAdminResponsDate();
			}
		}
		mail.setText(text);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
}
