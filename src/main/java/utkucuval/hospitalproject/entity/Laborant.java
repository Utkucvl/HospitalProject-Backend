package utkucuval.hospitalproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "laborant")
public class Laborant {

    //Although the name of the variable is id, this variable represents the laboratory number
    //I used the sequence generator to start the laboratory number from the seven-digit number in the database
    @Id
    @SequenceGenerator(name = "lab_id_creator",allocationSize = 2,initialValue = 1000000)
    @GeneratedValue(generator = "lab_id_creator" , strategy = GenerationType.SEQUENCE)
    Long id;

    String laborantName;

    String laborantLastName;

}
