package com.isa.isa.model.complaints.DTO;

import com.isa.isa.model.complaints.model.Complaint;
import com.isa.isa.model.enums.UserTypeISA;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.termins.model.StatusOfComplaint;

import java.time.LocalDateTime;

public class ComplaintAdminViewDTO {
    private int id;
    private String comment;
    private String userEmail;
    private LocalDateTime createdAt;

    private UserTypeISA providerType; //type of user {instructor / owner}
    private String providerEmail; //
    private RevisionType revisionType;
    private int entityId; // adventure / cottage / boat id

    private StatusOfComplaint statusOfComplaint;
    private String adminEmail;
    private String adminResponse;
    private LocalDateTime adminResponsDate;

    public ComplaintAdminViewDTO(){}

    public ComplaintAdminViewDTO(Complaint complaint){
        this.id = complaint.getId();
        this.comment = complaint.getComment();
        this.userEmail = complaint.getUserEmail();
        this.createdAt = complaint.getCreatedAt();
        this.providerType = complaint.getProviderType();
        this.providerEmail = complaint.getProviderEmail();
        this.revisionType = complaint.getRevisionType();
        this.entityId = complaint.getEntityId();
        this.statusOfComplaint = complaint.getStatusOfComplaint();
        this.adminEmail = complaint.getAdminEmail();
        this.adminResponsDate = complaint.getAdminResponsDate();
        this.adminResponse = complaint.getAdminResponse();
    }

    public ComplaintAdminViewDTO(int id, String comment, String userEmail, LocalDateTime createdAt, UserTypeISA providerType, String providerEmail, RevisionType revisionType, int entityId, StatusOfComplaint statusOfComplaint, String adminEmail, String adminResponse, LocalDateTime adminResponsDate) {
        this.id = id;
        this.comment = comment;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.providerType = providerType;
        this.providerEmail = providerEmail;
        this.revisionType = revisionType;
        this.entityId = entityId;
        this.statusOfComplaint = statusOfComplaint;
        this.adminEmail = adminEmail;
        this.adminResponse = adminResponse;
        this.adminResponsDate = adminResponsDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserTypeISA getProviderType() {
        return providerType;
    }

    public void setProviderType(UserTypeISA providerType) {
        this.providerType = providerType;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public RevisionType getRevisionType() {
        return revisionType;
    }

    public void setRevisionType(RevisionType revisionType) {
        this.revisionType = revisionType;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public StatusOfComplaint getStatusOfComplaint() {
        return statusOfComplaint;
    }

    public void setStatusOfComplaint(StatusOfComplaint statusOfComplaint) {
        this.statusOfComplaint = statusOfComplaint;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public LocalDateTime getAdminResponsDate() {
        return adminResponsDate;
    }

    public void setAdminResponsDate(LocalDateTime adminResponsDate) {
        this.adminResponsDate = adminResponsDate;
    }

    public String getAdminResponse() {
        return adminResponse;
    }

    public void setAdminResponse(String adminResponse) {
        this.adminResponse = adminResponse;
    }
}
