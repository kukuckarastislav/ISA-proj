package com.isa.isa.model.revisions.DTO;

import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.Client;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.termins.model.Revision;
import com.isa.isa.model.termins.model.StatusOfRevision;

import java.time.LocalDateTime;

public class RevisionAdminViewDTO {

    private String entityName; // name of adventure/cottage/boat
    private String ownerUsername; //name of instructor/cottageOwner/boatOwner

    private int idRevision;
    private StatusOfRevision statusOfRevision;
    private String clientUsername;
    private int grade;
    private String comment;
    private LocalDateTime revisionCreatedAt;

    private String adminUsername; 	//admin who REJECTED or APPROVED revision
    private LocalDateTime adminResponsDate;

    public RevisionAdminViewDTO(){}

    public RevisionAdminViewDTO(Adventure adventure, Revision revision, Client client) {
        this.entityName = adventure.getName();
        this.ownerUsername = adventure.getInstructor().getEmail();
        this.clientUsername = "TODO:RESITI";//client.getEmail();
        initDTO(revision);
    }

    public RevisionAdminViewDTO(Cottage cottage, Revision revision, Client client) {
        this.entityName = cottage.getName();
        this.ownerUsername = cottage.getOwner().getEmail();
        this.clientUsername = "TODO:RESITI";//client.getEmail();
        initDTO(revision);
    }

    public RevisionAdminViewDTO(Boat boat, Revision revision, Client client) {
        this.entityName = boat.getName();
        this.ownerUsername = boat.getOwner().getEmail();
        this.clientUsername = "TODO:RESITI";//client.getEmail();
        initDTO(revision);
    }

    private void initDTO(Revision revision){
        this.revisionCreatedAt = revision.getCreatedAt();
        this.idRevision = revision.getId();
        this.statusOfRevision = revision.getStatusOfRevision();
        this.grade = revision.getGrade();
        this.comment = revision.getComment();
        if(this.statusOfRevision != StatusOfRevision.PENDING){
            this.adminUsername = revision.getAdminUsername();
            this.adminResponsDate = revision.getAdminResponsDate();
        }else{
            this.adminUsername = "";
            this.adminResponsDate = null;
        }
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public StatusOfRevision getStatusOfRevision() {
        return statusOfRevision;
    }

    public void setStatusOfRevision(StatusOfRevision statusOfRevision) {
        this.statusOfRevision = statusOfRevision;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public LocalDateTime getAdminResponsDate() {
        return adminResponsDate;
    }

    public void setAdminResponsDate(LocalDateTime adminResponsDate) {
        this.adminResponsDate = adminResponsDate;
    }

    public LocalDateTime getRevisionCreatedAt() {
        return revisionCreatedAt;
    }

    public void setRevisionCreatedAt(LocalDateTime revisionCreatedAt) {
        this.revisionCreatedAt = revisionCreatedAt;
    }
}
