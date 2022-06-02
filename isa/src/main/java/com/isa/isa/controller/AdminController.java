package com.isa.isa.controller;

import com.isa.isa.DTO.*;
import com.isa.isa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/profileInfo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AdminDTO getInfo(Principal user) {
        return adminService.getInfoAdminDTO(user.getName());
    }

    // admin controller ....
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateProfile")
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO retVal = adminService.updateAdmin(adminDTO);
        if(retVal != null)
            return new ResponseEntity<AdminDTO>(retVal, HttpStatus.OK);
        return new ResponseEntity<AdminDTO>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updatePassword")
    public Boolean updateAdminPassword(@RequestBody PasswordDto passwordDto, Principal user) {
        return adminService.updatePassword(user, passwordDto);
    }


    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ArrayList<AdminViewUserDTO>> getUsers(Principal user) {
        return new ResponseEntity<ArrayList<AdminViewUserDTO>>(adminService.getUsers(),HttpStatus.OK);
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> adminDeleteUser(Principal user, @RequestBody AdminDeleteUserDTO adminDeleteUserDTO) {
        return new ResponseEntity<Boolean>(adminService.deleteUser(adminDeleteUserDTO),HttpStatus.OK);
    }

    @GetMapping("/entity")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ArrayList<AdminViewEntityDTO>> getAdminViewEntity(Principal user) {
        return new ResponseEntity<ArrayList<AdminViewEntityDTO>>(adminService.getAdminViewEntity(),HttpStatus.OK);
    }

    @PostMapping("/entity")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> adminDeleteEntity(Principal user, @RequestBody AdminDeleteEntityDTO adminDeleteEntityDTO) {
        return new ResponseEntity<Boolean>(adminService.deleteEntity(adminDeleteEntityDTO),HttpStatus.OK);
    }



}
