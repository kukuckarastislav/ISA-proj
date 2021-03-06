package com.isa.isa.security.controller;

import javax.servlet.http.HttpServletResponse;

import com.isa.isa.DTO.UserApproveDTO;
import com.isa.isa.security.exception.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.security.dto.JwtAuthenticationRequest;
import com.isa.isa.security.dto.UserTokenState;
import com.isa.isa.security.model.User;
import com.isa.isa.security.service.EmailService;
import com.isa.isa.security.service.UserService;
import com.isa.isa.security.util.TokenUtils;

import java.security.Principal;
import java.util.ArrayList;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(user.getRole().getName(),jwt, expiresIn, user.getUsername()));
	}

	// Endpoint za registraciju novog korisnika
	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO, UriComponentsBuilder ucBuilder) {

		User existUser = this.userService.findByUsername(userDTO.getEmail());

		if (existUser != null) {
			throw new ResourceConflictException(userDTO.getEmail(), "Username already exists");
		}

		User user = this.userService.save(userDTO);
		if (userDTO.isCustomer()) {
			try {
				emailService.sendNotificaition(user);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/confirm/{username}")
	public ResponseEntity confirm(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if(user!=null){
			System.out.println("Pronadjen user");
			user.setEnabled(true);
			user.setApproved(true);
			userService.save(user);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/noenabled")
	public ResponseEntity<ArrayList<UserDTO>> noEnabled() {
		ArrayList<UserDTO> noEnabledUsersDTO = userService.getNoApprovedStuff();
		return new ResponseEntity<ArrayList<UserDTO>>(noEnabledUsersDTO ,HttpStatus.OK);
	}

	@PostMapping("/setapproved")
	public ResponseEntity approveUser(@RequestBody UserApproveDTO userApproveDTO) {
		Boolean status = userService.approveOrReject(userApproveDTO);
		return new ResponseEntity(HttpStatus.OK);
	}


	@PostMapping("/signup/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<User> registerAdmin(Principal adminUser, @RequestBody UserDTO userDTO) {

		User existUser = this.userService.findByUsername(userDTO.getEmail());

		if (existUser != null) {
			throw new ResourceConflictException(userDTO.getEmail(), "Username already exists");
		}

		User user = this.userService.save(userDTO);
		if (userDTO.isCustomer()) {
			try {
				emailService.sendNotificaition(user);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}



}