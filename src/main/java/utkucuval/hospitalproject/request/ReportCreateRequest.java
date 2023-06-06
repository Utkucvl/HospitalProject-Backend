package utkucuval.hospitalproject.request;

import lombok.Data;

//It holds the variables required when creating the report object. The purpose of creating this class
//is that some fields are not required to be determined by the user, for example,
// report no can be given as an example of this field.
@Data
public class ReportCreateRequest {

    String patientName;

    String patientLastName;

    Long patientNo;

    String diagnosis;

    String detailOfDiagnosis;

    Long idOfLaborant;

    String photoUrl;
}
