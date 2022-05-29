package com.isa.isa.controller;

import com.isa.isa.DTO.AdminDTO;
import com.isa.isa.DTO.AdminViewUserDTO;
import com.isa.isa.DTO.InstructorDTO;
import com.isa.isa.DTO.PasswordDto;
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


    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ArrayList<AdminViewUserDTO>> getUsers(Principal user) {
        return new ResponseEntity<ArrayList<AdminViewUserDTO>>(adminService.getUsers(),HttpStatus.OK);
    }


}
