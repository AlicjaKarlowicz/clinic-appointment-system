package ts.project.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.project.clinic.model.entity.AppointmentStatus;
import ts.project.clinic.model.entity.BookedAppointment;
import ts.project.clinic.model.entity.Patient;

import java.util.List;

/**
 * repository class extending JpaRepository for BookedAppointment class
 * @author alicj
 */
public interface BookedAppointmentRepository extends JpaRepository<BookedAppointment, Integer> {

    /**
     * find by patient id and appointment status
     * method for finding past and booked appointments
     * @param id
     * @param appointmentStatus
     * @return List<BookedAppointment>
     */
    List<BookedAppointment> findByPatient_IdAndAppointment_Status(Integer id, AppointmentStatus appointmentStatus);

    /**
     * find booked appointment for given appointment id
     * @param id
     * @return BookedAppointment
     */
    BookedAppointment findByAppointment_Id(Integer id);
}
