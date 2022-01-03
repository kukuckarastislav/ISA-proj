package com.isa.isa.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.isa.security.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

