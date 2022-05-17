package com.isa.isa.model.revisions.DTO;

import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.revisions.model.Revision;
import com.isa.isa.model.termins.model.StatusOfRevision;

import java.time.LocalDateTime;

public class RevisionAdminViewDTO {

    private RevisionType revisionType;
    private OwnerType ownerType;

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

    public RevisionAdminViewDTO(RevisionType revisionType, String entityName, String ownerUsername, OwnerType ownerType, String clientUsername, Revision revision){
        this.revisionType = revisionType;
        this.ownerType = ownerType;
        this.entityName = entityName;
        this.ownerUsername = ownerUsername;
        this.clientUsername = clientUsername;
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

    public RevisionType getRevisionType() {
        return revisionType;
    }

    public void setRevisionType(RevisionType revisionType) {
        this.revisionType = revisionType;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }
}
