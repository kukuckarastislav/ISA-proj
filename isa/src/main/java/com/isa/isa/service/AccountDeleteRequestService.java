package com.isa.isa.service;

import com.isa.isa.DTO.AccountDeleteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.AccountDeleteRequest;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.repository.AccountDeleteRequestRepository;

import java.security.Principal;
import java.util.ArrayList;

@Service
public class AccountDeleteRequestService {

	@Autowired
    private AccountDeleteRequestRepository accountDeleteRequestRepository;
	
	public AccountDeleteRequest save(AccountDeleteRequest request) {
		return accountDeleteRequestRepository.saveAndFlush(request);
    }
	
	public AccountDeleteRequest getByUsername(String username)
	{
		return accountDeleteRequestRepository.getByUsername(username);
	}

    public ArrayList<AccountDeleteRequest> getAllRequests() {
		return (ArrayList<AccountDeleteRequest>) accountDeleteRequestRepository.findAll();
    }

	public Boolean createAccDeleteRequest(AccountDeleteRequestDTO reasonDTO, Principal user) {
		if(!reasonDTO.username.equals(user.getName())) return false;
		AccountDeleteRequest accountDeleteRequest = new AccountDeleteRequest(user.getName(), reasonDTO.reason);
		accountDeleteRequestRepository.saveAndFlush(accountDeleteRequest);
		return true;
	}
}
