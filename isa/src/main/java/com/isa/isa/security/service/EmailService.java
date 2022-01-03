package com.isa.isa.security.service;

import org.springframework.mail.MailException;

import com.isa.isa.security.model.User;

public interface EmailService {
	void sendNotificaition(User user) throws MailException, InterruptedException;
}
