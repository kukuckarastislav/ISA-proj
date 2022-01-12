package com.isa.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.isa.isa.model.enums.OwnerType;

@Entity
public class AccountDeleteRequest {

	@Id
	@SequenceGenerator(name = "mySeqGenV1", sequenceName = "mySeqV1", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
	private int id;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
    private OwnerType ownerType;
	
	public AccountDeleteRequest(String email,OwnerType ownerType)
	{
		this.email = email;
		this.ownerType = ownerType;
	}
	public AccountDeleteRequest()
	{
	
	}
}
