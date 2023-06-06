package utkucuval.hospitalproject.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import utkucuval.hospitalproject.entity.Laborant;
import utkucuval.hospitalproject.entity.Report;

import java.util.Date;

//It is a class created because we do not need every information in the database in the frontend part. Although its importance for this project is not clearly understood, for example,
// if we protect a user and the password of the user in the database section.
// We only need to return the user to the frontend, it will be unnecessary and unsafe to return the password to the frontend.
@Data
public class ReportResponse {
    Long id ;

    String patientName;

    String patientLastName;

    Long patientNo;

    String diagnosis;

    String detailOfDiagnosis;

    Long idOfLaborant;

    Laborant laborant;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date createdDate;

    String photoUrl;
    public ReportResponse(Report report){
        this.id=report.getId();
        this.patientName = report.getPatientName();
        this.patientLastName = report.getPatientLastName();
        this.patientNo= report.getPatientNo();
        this.diagnosis = report.getDiagnosis();
        this.detailOfDiagnosis = report.getDetailOfDiagnosis();
        this.idOfLaborant = report.getLaborant().getId();
        this.createdDate = report.getCreatedDate();
        this.photoUrl = report.getPhotoUrl();
        this.laborant=report.getLaborant();

    }
}
