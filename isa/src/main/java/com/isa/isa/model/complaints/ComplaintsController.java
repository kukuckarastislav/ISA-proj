package com.isa.isa.model.complaints;

import com.isa.isa.model.complaints.DTO.ComplaintAdminResponseDTO;
import com.isa.isa.model.complaints.DTO.ComplaintAdminViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintsController {

    @Autowired
    private ComplaintService complaintsService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ArrayList<ComplaintAdminViewDTO>> getComplaintsAdminView(Principal user) {
        return new ResponseEntity<ArrayList<ComplaintAdminViewDTO>>(complaintsService.getRevisionAdminView(), HttpStatus.OK);
    }

    @PostMapping("/respons")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> postAdminComplaintResponse(Principal user, @RequestBody ComplaintAdminResponseDTO complaintAdminResponseDTO) {
        return new ResponseEntity<Boolean>(complaintsService.postAdminComplaintResponse(complaintAdminResponseDTO), HttpStatus.OK);
    }

}
