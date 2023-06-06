package utkucuval.hospitalproject.request;

import lombok.Data;


//It holds the variables required when updating the report object.
@Data
public class ReportUpdateRequest {

    String patientName;

    String patientLastName;

    String diagnosis;

    String detailOfDiagnosis;

    String photoUrl;
}
