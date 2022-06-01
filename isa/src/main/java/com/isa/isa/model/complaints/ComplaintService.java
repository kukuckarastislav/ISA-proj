package com.isa.isa.model.complaints;

import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.complaints.DTO.ComplaintAdminResponseDTO;
import com.isa.isa.model.complaints.DTO.ComplaintAdminViewDTO;
import com.isa.isa.model.complaints.DTO.ComplaintForClientDTO;
import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.model.StatusOfComplaint;
import com.isa.isa.model.termins.repository.InstructorFastReservationRepository;
import com.isa.isa.model.termins.repository.InstructorReservationRepository;
import com.isa.isa.repository.AdventureRepository;
import com.isa.isa.repository.BoatRepository;
import com.isa.isa.repository.ClientRepository;
import com.isa.isa.repository.CottageRepository;
import com.isa.isa.security.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ArrayList<ComplaintAdminViewDTO> getRevisionAdminView() {
        ArrayList<ComplaintAdminViewDTO> complaintAdminViewDTOS = new ArrayList<>();
        for(Complaint complaint : complaintRepository.findAll()){
            String serviceName = getServiceNameByComplaint(complaint);
            complaintAdminViewDTOS.add(new ComplaintAdminViewDTO(complaint, serviceName));
        }
        return complaintAdminViewDTOS;
    }

    public Boolean postAdminComplaintResponse(ComplaintAdminResponseDTO complaintAdminResponseDTO) {
        Optional<Complaint> optComplaint = complaintRepository.findById(complaintAdminResponseDTO.getId());
        if(optComplaint.isPresent()){
            Complaint complaint = optComplaint.get();
            complaint.setAdminResponse(complaintAdminResponseDTO.getResponse());
            complaint.setAdminEmail(complaintAdminResponseDTO.getAdminEmail());
            complaint.setAdminResponsDate(LocalDateTime.now());
            complaint.setStatusOfComplaint(StatusOfComplaint.ANSWERED);
            complaintRepository.saveAndFlush(complaint);
            //send email
            String offerName = getServiceNameByComplaint(complaint);
            emailService.sendAdminComplaintResponseToClient(complaint, offerName);
            emailService.sendAdminComplaintResponseToProvider(complaint, offerName);
            return true;
        }else{
            System.out.println("Error id of Complaint is invalid");
            return false;
        }
    }

    private String getServiceNameByComplaint(Complaint complaint){
        String serviceName = "";
        if(complaint.getRevisionType() == RevisionType.ENTITY){
            if(complaint.getProviderType() == UserTypeISA.INSTRUCTOR){
                Adventure adventure = adventureRepository.getAdventureByComplaintId(complaint.getId());
                if(adventure != null)
                    serviceName = adventure.getName();
            }else if(complaint.getProviderType() == UserTypeISA.BOAT_OWNER){
                Boat boat = boatRepository.getBoatByComplaintId(complaint.getId());
                if(boat != null)
                    serviceName = boat.getName();
            }else if(complaint.getProviderType() == UserTypeISA.COTTAGE_OWNER){
                Cottage cottage = cottageRepository.getCottageByComplaintId(complaint.getId());
                if(cottage != null)
                    serviceName = cottage.getName();
            }
        }
        return serviceName;
    }

    @Autowired
    private InstructorReservationRepository instructorReservationRepository;

    @Autowired private InstructorFastReservationRepository instructorFastReservationRepository;

    public Boolean postInstructorComplaint(String username, ComplaintForClientDTO complaintForClientDTO) {

        Client client = clientRepository.findByEmail(complaintForClientDTO.getClientEmail());
        if(client == null) return false;

        if(complaintForClientDTO.getFastReservation()){
            InstructorFastReservation instructorFastReservation = instructorFastReservationRepository.getByIdWithHistory(complaintForClientDTO.getIdReservation());
            if(instructorFastReservation == null) return false;
            if(instructorFastReservation.getEndTime().isAfter(LocalDateTime.now())) return false;
            if(!instructorFastReservation.isTakenByClientWihtUsername(complaintForClientDTO.getClientEmail())) return false;

        }else{
            InstructorReservation instructorReservation = instructorReservationRepository.findById(complaintForClientDTO.getIdReservation()).get();
            if(instructorReservation == null) return false;
            if(instructorReservation.getEndTime().isAfter(LocalDateTime.now())) return false;
            if(!instructorReservation.getClient().getEmail().equals(complaintForClientDTO.getClientEmail())) return false;

        }

        Complaint instructorComplaint = new Complaint(username, UserTypeISA.INSTRUCTOR, complaintForClientDTO);

        client.getComplaints().add(instructorComplaint);
        clientRepository.saveAndFlush(client);

        return true;
    }
}
