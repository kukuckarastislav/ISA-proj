package com.isa.isa.service;

import com.isa.isa.model.Person;

public interface PersonService {
	Person findByEmail(String email);
}
