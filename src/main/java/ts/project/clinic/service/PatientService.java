package ts.project.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ts.project.clinic.exception.ResourceNotFound;
import ts.project.clinic.model.entity.Patient;
import ts.project.clinic.repository.PatientRepository;

/**
 * service class for Patient class
 * @author alicj
 */
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * save patient
     * @param patient
     */
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }


}
