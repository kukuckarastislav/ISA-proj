package com.isa.isa.model.report.DTO;

import com.isa.isa.model.termins.model.StatusOfRevision;

public class ReportAdminResponseDTO {

    private int idReport;
    private String comment;
    private StatusOfRevision statusOfReport;

    public ReportAdminResponseDTO(){}

    public ReportAdminResponseDTO(int idReport, String comment, StatusOfRevision statusOfReport) {
        this.idReport = idReport;
        this.comment = comment;
        this.statusOfReport = statusOfReport;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public StatusOfRevision getStatusOfReport() {
        return statusOfReport;
    }

    public void setStatusOfReport(StatusOfRevision statusOfReport) {
        this.statusOfReport = statusOfReport;
    }
}
