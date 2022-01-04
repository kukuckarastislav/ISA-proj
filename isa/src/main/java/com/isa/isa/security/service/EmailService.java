package com.isa.isa.security.service;

import com.isa.isa.DTO.UserApproveDTO;
import org.springframework.mail.MailException;

import com.isa.isa.security.model.User;

public interface EmailService {
	void sendNotificaition(User user) throws MailException, InterruptedException;

    void sendNotificaitionApproved(User user) throws MailException, InterruptedException;

    void sendNotificaitionRejected(User user, UserApproveDTO userApproveDTO) throws MailException, InterruptedException;
}
