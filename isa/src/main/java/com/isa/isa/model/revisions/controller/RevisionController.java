package com.isa.isa.model.revisions.controller;

import com.isa.isa.model.revisions.DTO.RevisionAdminViewDTO;
import com.isa.isa.model.revisions.service.RevisionService;
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
@RequestMapping("/api/revision")
public class RevisionController {

    @Autowired
    private RevisionService revisionService;

    @GetMapping("/entity")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ArrayList<RevisionAdminViewDTO>> getRevisionEntitysAdminView(Principal user) {
        return new ResponseEntity<ArrayList<RevisionAdminViewDTO>>(revisionService.getRevisionAdminView(), HttpStatus.OK);
    }

    @GetMapping("/owners")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ArrayList<RevisionAdminViewDTO>> getRevisionOwnersAdminView(Principal user) {
        return new ResponseEntity<ArrayList<RevisionAdminViewDTO>>(revisionService.getRevisionOwnersAdminView(), HttpStatus.OK);
    }


}
