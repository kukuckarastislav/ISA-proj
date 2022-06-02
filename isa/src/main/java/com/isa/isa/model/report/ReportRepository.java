package com.isa.isa.model.report;

import com.isa.isa.model.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository  extends JpaRepository<Report, Integer> {
}
