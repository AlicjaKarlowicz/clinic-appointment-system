package ts.project.clinic.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workplace_id", referencedColumnName = "id")
    private Workplace workplace;

    @Column(name = "app_date")
    private LocalDate appDate;

    @Column(name = "start_time")
    private LocalTime startTime;
    private AppointmentStatus status;

    @OneToOne(mappedBy = "appointment")
    private BookedAppointment bookedAppointment;


    public Appointment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getAppointments().add(this);
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
        workplace.getAppointments().add(this);
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public void setAppDate(LocalDate appDate) {
        this.appDate = appDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public BookedAppointment getBookedAppointment() {
        return bookedAppointment;
    }

    public void setBookedAppointment(BookedAppointment bookedAppointment) {
        this.bookedAppointment = bookedAppointment;
    }
}
