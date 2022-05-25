package com.isa.isa.security.dto;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {
	
	private String role;
    private String accessToken;
    private Long expiresIn;
    private String email;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
    

    public UserTokenState(String role, String accessToken, long expiresIn, String email) {
		this.role = role;
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
        this.email = email;
	}

	public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}