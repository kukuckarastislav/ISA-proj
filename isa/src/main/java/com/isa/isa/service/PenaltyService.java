package com.isa.isa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.isa.isa.model.Client;
import com.isa.isa.model.Penalty;
import com.isa.isa.model.report.model.Report;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.PenaltyRepository;

@Service
public class PenaltyService {
	
	@Autowired
    private PenaltyRepository penaltyRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Penalty getPenaltyFromReport(Report report) {
		Optional<Client> clientCheck = clientRepository.findById(report.getIdClient());
		if(clientCheck.isEmpty()) return null;
		Client client = clientCheck.get();
		if(!report.getClientShowedUp()) {
			return penaltyRepository.save(new Penalty(client,"Client didn't show up for a reservation"));
		} else if(report.getSanctionClient() && report.getSanctionApproved()) {
			return penaltyRepository.save(new Penalty(client,report.getText()));
		}
		return null;
	}
	
	//@Scheduled(cron = "0 23 21 28 5 *")
	@Scheduled(cron = "0 0 0 1 * *")
	public void deletePenalties() {
		penaltyRepository.deleteAll();
	}

}
