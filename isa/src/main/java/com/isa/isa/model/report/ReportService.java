package com.isa.isa.model.report;

import com.isa.isa.model.Client;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.report.DTO.NewReportDTO;
import com.isa.isa.model.report.DTO.ReportAdminViewDTO;
import com.isa.isa.model.report.model.Report;
import com.isa.isa.model.termins.model.*;
import com.isa.isa.model.termins.repository.*;
import com.isa.isa.repository.InstructorRepository;
import com.isa.isa.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private BoatReservationRepository boatReservationRepository;

    @Autowired
    private BoatFastReservationRepository boatFastReservationRepository;

    @Autowired
    private CottageReservationRepository cottageReservationRepository;

    @Autowired
    private CottageFastReservationRepository cottageFastReservationRepository;

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

    public ArrayList<ReportAdminViewDTO> getReportsForAdmin() {
        ArrayList<ReportAdminViewDTO> reportAdminView = new ArrayList<>();
        for(Report report : reportRepository.findAll()){
            if(report.getSanctionClient()){
                reportAdminView.add(new ReportAdminViewDTO(getReservationNameFromReport(report), report));
            }
        }
        return reportAdminView;
    }

    private String getReservationNameFromReport(Report report){
        if(report.getReservationType() == OwnerType.INSTRUCTOR){
            if(report.getTermType() == TermType.RESERVATION){
                Optional<InstructorReservation> instructorReservationOptional = instructorReservationRepository.findById(report.getReservationId());
                if(instructorReservationOptional.isPresent()){
                    return instructorReservationOptional.get().getAdventure().getName();
                }
            } else if(report.getTermType() == TermType.FAST_RESERVATION){
                InstructorFastReservation instructorFastReservation = instructorFastReservationRepository.getByIdWithHistory(report.getReservationId());
                if(instructorFastReservation != null){
                    return instructorFastReservation.getAdventure().getName();
                }
            }
        }else if(report.getReservationType() == OwnerType.BOAT_OWNER){
            if(report.getTermType() == TermType.RESERVATION){
                Optional<BoatReservations> boatReservationsOptional = boatReservationRepository.findById(report.getReservationId());
                if(boatReservationsOptional.isPresent()){
                    return boatReservationsOptional.get().getBoat().getName();
                }
            } else if(report.getTermType() == TermType.FAST_RESERVATION){
                Optional<BoatFastReservation> boatFastReservationOptional = boatFastReservationRepository.findById(report.getReservationId());
                if(boatFastReservationOptional.isPresent()){
                    return boatFastReservationOptional.get().getBoat().getName();
                }
            }
        }else if(report.getReservationType() == OwnerType.COTTAGE_OWNER){
            if(report.getTermType() == TermType.RESERVATION){
                Optional<CottageReservations> cottageReservationsOptional = cottageReservationRepository.findById(report.getReservationId());
                if(cottageReservationsOptional.isPresent()){
                    return cottageReservationsOptional.get().getCottage().getName();
                }
            } else if(report.getTermType() == TermType.FAST_RESERVATION){
                Optional<CottageFastReservation> cottageFastReservationOptional = cottageFastReservationRepository.findById(report.getReservationId());
                if(cottageFastReservationOptional.isPresent()){
                    return cottageFastReservationOptional.get().getCottage().getName();
                }
            }
        }

        return "";
    }
}
