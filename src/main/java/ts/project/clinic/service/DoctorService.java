package ts.project.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ts.project.clinic.model.entity.Doctor;
import ts.project.clinic.model.entity.Specialization;
import ts.project.clinic.repository.DoctorRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * service for Doctor class
 * @author alicj
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * find doctor by specialization
     * @param id
     * @return List<Doctor>
     */
    public List<Doctor> findBySpecialization(Integer id){
       return doctorRepository.findAllBySpecialization_Id(id);
    }
}
