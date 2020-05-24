package ts.project.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ts.project.clinic.model.entity.Specialization;

/**
 * repository class extending JpaRepository for Specialization class
 * @author alicj
 */
@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {


}
