package utkucuval.hospitalproject.services;

import org.springframework.stereotype.Service;
import utkucuval.hospitalproject.entity.Laborant;
import utkucuval.hospitalproject.repository.LaborantRepository;
import utkucuval.hospitalproject.request.LaborantCreateRequest;

import java.util.List;

@Service
public class LaborantService {
    private LaborantRepository laborantRepository;

    public LaborantService(LaborantRepository laborantRepository) {
        this.laborantRepository = laborantRepository;
    }

    public Laborant getOneLaborantById(Long laborantId) {
        return laborantRepository.findById(laborantId).orElse(null);
    }

    public Laborant getOneLaborant(Long idOfLaborant) {
        return laborantRepository.findById(idOfLaborant).orElse(null);
    }

    public List<Laborant> getAllLaborant() {
        return laborantRepository.findAll();
    }

    //By converting the Laborant Create Request object to the Laboratory object, it saves the Laboratory object to the database.
    public Laborant createOneLaborant(LaborantCreateRequest laborant) {
        Laborant toSave = new Laborant();
        toSave.setLaborantName(laborant.getLaborantName());
        toSave.setLaborantLastName(laborant.getLaborantLastName());
        return laborantRepository.save(toSave);
    }
}
