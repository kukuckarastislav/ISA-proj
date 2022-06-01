package com.isa.isa.DTO;



import com.isa.isa.model.Address;
import com.isa.isa.model.Client;
import com.isa.isa.model.loyalty.Loyalty;

public class ClientDto {
	
	private String email;
	
	private String password;
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private Address address;

	
	private String phoneNumber;
	
	private Loyalty loyalty;

	
	

	public ClientDto() {
		super();
	}
	
	public ClientDto(Client client) {
		super();
		this.email = client.getEmail();
		this.password = client.getPassword();
		this.firstName = client.getFirstName();
		this.lastName = client.getLastName();
		this.address = client.getAddress();
		this.phoneNumber = client.getPhoneNumber();
		this.loyalty = client.getLoyalty();
	}

	public ClientDto(String email, String password, String firstName, String lastName, Address address,
			String phoneNumber,Loyalty loyalt) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.loyalty = loyalt;
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

	public Loyalty getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(Loyalty loyalty) {
		this.loyalty = loyalty;
	}
	
	
	
}
