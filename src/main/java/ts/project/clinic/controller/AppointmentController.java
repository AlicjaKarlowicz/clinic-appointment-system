package ts.project.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ts.project.clinic.model.entity.*;
import ts.project.clinic.model.forms.AppointmentForm;
import ts.project.clinic.model.forms.RegistrationForm;
import ts.project.clinic.service.*;
import ts.project.clinic.service.login.LoginService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alicj
 * Controller  class for appointment booking
 *
 */

@Controller
@Transactional
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private WorkplaceService workplaceService;

    /**
     * Sending form and specialization with workplace list for populating the form
     * @param model
     * @return book-appointments template
     */

    @GetMapping("/bookappointment")
    public String bookAppointment(Model model){
        model.addAttribute("appointmentForm", new AppointmentForm());
        model.addAttribute("specializationList",specializationService.getAll());
        model.addAttribute("workplaceList",workplaceService.getAll());
        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);

        return "book-appointments";
    }

    /**
     * Sending params/inputs from appointment form
     * checking available appointments for parameters and sending them as attribute list
     * @param appointmentForm
     * @param auth
     * @param bindingResult
     * @param model
     * @return book-appointments template
     */

    @PostMapping("/bookappointment")
    public String bookAppointment(@ModelAttribute("appointmentForm") @Valid AppointmentForm appointmentForm, Authentication auth, BindingResult bindingResult, Model model){

        List<Doctor> doctors = doctorService.findBySpecialization(Integer.parseInt(appointmentForm.getSpecialization()));

        List<Appointment> availableAppointments = new ArrayList<>();

        for (Doctor doctor: doctors) {
            List<Appointment> searchedAppointments = appointmentService.getAppointment(doctor,workplaceService.findById(Integer.parseInt(appointmentForm.getLocation())), appointmentForm.getDate());
            if (!searchedAppointments.isEmpty())
                availableAppointments = searchedAppointments;
        }

        model.addAttribute("availableAppointments",availableAppointments);
        model.addAttribute("appointmentForm",appointmentForm);
        model.addAttribute("specializationList",specializationService.getAll());
        model.addAttribute("workplaceList",workplaceService.getAll());
        return "book-appointments";
    }

    /**
     * Getting chosen hour and booking appointment for that hour
     * Redirecting to patient view with updated list appointments
     * @param appointmentForm
     * @param id
     * @param auth
     * @param bindingResult
     * @param model
     * @return appointments endopoint
     */
    @PostMapping("/choosehour")
    public String choosehour(@ModelAttribute("appointmentForm") @Valid AppointmentForm appointmentForm, @RequestParam(value = "id") Integer id, Authentication auth, BindingResult bindingResult, Model model){
        String user = auth.getName();
        LoginData loginData = loginService.findByEmail(user);
        Patient patient = loginData.getPatient();

        appointmentService.bookAppointment(id,patient);

        return "redirect:/appointments";
    }



}
