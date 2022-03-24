package com.isa.isa.controller;

import com.isa.isa.DTO.AccountDeleteRequestDetailDTO;
import com.isa.isa.model.AccountDeleteRequest;
import com.isa.isa.service.AccountDeleteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
