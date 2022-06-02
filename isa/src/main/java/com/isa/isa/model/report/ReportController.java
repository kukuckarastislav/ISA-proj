package com.isa.isa.model.report;

import com.isa.isa.model.report.DTO.NewReportDTO;
import com.isa.isa.model.report.DTO.ReportAdminResponseDTO;
import com.isa.isa.model.report.DTO.ReportAdminViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/instructor")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public ResponseEntity<Boolean> postInstructorReport(Principal user, @RequestBody NewReportDTO newReportDTO){
        if(reportService.postInstructorReport(user.getName(), newReportDTO)){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        }else{
            return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ArrayList<ReportAdminViewDTO>> getReportsForAdmin(Principal user){
        return new ResponseEntity<ArrayList<ReportAdminViewDTO>>(reportService.getReportsForAdmin(),HttpStatus.OK);
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> postAdminReport(Principal user, @RequestBody ReportAdminResponseDTO reportAdminResponseDTO){
        return new ResponseEntity<Boolean>(reportService.postAdminReport(user.getName(), reportAdminResponseDTO), HttpStatus.OK);
    }


}
