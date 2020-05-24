package ts.project.clinic.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "patient_name")
    private String patientName;
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private int phone;
    private String address;

    @OneToMany(
            mappedBy = "appointment",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<BookedAppointment> bookedAppointments;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "login_data_id", referencedColumnName = "id")
    private LoginData loginData;

    public Patient() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<BookedAppointment> getBookedAppointments() {
        return bookedAppointments;
    }

    public void setBookedAppointments(Set<BookedAppointment> bookedAppointments) {
        this.bookedAppointments = bookedAppointments;
        for (BookedAppointment bookedAppointment: bookedAppointments) {
            bookedAppointment.setPatient(this);
        }
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }
}


