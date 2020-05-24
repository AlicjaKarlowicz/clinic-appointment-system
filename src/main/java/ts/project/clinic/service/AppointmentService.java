package ts.project.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ts.project.clinic.model.entity.*;
import ts.project.clinic.repository.AppointmentRepository;
import ts.project.clinic.repository.BookedAppointmentRepository;
import ts.project.clinic.repository.DoctorRepository;
import ts.project.clinic.repository.WorkplaceRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * srvice class for Appointment
 * @author alicj
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BookedAppointmentRepository bookedAppointmentRepository;


    /**
     * get appointments list matching the doctor, workplace and date
     * @param doctor
     * @param workplace
     * @param date
     * @return List<Appointment>
     */
    public List<Appointment> getAppointment(Doctor doctor, Workplace workplace, LocalDate date) {
       List<Appointment> appointmentList = appointmentRepository.findByDoctorAndWorkplaceAndAppDate(doctor, workplace, date);

       List<Appointment> freeAppointments = new ArrayList<>();
        for (Appointment appointment: appointmentList) {
            if (appointment.getStatus().equals(AppointmentStatus.FREE))
                freeAppointments.add(appointment);
        }
      return freeAppointments;
    }

    /**
     * update Appointment status
     * @param appointmentId
     * @param status
     * @return Appointment
     */
    public Appointment updateStatus(Integer appointmentId, AppointmentStatus status) {

        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);

        if(appointment.isPresent()){
            if(appointment.get().getStatus() != null){
                appointment.get().setStatus(status);
            }
            return appointmentRepository.save(appointment.get());
        }
        return null;
    }

    /**
     * book appointment, set appointment status to booked and insert into booked_appointments table
     * @param appointmentId
     * @param patient
     * @return BookedAppointment
     */
    public BookedAppointment bookAppointment(Integer appointmentId, Patient patient) {

        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);

        updateStatus(appointmentId,AppointmentStatus.BOOKED);

        BookedAppointment bookedAppointment = new BookedAppointment();
        bookedAppointment.setAppointment(appointment.get());
        bookedAppointment.setPatient(patient);

        return bookedAppointmentRepository.save(bookedAppointment);
    }

    /**
     * chceking for past appointments every 5 minutes since the application starts
     */
    @Scheduled(fixedRate = 300000)
    public void updatePastAppointments() {

        List<Appointment> appointments = appointmentRepository.findAll();

        for (Appointment appointment:appointments) {

            if (LocalDate.now().isEqual(appointment.getAppDate()) &&
                    LocalTime.now().isAfter(appointment.getStartTime())) {
                appointment.setStatus(AppointmentStatus.PAST);
                appointmentRepository.save(appointment);
            }


        }
    }
}
