package com.isa.isa.DTO;

public class ClientPasswordDto {
	
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeated;
	
	
	public ClientPasswordDto() {
		super();
		
	}
	public ClientPasswordDto(String oldPassword, String newPassword, String newPasswordRepeated) {
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
	
	
}
