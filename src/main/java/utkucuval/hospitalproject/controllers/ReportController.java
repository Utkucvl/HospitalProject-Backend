package utkucuval.hospitalproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utkucuval.hospitalproject.entity.Report;
import utkucuval.hospitalproject.exceptions.LaborantNotFoundException;
import utkucuval.hospitalproject.exceptions.ReportNotCreatedException;
import utkucuval.hospitalproject.exceptions.ReportNotFoundException;
import utkucuval.hospitalproject.exceptions.ReportNotUpdatedException;
import utkucuval.hospitalproject.request.ReportCreateRequest;
import utkucuval.hospitalproject.request.ReportUpdateRequest;
import utkucuval.hospitalproject.response.ReportResponse;
import utkucuval.hospitalproject.services.LaborantService;
import utkucuval.hospitalproject.services.ReportService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController {
    private ReportService reportService;
    private LaborantService laborantService;

    public ReportController(ReportService reportService,LaborantService laborantService) {

        this.reportService = reportService;
        this.laborantService = laborantService;
    }
        @GetMapping
        public List<ReportResponse> getAllReports(){
            List<Report> reports ;

            reports = reportService.getAllReports();

            return reports.stream().map(report -> new ReportResponse(report)).collect(Collectors.toList());
        }
    @GetMapping("/{reportId}")
    public ReportResponse getOneReport(@PathVariable Long reportId){
        //Checks if there is a report according to given id
        Report report = reportService.getReportById(reportId);
        if(report == null) {
            throw new ReportNotFoundException();
        }
        return new ReportResponse(report);
    }
    @PostMapping
    public Report createReport(@RequestBody ReportCreateRequest report){
        //Checks whether the required fields are filled while generating the report.

        if(!isReportValid(report)){
            throw new ReportNotCreatedException();
        }
        //Checks if there is a laboratory according to the laboratory id given in the report.

        else if (laborantService.getOneLaborant(report.getIdOfLaborant()) == null)
            throw new LaborantNotFoundException();
        //Creates a new report
        return reportService.createOneReport(report);
    }
    @PutMapping("/{reportId}")
    public Report updateOneReport(@PathVariable Long reportId , @RequestBody ReportUpdateRequest report){
        // Checks if the required fields to update the report are filled
        if(!isUpdateReportValid(report)){
            throw new ReportNotUpdatedException();
        }

        //Updates report
        return reportService.updateOneReport(report , reportId);
    }
    @DeleteMapping("/{reportId}")
    public void deleteOneReportById(@PathVariable Long reportId){
        //Checks if there is a report according to given id
        Report report = reportService.getReportById(reportId);
        if(report == null){
            throw new ReportNotFoundException();
        }
        else {
            //Deletes report
            reportService.deleteOneReportById(reportId);
        }
    }

    // Method which allow us to check if all required places that are needed to create new report  are filled
    public boolean isReportValid(ReportCreateRequest report){
        if( report.getPatientName() != null && report.getPatientLastName() !=null
        && report.getPatientNo() != null && report.getDiagnosis() != null && report.getDetailOfDiagnosis() !=null
                 && report.getPhotoUrl() !=null && report.getIdOfLaborant() != null)
            return true;

        return false;
    }
    // Method which allow us to check if all required places that are needed to update report are filled
    public boolean isUpdateReportValid(ReportUpdateRequest report ){
        if(report.getPatientName() !="" && report.getPatientLastName()!="" && report.getDiagnosis() !="" && report.getDetailOfDiagnosis()!="" && report.getPhotoUrl() !="")
            return true;

        return false;
    }
    //Exception Handling

    @ExceptionHandler(ReportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleReportNotFoundException() {

    }
    @ExceptionHandler(ReportNotCreatedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handleReportNotCreatedException() {

    }
    @ExceptionHandler(ReportNotUpdatedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handleReportNotUpdatedException() {

    }
    @ExceptionHandler(LaborantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleLaborantNotFoundException() {

    }
}
