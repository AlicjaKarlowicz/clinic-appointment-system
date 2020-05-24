package ts.project.clinic.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "workplaces")
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "workplace_name")
    private String workplaceName;



    @OneToMany(
            mappedBy = "workplace",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Appointment> appointments;


    public Workplace() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkplaceName() {
        return workplaceName;
    }

    public void setWorkplaceName(String workplaceName) {
        this.workplaceName = workplaceName;
    }


    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
        for (Appointment appointment: appointments) {
            appointment.setWorkplace(this);

        }
    }


}
