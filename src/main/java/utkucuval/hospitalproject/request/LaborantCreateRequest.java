package utkucuval.hospitalproject.request;

import lombok.Data;

//This class is a class that holds the fields required to create a laboratory object.
@Data
public class LaborantCreateRequest {
    String laborantName;

    String laborantLastName;
}
