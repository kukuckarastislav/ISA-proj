package com.isa.isa.DTO;

public class UserApproveDTO {
    public String email;
    public String message;
    public boolean enabled;

    public UserApproveDTO(){}

    public UserApproveDTO(String email, String message, boolean enabled) {
        this.email = email;
        this.message = message;
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
