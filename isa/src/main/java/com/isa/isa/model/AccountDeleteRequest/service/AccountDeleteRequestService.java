package com.isa.isa.model.AccountDeleteRequest.service;

import com.isa.isa.DTO.AdminDeleteEntityDTO;
import com.isa.isa.model.AccountDeleteRequest.DTO.AccountDeleteRequestDetailDTO;
import com.isa.isa.model.AccountDeleteRequest.DTO.AccountDeleteRequestFromFrontDTO;
import com.isa.isa.model.AccountDeleteRequest.DTO.AdminResponseToAccDelReqDTO;
import com.isa.isa.model.*;
import com.isa.isa.model.AccountDeleteRequest.DTO.AdmninDeleteUserDTO;
import com.isa.isa.model.AccountDeleteRequest.model.AccountDeleteRequest;
import com.isa.isa.model.AccountDeleteRequest.repository.AccountDeleteRequestRepository;
import com.isa.isa.model.AccountDeleteRequest.enums.DeleteRequestStatus;
import com.isa.isa.model.enums.IsaEntityType;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.repository.*;
import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;
import com.isa.isa.security.service.EmailService;
import com.isa.isa.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	private AdventureRepository adventureRepository;

	@Autowired
	private CottageRepository cottageRepository;

	@Autowired
	private BoatRepository boatRepository;
	
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

	public Boolean createAccDeleteRequest(AccountDeleteRequestFromFrontDTO reasonDTO, Principal user, UserTypeISA userType) {
		if(!reasonDTO.username.equals(user.getName())) return false;
		AccountDeleteRequest accountDeleteRequest = new AccountDeleteRequest(user.getName(), reasonDTO.reason, userType);
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

	@Autowired
	private EmailService emailService;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean setAdminRespons(AdminResponseToAccDelReqDTO adminResponseToAccDelReqDTO, String adminEmail) {
		AccountDeleteRequest accountDeleteRequest = null;
		try {
			accountDeleteRequest = accountDeleteRequestRepository.getDelReqById(adminResponseToAccDelReqDTO.getIdRequest());
			if(accountDeleteRequest != null){
				if(adminResponseToAccDelReqDTO.getDeleteRequestStatus() != DeleteRequestStatus.PENDING){
					accountDeleteRequest.setAdminUsername(adminEmail);
					accountDeleteRequest.setAdminResponse(adminResponseToAccDelReqDTO.getAdminResponse());
					accountDeleteRequest.setDeleteRequestStatus(adminResponseToAccDelReqDTO.getDeleteRequestStatus());
					accountDeleteRequest.setAdminResponsDate(LocalDateTime.now());
					accountDeleteRequestRepository.save(accountDeleteRequest);
					if(accountDeleteRequest.getDeleteRequestStatus() == DeleteRequestStatus.APPROVED){
						deleteUser(accountDeleteRequest.getUsername(), accountDeleteRequest.getUserTypeISA());
					}
					System.out.println("Admin response successfully");
					emailService.sendNotificaitionDeleteAccRespo(accountDeleteRequest);
					return true;
				}else{
					System.out.println("Error: status is invalid");
				}
			}else{
				System.out.println("Error: id: accountDeleteRequest is not found");
			}
		}catch (PessimisticLockingFailureException e){
			System.out.println("error PessimisticLockingFailureException");
		}
		return false;
    }

	@Autowired
	private UserRepository userRepository;

	private Boolean deleteUser(String username, UserTypeISA userTypeISA){
		if(userTypeISA == UserTypeISA.INSTRUCTOR){
			Instructor instructor = instructorRepository.getByEmail(username);
			if(instructor == null) return false;
			instructor.setDeleted(true);
			instructorRepository.saveAndFlush(instructor);
			User user = userRepository.findByUsername(instructor.getEmail());
			user.setEnabled(false);
			userRepository.saveAndFlush(user);
			deleteAllOwnerEntity(instructor);
		}else if(userTypeISA == UserTypeISA.COTTAGE_OWNER){
			CottageOwner cottageOwner = cottageOwnerRepository.getByEmail(username);
			if(cottageOwner == null) return false;
			cottageOwner.setDeleted(true);
			cottageOwnerRepository.saveAndFlush(cottageOwner);
			User user = userRepository.findByUsername(cottageOwner.getEmail());
			user.setEnabled(false);
			userRepository.saveAndFlush(user);
			deleteAllOwnerEntity(cottageOwner);
		}else if(userTypeISA == UserTypeISA.BOAT_OWNER){
			BoatOwner boatOwner = boatOwnerRepository.getByEmail(username);
			if(boatOwner == null) return false;
			boatOwner.setDeleted(true);
			boatOwnerRepository.saveAndFlush(boatOwner);
			User user = userRepository.findByUsername(boatOwner.getEmail());
			user.setEnabled(false);
			userRepository.saveAndFlush(user);
			deleteAllOwnerEntity(boatOwner);
		}else if(userTypeISA == UserTypeISA.CLIENT){
			Client client = clientRepository.findByEmail(username);
			if(client == null) return false;
			client.setDeleted(true);
			clientRepository.saveAndFlush(client);
			User user = userRepository.findByUsername(client.getEmail());
			user.setEnabled(false);
			userRepository.saveAndFlush(user);
		}
		System.out.println("Deleted and disabled " + username);
		return true;
	}

	public void deleteAllOwnerEntity(Instructor instructor){
		for(Adventure adventure : adventureRepository.getByInstructorId(instructor.getId())){
			deleteEntity(IsaEntityType.ADVENTURE, adventure.getId());
		}
	}

	private void deleteAllOwnerEntity(BoatOwner boatOwner) {
		for(Boat boat : boatRepository.getByOwnerId(boatOwner.getId())){
			deleteEntity(IsaEntityType.BOAT, boat.getId());
		}
	}

	private void deleteAllOwnerEntity(CottageOwner cottageOwner) {
		for(Cottage cottage : cottageRepository.getByOwnerId(cottageOwner.getId())){
			deleteEntity(IsaEntityType.COTTAGE, cottage.getId());
		}
	}

	public boolean adminDeleteUser(AdmninDeleteUserDTO admninDeleteUserDTO, String adminUsername) {
		return deleteUser(admninDeleteUserDTO.getUsername(), admninDeleteUserDTO.getUserTypeISA());
	}

	public Boolean deleteEntity(IsaEntityType isaEntityType, int idEntity) {
		if(isaEntityType == IsaEntityType.ADVENTURE){
			Optional<Adventure> adventureOptional = adventureRepository.findById(idEntity);
			if(adventureOptional.isEmpty()) return false;
			Adventure adventure = adventureOptional.get();
			adventure.setDeleted(true);
			adventureRepository.saveAndFlush(adventure);
		}else if(isaEntityType == IsaEntityType.BOAT){
			Optional<Boat> boatOptional = boatRepository.findById(idEntity);
			if(boatOptional.isEmpty()) return false;
			Boat boat = boatOptional.get();
			boat.setDeleted(true);
			boatRepository.saveAndFlush(boat);
		}else if(isaEntityType == IsaEntityType.COTTAGE){
			Optional<Cottage> cottageOptional = cottageRepository.findById(idEntity);
			if(cottageOptional.isEmpty()) return false;
			Cottage cottage = cottageOptional.get();
			cottage.setDeleted(true);
			cottageRepository.saveAndFlush(cottage);
		}

		return true;
	}
}
