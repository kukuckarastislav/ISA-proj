package com.isa.isa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.PersonDto;
import com.isa.isa.model.Client;
import com.isa.isa.model.Person;
import com.isa.isa.service.ClientService;
import com.isa.isa.service.PersonService;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping(value = "api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody PersonDto personDto, HttpServletResponse response) {

		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				personDto.getEmail(), personDto.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}
	
	@PostMapping("/registration")
	public ResponseEntity<Client> addUser(@RequestBody ClientDto clientDto, UriComponentsBuilder ucBuilder) {

		Person existPerson = this.personService.findByEmail(clientDto.getEmail());

		if (existPerson != null) {
			//throw new ResourceConflictException(clientDto.getEmail(), "Email already exists");
			return null;
		}

		Client client = this.clientService.save(new Client(clientDto));

		return new ResponseEntity<>(client, HttpStatus.CREATED);
	}
}
