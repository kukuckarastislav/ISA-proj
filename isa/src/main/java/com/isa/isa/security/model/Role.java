package com.isa.isa.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

// POJO koji implementira Spring Security GrantedAuthority kojim se mogu definisati role u aplikaciji
@Entity
@Table(name="ROLE")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name")
    String name;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isCustomer() { return name.equals("ROLE_CUSTOMER"); }

    public boolean isAdmin() {
        return name.equals("ROLE_ADMIN");
    }

    public boolean isCottageOwner() {
        return name.equals("ROLE_COTTAGE_OWNER");
    }

    public boolean isBoatOwner() {
        return name.equals("ROLE_BOAT_OWNER");
    }

    public boolean isInstructor() {
        return name.equals("ROLE_INSTRUCTOR");
    }

}
