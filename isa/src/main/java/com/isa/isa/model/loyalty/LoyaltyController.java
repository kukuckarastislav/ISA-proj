package com.isa.isa.model.loyalty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
        var retrieval = loyaltyService.setLoyaltySettings(newLoyaltySettings);
        if (retrieval != null){
            return new ResponseEntity<>(retrieval, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
