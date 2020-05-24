package ts.project.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ts.project.clinic.model.entity.Specialization;
import ts.project.clinic.repository.SpecializationRepository;

import java.util.List;

/**
 * service class for Specialization
 * @author alicj
 */
@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    /**
     * get all specializations
     * @return List<Specialization>
     */
    public List<Specialization> getAll() {
        return specializationRepository.findAll();
    }


}
