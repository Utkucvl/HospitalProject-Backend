package utkucuval.hospitalproject.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import utkucuval.hospitalproject.entity.Laborant;
import utkucuval.hospitalproject.entity.Report;
import utkucuval.hospitalproject.exceptions.LaborantNotFoundException;
import utkucuval.hospitalproject.exceptions.ReportNotUpdatedException;
import utkucuval.hospitalproject.repository.LaborantRepository;
import utkucuval.hospitalproject.repository.ReportRepository;
import utkucuval.hospitalproject.request.ReportCreateRequest;
import utkucuval.hospitalproject.request.ReportUpdateRequest;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    private ReportRepository reportRepository;
    private LaborantService laborantService;

    public ReportService(ReportRepository reportRepository , LaborantService laborantService) {
        this.reportRepository = reportRepository;
        this.laborantService = laborantService;
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }


    public Report getReportById(Long reportId) {
        return reportRepository.findById(reportId).orElse(null);
    }

    //By assigning the information in ReportCreateRequest to an object created from the Report class,
    // creates an object created from a Report class and saves this object to the database
    public Report createOneReport(ReportCreateRequest report) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis+10800000);
        Report toSave = new Report();
        toSave.setDiagnosis(report.getDiagnosis());
        toSave.setCreatedDate(date);
        toSave.setLaborant(laborantService.getOneLaborant(report.getIdOfLaborant()));
        toSave.setPatientName(report.getPatientName());
        toSave.setPatientNo(report.getPatientNo());
        toSave.setPatientLastName(report.getPatientLastName());
        toSave.setDetailOfDiagnosis(report.getDetailOfDiagnosis());
        toSave.setPhotoUrl(report.getPhotoUrl());

        return reportRepository.save(toSave);
    }

    // It receives the information in the ReportUpdateRequest and sends this information to the object created from
    //the Report class to be updated, and saves a newly created Report class object to the database in an updated form.
    public Report updateOneReport(ReportUpdateRequest report , Long reportId) {
        Report updateReport = reportRepository.findById(reportId).orElse(null);
        if(updateReport != null){
            updateReport.setPatientLastName(report.getPatientLastName());
            updateReport.setDiagnosis(report.getDiagnosis());
            updateReport.setPatientName(report.getPatientName());
            updateReport.setDetailOfDiagnosis(report.getDetailOfDiagnosis());
            updateReport.setPhotoUrl(report.getPhotoUrl());

        }
        return reportRepository.save((updateReport));

    }

    public void deleteOneReportById(Long reportId) {
        reportRepository.deleteById(reportId);
    }


}
