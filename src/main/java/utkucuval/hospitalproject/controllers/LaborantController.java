package utkucuval.hospitalproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utkucuval.hospitalproject.entity.Laborant;
import utkucuval.hospitalproject.exceptions.LaborantNotCreatedException;
import utkucuval.hospitalproject.exceptions.LaborantNotFoundException;
import utkucuval.hospitalproject.request.LaborantCreateRequest;
import utkucuval.hospitalproject.services.LaborantService;

import java.util.List;

@RestController
@RequestMapping("/laborant")
public class LaborantController {
    private LaborantService laborantService;

    public LaborantController(LaborantService laborantService) {
        this.laborantService = laborantService;
    }

    @GetMapping
    public List<Laborant> getAllLaborant(){
        return laborantService.getAllLaborant();
    }

    @GetMapping("/{laborantId}")
    public Laborant getOneLaborantById(@PathVariable Long laborantId){
        return laborantService.getOneLaborantById(laborantId);
    }

    //Method checks the fields that need to be filled to create a laboratory and creates the laboratory if it is filled
    @PostMapping
    public Laborant createLaborant(@RequestBody LaborantCreateRequest laborant){
        if(laborant.getLaborantName() =="" || laborant.getLaborantLastName() == ""){
            throw new LaborantNotCreatedException();
        }
        return laborantService.createOneLaborant(laborant);
    }

    //Exception Handling
    @ExceptionHandler(LaborantNotCreatedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handleLaborantNotCreatedException() {

    }

}
