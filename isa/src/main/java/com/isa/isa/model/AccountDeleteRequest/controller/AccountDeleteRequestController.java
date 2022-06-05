package com.isa.isa.model.AccountDeleteRequest.controller;

import com.isa.isa.model.AccountDeleteRequest.DTO.AccountDeleteRequestDetailDTO;
import com.isa.isa.model.AccountDeleteRequest.DTO.AdminResponseToAccDelReqDTO;
import com.isa.isa.model.AccountDeleteRequest.DTO.AdmninDeleteUserDTO;
import com.isa.isa.model.AccountDeleteRequest.model.AccountDeleteRequest;
import com.isa.isa.model.AccountDeleteRequest.service.AccountDeleteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/deleteRequest")
public class AccountDeleteRequestController {

    @Autowired
    private AccountDeleteRequestService accountDeleteRequestService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/allRequests")
    public ResponseEntity<ArrayList<AccountDeleteRequest>> getAllAccountDeleteRequest(Principal user) {
        return new ResponseEntity<ArrayList<AccountDeleteRequest>>(accountDeleteRequestService.getAllRequests(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/allRequestsDetail")
    public ResponseEntity<ArrayList<AccountDeleteRequestDetailDTO>> getAllAccountDeleteRequestDetailDTO(Principal user) {
        return new ResponseEntity<ArrayList<AccountDeleteRequestDetailDTO>>(accountDeleteRequestService.getAllRequestsDetailDTO(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("admin-response")
    public boolean adminResponsToUserDeleteRequest(@RequestBody AdminResponseToAccDelReqDTO adminResponseToAccDelReqDTO, Principal user){
        return accountDeleteRequestService.setAdminRespons(adminResponseToAccDelReqDTO, user.getName());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("user")
    public boolean adminDeleteUser(@RequestBody AdmninDeleteUserDTO admninDeleteUserDTO, Principal user){
        return accountDeleteRequestService.adminDeleteUser(admninDeleteUserDTO, user.getName());
    }

}
