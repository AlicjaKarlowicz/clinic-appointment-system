package ts.project.clinic.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "specializations")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String specialization;

    @OneToMany(
            mappedBy = "specialization",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Doctor> doctors;

    public Specialization() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
        for (Doctor doctor : doctors) {
            doctor.setSpecialization(this);
        }
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", specialization='" + specialization + '\'' +
                ", doctors=" + doctors +
                '}';
    }
}
