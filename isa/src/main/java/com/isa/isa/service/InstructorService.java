package com.isa.isa.service;

import com.isa.isa.DTO.InstructorDTO;
import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.loyalty.LoyaltySettings;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.termins.DTO.ComplaintClientDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.DTO.RevisionClientDTO;
import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.revisions.model.Revision;
import com.isa.isa.model.termins.service.InstructorFastReservationService;
import com.isa.isa.model.termins.service.InstructorReservationService;
import com.isa.isa.model.termins.service.InstructorTermService;
import com.isa.isa.repository.AdventureRepository;
import com.isa.isa.repository.InstructorRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.isa.isa.security.model.User;
import com.isa.isa.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private AdventureRepository adventureRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private InstructorTermService instructorTermService;
	
	@Autowired
    private InstructorReservationService instructorReservationService;
	
	@Autowired
    private InstructorFastReservationService instructorFastReservationService;


    public Instructor save(UserDTO userDTO) {
        Instructor instructor = new Instructor(userDTO);
        return instructorRepository.saveAndFlush(instructor);
    }
    
    public ArrayList<Instructor> getAllInstructorsWithAdventures(){
    	ArrayList<Instructor> instructors = new ArrayList<Instructor>(instructorRepository.findAll());
    	for(Instructor i : instructors){
    		//i.setAdventures(new HashSet<>(adventureRepository.getByInstructorId(i.getId())));
    		Set<Adventure>adventures = new HashSet<>(adventureRepository.getByInstructorId(i.getId()));
    		for (Adventure a : adventures) {
    			a.setInstructor(null);
    		}
    		i.setAdventures(adventures);
    	}
    	return instructors;
    }
    
    public Instructor getInstructorById(int id) {
    	Instructor instructor = instructorRepository.getById(id);
    	Set<Adventure>adventures = new HashSet<>(adventureRepository.getByInstructorId(instructor.getId()));
		for (Adventure a : adventures) {
			a.setInstructor(null);
		}
		instructor.setAdventures(adventures);
    	return instructor;
    }

    public InstructorDTO getInfoInstructorDTO(String name) {
		Instructor instructor = instructorRepository.getByEmail(name);
		if(instructor != null)
			return new InstructorDTO(instructor);

		return new InstructorDTO();
    }

	public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
		// email ne mozemo menjati pa mozemo da trazimo po njemu
		Instructor instructor = instructorRepository.getByEmail(instructorDTO.getEmail());
		if(instructor != null){
			instructor.updateInfo(instructorDTO);
			instructorRepository.save(instructor);
			return new InstructorDTO(instructor);
		}else {
			return null;
		}
	}

	public Boolean updatePassword(Principal principalUser, PasswordDto passwordDto) {
		Instructor instructor = instructorRepository.getByEmail(principalUser.getName());
		if(passwordEncoder.matches(passwordDto.getOldPassword(), instructor.getPassword())
			&& passwordDto.newPasswordEqualRepeted()){

			instructor.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
			instructorRepository.save(instructor);

			User user = userRepository.findByUsername(instructor.getEmail());
			user.setPassword(instructor.getPassword());
			userRepository.save(user);
			return true;
		}else{
			return false;
		}
	}

	public String updateBiography(Principal user, String newBiography) {
		Instructor instructor = instructorRepository.getByEmail(user.getName());
		if(instructor != null){
			instructor.setBiography(newBiography);
			instructorRepository.save(instructor);
			return newBiography;
		}

		return null;
	}
	
	public Boolean isInstructorFree(InstructorTermsDTO dto) {
		if(!instructorTermService.isInstructorFree(dto)) return false;
		if (!instructorReservationService.isInstructorFree(dto)) return false;
		if (!instructorFastReservationService.isInstructorFree(dto)) return false;
		return true;
	}
	
	public void addRevision(RevisionClientDTO revisionClientDTO) {
		Instructor instructor = instructorRepository.findById(revisionClientDTO.getOverseerId()).get();
		instructor.getRevisions().add(new Revision(revisionClientDTO.getUserId(),revisionClientDTO.getOverseerGrade(),revisionClientDTO.getOverseerComment()));
		instructorRepository.saveAndFlush(instructor);
	}
	
	public void addComplaint(ComplaintClientDTO complaintClientDTO) {
		Instructor instructor = instructorRepository.findById(complaintClientDTO.getOverseerId()).get();
		instructor.getComplaints().add(new Complaint(complaintClientDTO.getOverseerComment(),complaintClientDTO.getUserEmail(), UserTypeISA.INSTRUCTOR, instructor.getEmail(), RevisionType.OWNER, -1));
		instructorRepository.saveAndFlush(instructor);
	}

    public void updateLoyaltyForAll(LoyaltySettings loyaltySettings) {
		for(Instructor instructor : instructorRepository.findAll()){
			if(instructor.getLoyalty().update(loyaltySettings.getMinimumScoreForSILVER(), loyaltySettings.getMinimumScoreForGOLD())){
				instructorRepository.saveAndFlush(instructor);
			}
		}
    }
}
