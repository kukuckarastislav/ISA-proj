package com.isa.isa.model.report;

import com.isa.isa.model.Client;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.report.DTO.NewReportDTO;
import com.isa.isa.model.report.model.Report;
import com.isa.isa.model.termins.model.InstructorFastReservation;
import com.isa.isa.model.termins.model.InstructorReservation;
import com.isa.isa.model.termins.model.TermType;
import com.isa.isa.model.termins.repository.InstructorFastReservationRepository;
import com.isa.isa.model.termins.repository.InstructorReservationRepository;
import com.isa.isa.repository.InstructorRepository;
import com.isa.isa.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private InstructorReservationRepository instructorReservationRepository;

    @Autowired
    private InstructorFastReservationRepository instructorFastReservationRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private PenaltyService penaltyService;

    public Boolean postInstructorReport(String instructorUsername, NewReportDTO newReportDTO) {

        Instructor instructor = instructorRepository.getByEmail(instructorUsername);
        if(instructor == null) return false;

        Report report = new Report(newReportDTO, instructorUsername);

        if(newReportDTO.getTermType() == TermType.RESERVATION){
            Optional<InstructorReservation> instructorReservationOptional = instructorReservationRepository.findById(newReportDTO.getReservationId());
            if(instructorReservationOptional.isEmpty()) return false;
            InstructorReservation instructorReservation = instructorReservationOptional.get();
            if(!instructorReservation.getClient().getEmail().equals(newReportDTO.getClientEmail())) return false;
            if(instructorReservation.getEndTime().isAfter(LocalDateTime.now())) return false;
            if(instructorReservation.getReport() != null) return false;

            instructorReservation.setReport(report);
            instructorReservationRepository.saveAndFlush(instructorReservation);

        }else if(newReportDTO.getTermType() == TermType.FAST_RESERVATION){
            InstructorFastReservation instructorFastReservation = instructorFastReservationRepository.getByIdWithHistory(newReportDTO.getReservationId());
            if(instructorFastReservation == null) return false;
            Client client = instructorFastReservation.getClientWhoTake();
            if(client == null) return false;    //nema klijenta koji je uzeo ovu brzu rezervaciju, znaci ne moze da se radi report
            if(!client.getEmail().equals(newReportDTO.getClientEmail())) return false;
            if(instructorFastReservation.getEndTime().isAfter(LocalDateTime.now())) return false;
            if(instructorFastReservation.getReport() != null) return false;

            instructorFastReservation.setReport(report);
            instructorFastReservationRepository.saveAndFlush(instructorFastReservation);
        }

        penaltyService.getPenaltyFromReport(report);
        return true;
    }
}
