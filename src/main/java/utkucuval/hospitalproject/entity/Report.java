package utkucuval.hospitalproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="report")
public class Report {

    //Although the name of the variable is id, this variable represents the file number.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String patientName;

    String patientLastName;

    Long patientNo;

    String diagnosis;

    String detailOfDiagnosis;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_of_laborant",nullable = false)
    Laborant laborant;


    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    String photoUrl;


}
