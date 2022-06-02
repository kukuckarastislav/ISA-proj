package com.isa.isa.DTO;

import com.isa.isa.model.enums.UserTypeISA;

public class AdminDeleteUserDTO {

    private int idUser;
    private String username;
    private UserTypeISA userTypeISA;

    public AdminDeleteUserDTO(){}

    public AdminDeleteUserDTO(int idUser, String username, UserTypeISA userTypeISA) {
        this.idUser = idUser;
        this.username = username;
        this.userTypeISA = userTypeISA;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserTypeISA getUserTypeISA() {
        return userTypeISA;
    }

    public void setUserTypeISA(UserTypeISA userTypeISA) {
        this.userTypeISA = userTypeISA;
    }
}
