package com.isa.isa.model.report.DTO;

import com.isa.isa.model.report.model.Report;

public class ReportAdminViewDTO {
    private String entityName;
    private Report report;

    public ReportAdminViewDTO(){}
    public ReportAdminViewDTO(String entityName, Report report) {
        this.entityName = entityName;
        this.report = report;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
