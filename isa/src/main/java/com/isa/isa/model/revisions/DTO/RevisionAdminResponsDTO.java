package com.isa.isa.model.revisions.DTO;

import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.revisions.model.RevisionType;

public class RevisionAdminResponsDTO {

    private String adminUsername;
    private Boolean approve;    //true - approve; false - reject
    private int idRevision;
    private OwnerType ownerType;
    private RevisionType revisionType;

    public RevisionAdminResponsDTO(){}

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public Boolean isApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public RevisionType getRevisionType() {
        return revisionType;
    }

    public void setRevisionType(RevisionType revisionType) {
        this.revisionType = revisionType;
    }
}
