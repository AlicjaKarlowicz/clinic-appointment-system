package ts.project.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ts.project.clinic.exception.ResourceNotFound;
import ts.project.clinic.model.entity.Appointment;
import ts.project.clinic.model.entity.AppointmentStatus;
import ts.project.clinic.model.entity.BookedAppointment;
import ts.project.clinic.model.entity.Patient;
import ts.project.clinic.repository.BookedAppointmentRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * service class for BookedAppointment
 * @author alicj
 */
@Service
public class BookedAppointmentService {

    @Autowired
    private BookedAppointmentRepository bookedAppointmentRepository;


    /**
     * find booked appointments for patient id
     * @param id
     * @return List<Appointment>
     */
    public List<Appointment> finBookedByPatient(Integer id) {
        List<BookedAppointment> bookedAppointments = bookedAppointmentRepository.findByPatient_IdAndAppointment_Status(id, AppointmentStatus.BOOKED);

        List<Appointment> appointments = new ArrayList<>();

        for (BookedAppointment booked : bookedAppointments) {
            appointments.add(booked.getAppointment());

        }

        return appointments;
    }

    /**
     * find past appointments for patient id
     * @param id
     * @return List<Appointment>
     */
    public List<Appointment> finPastByPatient(Integer id) {
        List<BookedAppointment> bookedAppointments = bookedAppointmentRepository.findByPatient_IdAndAppointment_Status(id, AppointmentStatus.PAST);


        List<Appointment> appointments = new ArrayList<>();

        for (BookedAppointment booked : bookedAppointments) {

            appointments.add(booked.getAppointment());

        }

        return appointments;
    }


    /**
     * find booked appointment by id
     * @param id
     * @return BookedAppointment
     */
    public BookedAppointment find(Integer id) {
        return bookedAppointmentRepository.findById(id).orElseThrow(ResourceNotFound::new);
    }

    /**
     * delete booked appointment from the table
     * @param bookedAppointment
     */
    public void delete(BookedAppointment bookedAppointment) {
        bookedAppointmentRepository.delete(bookedAppointment);
    }

    /**
     * fing booked appointment by id
     * @param id
     * @return BookedAppointment
     */
    public BookedAppointment findByAppointmentId(Integer id) {
        return bookedAppointmentRepository.findByAppointment_Id(id);
    }


}

