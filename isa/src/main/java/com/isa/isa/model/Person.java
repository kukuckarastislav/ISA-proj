package com.isa.isa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.isa.DTO.ClientDto;
import com.isa.isa.DTO.StuffDTO;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.userdetails.UserDetails;



@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
public abstract class Person implements UserDetails {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "mySeqGenV1", sequenceName = "mySeqV1", initialValue = 10, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
	private int id;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@Embedded
	private Address address;

	@Column(nullable=false)
	private String phoneNumber;
	
	 @Column(name = "enabled")
	    private boolean enabled;

	 @Column(name = "last_password_reset_date")
	 private Timestamp lastPasswordResetDate;
	
	
	public Person() {
	}

	public Person(StuffDTO stuffDTO) {
		this.email = stuffDTO.getEmail();
		this.password = stuffDTO.getPassword();
		this.firstName = stuffDTO.getFirstName();
		this.lastName = stuffDTO.getLastName();
		this.address = stuffDTO.getAddress();
		this.phoneNumber = stuffDTO.getPhoneNumber();
	}
	
	public Person(ClientDto clientDto) {
		this.email = clientDto.getEmail();
		this.password = clientDto.getPassword();
		this.firstName = clientDto.getFirstName();
		this.lastName = clientDto.getLastName();
		this.address = clientDto.getAddress();
		this.phoneNumber = clientDto.getPhoneNumber();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	  public Timestamp getLastPasswordResetDate() {
	        return lastPasswordResetDate;
	    }

	    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
	        this.lastPasswordResetDate = lastPasswordResetDate;
	    }

	    @JsonIgnore
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @JsonIgnore
	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @JsonIgnore
	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }



	
	
}
