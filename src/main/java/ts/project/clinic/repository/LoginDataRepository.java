package ts.project.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ts.project.clinic.model.entity.LoginData;

/**
 * repository class extending JpaRepository for LoginData class
 * @author alicj
 */
@Repository
public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {

   /**
    * find LoginData by email
    * @param email
    * @return LoginData
    */
   LoginData findByEmail(String email);
}
