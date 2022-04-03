package com.isa.isa.security.service.impl;

import com.isa.isa.DTO.UserApproveDTO;
import com.isa.isa.model.Client;
import com.isa.isa.model.termins.DTO.ClientAdventureReservationDTO;
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

}
