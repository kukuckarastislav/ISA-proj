package com.isa.isa.controller;

import com.isa.isa.DTO.InstructorBusinessReportDTO;
import com.isa.isa.model.AccountDeleteRequest.DTO.AccountDeleteRequestFromFrontDTO;
import com.isa.isa.DTO.BiographyDTO;
import com.isa.isa.DTO.PasswordDto;
import com.isa.isa.DTO.InstructorDTO;
import com.isa.isa.model.AccountDeleteRequest.service.AccountDeleteRequestService;
import com.isa.isa.model.TimeStamp;
import com.isa.isa.model.enums.IsaEntityType;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.loyalty.LoyaltyService;
import com.isa.isa.model.termins.service.InstructorTermService;
import com.isa.isa.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private AccountDeleteRequestService accountDeleteRequestService;

    @Autowired
    private InstructorTermService instructorTermService;

    @GetMapping("/profileInfo")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public InstructorDTO getInfo(Principal user) {
        return this.instructorService.getInfoInstructorDTO(user.getName());
    }

    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @PutMapping("/updateProfile")
    public ResponseEntity<InstructorDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO) {
        InstructorDTO retVal = instructorService.updateInstructor(instructorDTO);
        if(retVal != null)
            return new ResponseEntity<InstructorDTO>(retVal, HttpStatus.OK);
        return new ResponseEntity<InstructorDTO>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @PutMapping("/updatePassword")
    public Boolean updateInstructorPassword(@RequestBody PasswordDto passwordDto, Principal user) {
        return instructorService.updatePassword(user, passwordDto);
    }

    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @PutMapping("/updateBiography")
    public ResponseEntity<String> updateInstructorBiography(@RequestBody BiographyDTO biographyDTO, Principal user) { // @RequestBody String newBiography
        String updatedBio = instructorService.updateBiography(user, biographyDTO.biography);
        if(updatedBio != null){
            return new ResponseEntity<String>(updatedBio, HttpStatus.OK);
        }
        return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @PutMapping("/deleteRequest")
    public ResponseEntity<Boolean> deleteRequest(@RequestBody AccountDeleteRequestFromFrontDTO reasonDTO, Principal user) {
        return new ResponseEntity<Boolean>(accountDeleteRequestService.createAccDeleteRequest(reasonDTO, user, UserTypeISA.INSTRUCTOR), HttpStatus.OK);
    }

    @PostMapping("/business")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public InstructorBusinessReportDTO getBusiness(Principal user, @RequestBody TimeStamp timeStamp) {
        return instructorTermService.getBusinessReport(user.getName(), timeStamp);
    }

}
