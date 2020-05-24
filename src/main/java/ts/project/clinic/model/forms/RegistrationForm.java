package ts.project.clinic.model.forms;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * registration form class
 * @author alicj
 */
public class RegistrationForm {


    @NotBlank(message = "Enter name")
    private String name;

    @NotBlank(message = "Enter email")
    @Email(message = "Email not valid")
    @Size(min = 10, message = "Email too short")
    private String email;

    @NotBlank(message = "Enter password")
    @Size(min = 6, message = "Password too short")
    private String password;

    @NotBlank(message = "Choose gender")
    private String gender;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotNull
    private LocalDate birth;

    @Positive

    private int phone;

    @NotBlank
    private String address;

    public RegistrationForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
