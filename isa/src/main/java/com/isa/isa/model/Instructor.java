package com.isa.isa.model;

import com.isa.isa.DTO.StuffDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="instructor")
public class Instructor extends Person{

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Adventure> adventures = new HashSet<Adventure>();

    @Column(name="registrationMotivation", nullable=true)
    private String registrationMotivation;

    public Instructor(){}

    public Instructor(StuffDTO stuffDTO) {
        super(stuffDTO);
        registrationMotivation = stuffDTO.getRegistrationMotivation();
    }
}
