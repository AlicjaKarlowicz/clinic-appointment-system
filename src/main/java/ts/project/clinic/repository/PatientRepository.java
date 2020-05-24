package ts.project.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ts.project.clinic.model.entity.Patient;

/**
 * repository class extending JpaRepository for Patient class
 * @author alicj
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    /**
     * find patient by login data id
     * @param loginData
     * @return Patient
     */
    Patient findByLoginData_Id(Integer loginData);
}
