package com.isa.isa.security.service;

import java.util.ArrayList;
import java.util.List;

import com.isa.isa.DTO.UserApproveDTO;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.security.model.User;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserDTO userDTO);
    User save(User user);
    ArrayList<UserDTO> getNoApprovedStuff();
    boolean approveOrReject(UserApproveDTO userApproveDTO);
}
