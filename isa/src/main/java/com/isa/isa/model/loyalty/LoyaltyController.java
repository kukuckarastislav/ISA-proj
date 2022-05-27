package com.isa.isa.model.loyalty;

import com.isa.isa.model.revisions.DTO.RevisionAdminViewDTO;
import com.isa.isa.model.revisions.service.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/loyalty")
public class LoyaltyController {

    @Autowired
    private LoyaltyService loyaltyService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<LoyaltySettings> getLoyaltySettings(Principal user) {
        return new ResponseEntity<LoyaltySettings>(loyaltyService.getLoyaltySettings(), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<LoyaltySettings> setLoyaltySettings(Principal user, @RequestBody LoyaltySettings newLoyaltySettings) {
        return new ResponseEntity<LoyaltySettings>(loyaltyService.setLoyaltySettings(newLoyaltySettings), HttpStatus.OK);
    }

}
