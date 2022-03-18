package com.isa.isa.model;

import com.isa.isa.DTO.InstructorDTO;
import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.termins.model.InstructorTerms;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="instructor")
public class Instructor extends Person{

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Adventure> adventures = new HashSet<Adventure>();

    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<InstructorTerms> instructorTerms = new HashSet<InstructorTerms>();

    @Column(name="registrationMotivation", nullable=true)
    private String registrationMotivation;
    
    @Column(nullable=true)
	private double averageGrade;

    private String biography;

    public String getRegistrationMotivation() {
        return registrationMotivation;
    }

    public void setRegistrationMotivation(String registrationMotivation) {
        this.registrationMotivation = registrationMotivation;
    }

    public Instructor(){}

    public Instructor(UserDTO userDTO) {
        super(userDTO);
        registrationMotivation = userDTO.getRegistrationMotivation();
    }

    public Set<Adventure> getAdventures() {
        return adventures;
    }

    public void setAdventures(Set<Adventure> adventures) {
        this.adventures = adventures;
    }

    public Set<InstructorTerms> getInstructorTerms() {
        return instructorTerms;
    }

    public void setInstructorTerms(Set<InstructorTerms> instructorTerms) {
        this.instructorTerms = instructorTerms;
    }

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void updateInfo(InstructorDTO instructorDTO){
        super.setEmail(instructorDTO.getEmail());
        super.setFirstName(instructorDTO.getFirstName());
        super.setLastName(instructorDTO.getLastName());
        super.setAddress(instructorDTO.getAddress());
        super.setPhoneNumber(instructorDTO.getPhoneNumber());
    }
}
