package com.isa.isa.service;

import com.isa.isa.DTO.AccountDeleteRequestDetailDTO;
import com.isa.isa.DTO.AccountDeleteRequestFromFrontDTO;
import com.isa.isa.DTO.AdminResponseToAccDelReqDTO;
import com.isa.isa.model.*;
import com.isa.isa.model.enums.DeleteRequestStatus;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountDeleteRequestService {

	@Autowired
    private AccountDeleteRequestRepository accountDeleteRequestRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private CottageOwnerRepository cottageOwnerRepository;

	@Autowired
	private BoatOwnerRepository boatOwnerRepository;
	
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

	public Boolean createAccDeleteRequest(AccountDeleteRequestFromFrontDTO reasonDTO, Principal user) {
		if(!reasonDTO.username.equals(user.getName())) return false;
		AccountDeleteRequest accountDeleteRequest = new AccountDeleteRequest(user.getName(), reasonDTO.reason, UserTypeISA.INSTRUCTOR);
		accountDeleteRequestRepository.saveAndFlush(accountDeleteRequest);
		return true;
	}

	public ArrayList<AccountDeleteRequestDetailDTO> getAllRequestsDetailDTO() {
		ArrayList<AccountDeleteRequestDetailDTO> retlist = new ArrayList<AccountDeleteRequestDetailDTO>();
		for (AccountDeleteRequest adr : accountDeleteRequestRepository.findAll()) {
			AccountDeleteRequestDetailDTO adrDetailDTO = getAccountDeleteRequestDetailDTO(adr);
			if(adrDetailDTO != null)
				retlist.add(adrDetailDTO);
		}
		return retlist;
	}



	private AccountDeleteRequestDetailDTO getAccountDeleteRequestDetailDTO(AccountDeleteRequest accountDeleteRequest){
		if(accountDeleteRequest.getUserTypeISA() == UserTypeISA.CLIENT){
			Client client = clientRepository.findByEmail(accountDeleteRequest.getUsername());
			if(client != null)
				return new AccountDeleteRequestDetailDTO(client.getFirstName(), client.getLastName(), "Client", client.getEmail(), client.getAddress(),client.getPhoneNumber(), accountDeleteRequest);
		}else if(accountDeleteRequest.getUserTypeISA() == UserTypeISA.BOAT_OWNER){
			BoatOwner boatOwner = boatOwnerRepository.getByEmail(accountDeleteRequest.getUsername());
			if(boatOwner != null)
				return new AccountDeleteRequestDetailDTO(boatOwner.getFirstName(), boatOwner.getLastName(), "Boat Owner", boatOwner.getEmail(), boatOwner.getAddress(), boatOwner.getPhoneNumber(), accountDeleteRequest);
		}else if(accountDeleteRequest.getUserTypeISA() == UserTypeISA.COTTAGE_OWNER){
			CottageOwner cottageOwner = cottageOwnerRepository.getByEmail(accountDeleteRequest.getUsername());
			if(cottageOwner != null)
				return new AccountDeleteRequestDetailDTO(cottageOwner.getFirstName(), cottageOwner.getLastName(), "Cottage Owner", cottageOwner.getEmail(), cottageOwner.getAddress(), cottageOwner.getPhoneNumber(), accountDeleteRequest);
		}else if(accountDeleteRequest.getUserTypeISA() == UserTypeISA.INSTRUCTOR){
			Instructor instructor = instructorRepository.getByEmail(accountDeleteRequest.getUsername());
			if(instructor != null)
				return new AccountDeleteRequestDetailDTO(instructor.getFirstName(), instructor.getLastName(), "Instructor", instructor.getEmail(), instructor.getAddress(), instructor.getPhoneNumber(), accountDeleteRequest);
		}

		return null;
	}

    public boolean setAdminRespons(AdminResponseToAccDelReqDTO adminResponseToAccDelReqDTO, String adminEmail) {
		Optional<AccountDeleteRequest> accountDeleteRequestOptional = accountDeleteRequestRepository.findById(adminResponseToAccDelReqDTO.getIdRequest());
		AccountDeleteRequest accountDeleteRequest = accountDeleteRequestOptional.get();
		if(accountDeleteRequest != null || accountDeleteRequest.getId() == 0){
			if(adminResponseToAccDelReqDTO.getDeleteRequestStatus() != DeleteRequestStatus.PENDING){
				accountDeleteRequest.setAdminUsername(adminEmail);
				accountDeleteRequest.setAdminResponse(adminResponseToAccDelReqDTO.getAdminResponse());
				accountDeleteRequest.setDeleteRequestStatus(adminResponseToAccDelReqDTO.getDeleteRequestStatus());
				accountDeleteRequest.setAdminResponsDate(LocalDateTime.now());
				accountDeleteRequestRepository.save(accountDeleteRequest);
				System.out.println("Admin response successfully");
				return true;
			}else{
				System.out.println("Error: status is invalid");
			}
		}else{
			System.out.println("Error: id: accountDeleteRequest is not found");
		}
		return false;
    }
}
