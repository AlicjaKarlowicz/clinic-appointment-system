package ts.project.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ts.project.clinic.model.entity.Doctor;
import java.util.List;

/**
 * repository class extending JpaRepository for Doctor class
 * @author alicj
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    /**
     * find doctors for requested specialization
     * executing sql query
     * @param id
     * @return List<Doctor>
     */
    List<Doctor> findAllBySpecialization_Id(Integer id);
}
