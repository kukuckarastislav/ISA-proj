package com.isa.isa.DTO;

public class PasswordDto {
	
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeated;
	
	
	public PasswordDto() {
		super();
		
	}
	public PasswordDto(String oldPassword, String newPassword, String newPasswordRepeated) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPasswordRepeated = newPasswordRepeated;
	}

	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordRepeated() {
		return newPasswordRepeated;
	}
	public void setNewPasswordRepeated(String newPasswordRepeated) {
		this.newPasswordRepeated = newPasswordRepeated;
	}


	public boolean newPasswordEqualRepeted() {
		return newPassword.equals(newPasswordRepeated);
	}
}
