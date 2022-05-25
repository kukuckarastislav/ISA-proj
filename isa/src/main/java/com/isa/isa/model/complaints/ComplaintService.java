package com.isa.isa.model.complaints;

import com.isa.isa.model.complaints.DTO.ComplaintAdminViewDTO;
import com.isa.isa.model.complaints.model.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public ArrayList<ComplaintAdminViewDTO> getRevisionAdminView() {
        ArrayList<ComplaintAdminViewDTO> complaintAdminViewDTOS = new ArrayList<>();
        for(Complaint complaint : complaintRepository.findAll()){
            complaintAdminViewDTOS.add(new ComplaintAdminViewDTO(complaint));
        }
        return complaintAdminViewDTOS;
    }
}
