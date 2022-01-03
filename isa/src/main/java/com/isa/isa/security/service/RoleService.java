package com.isa.isa.security.service;

import java.util.List;

import com.isa.isa.security.model.Role;

public interface RoleService {
	Role findById(Long id);
	List<Role> findByName(String name);
}
