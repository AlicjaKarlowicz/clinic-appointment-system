package ts.project.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ts.project.clinic.model.entity.Appointment;
import ts.project.clinic.model.entity.Doctor;
import ts.project.clinic.model.entity.Patient;
import ts.project.clinic.model.entity.Workplace;

import java.time.LocalDate;
import java.util.List;

/**
 * repository extending JpaRepository for Appointment class
 * @author alicj
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {


    /**
     * find appointment by doctor and workplace and app date for booking appointments
     * represents sql query searching for entity in table
     * @param doctor
     * @param workplace
     * @param appdate
     * @returnn List<Appointment> </Appointment>
     */
    List<Appointment> findByDoctorAndWorkplaceAndAppDate(Doctor doctor, Workplace workplace, LocalDate appdate);

}
