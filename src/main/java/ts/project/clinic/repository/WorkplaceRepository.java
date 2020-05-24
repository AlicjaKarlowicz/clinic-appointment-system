package ts.project.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ts.project.clinic.model.entity.Workplace;

/**
 * repository class extending JpaRepository for Workplace class
 * @author alicj
 */
@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Integer> {

}
