package ts.project.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ts.project.clinic.exception.InvalidNameOrPasswordException;
import ts.project.clinic.model.entity.*;
import ts.project.clinic.model.forms.InfoUpdateForm;
import ts.project.clinic.model.forms.UpdateForm;
import ts.project.clinic.service.AppointmentService;
import ts.project.clinic.service.BookedAppointmentService;
import ts.project.clinic.service.PatientService;
import ts.project.clinic.service.login.LoginService;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for patient panel
 * @author alicj
 */
@Controller
public class PatientController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private BookedAppointmentService bookedAppointmentService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * send patient attributes to patient-view template
     * find past and present appointments and send them to template
     * @param model
     * @param auth
     * @return patient-view template
     */
    @GetMapping("/appointments")
    public String getAppointments(Model model, Authentication auth) {
        String user = auth.getName();
        LoginData loginData = loginService.findByEmail(user);
        Patient patient = loginData.getPatient();

        List<Appointment> appointmentListPast = bookedAppointmentService.finPastByPatient(patient.getId());
        List<Appointment> appointmentListUpcoming = bookedAppointmentService.finBookedByPatient(patient.getId());



        model.addAttribute("patient",patient);
        model.addAttribute("pastAppointments",appointmentListPast);
        model.addAttribute("upcomingAppointments",appointmentListUpcoming);
        model.addAttribute("appointment",new Appointment());
        return "patient-view";
    }

    /**
     * cancel appointment, delete from bookedappointments table
     * @param id
     * @return appointments endpoint
     */
    @PostMapping("/deleteAppointment")
    public String cancelAppointment(@RequestParam Integer id)
    {
        BookedAppointment bookedAppointment = bookedAppointmentService.findByAppointmentId(id);

        appointmentService.updateStatus(id, AppointmentStatus.FREE);
        bookedAppointmentService.delete(bookedAppointment);

        return "redirect:/appointments";
    }

    /**
     * display change password form
     * @param model
     * @return change-password template
     */
    @GetMapping("/changePassword")
    public String showUpdateFormPassword(Model model) {
        model.addAttribute("updateForm", new UpdateForm());
        return "change-password";

    }

    /**
     * change password from sent data UpdateForm
     * @param auth
     * @param updateForm
     * @param bindingResult
     * @param model
     * @return logout endpoint
     */
    @PostMapping("/changePassword")
    public String updatePatientPassword( Authentication auth,@ModelAttribute("updateForm") @Valid UpdateForm updateForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "change-password";
        }

        String user = auth.getName();
        LoginData loginData = loginService.findByEmail(user);

        if (bCryptPasswordEncoder.matches(updateForm.getOldPassword(),loginData.getPassword())){
              if  (updateForm.getNewPassword().equals(updateForm.getConfirmPassword())) {
                  loginData.setPassword(updateForm.getNewPassword());
              } else
                  throw new InvalidNameOrPasswordException("Passwords are not the same");
        } else
            throw new InvalidNameOrPasswordException("Old password does not match");

        loginService.save(loginData);

        return "redirect:/logout";
    }

    /**
     * display change info form
     * @param model
     * @param auth
     * @return change-info template
     */
    @GetMapping("/changeInfo")
    public String showUpdateInfoForm(Model model,  Authentication auth) {
        String user = auth.getName();
        LoginData loginData = loginService.findByEmail(user);
        Patient patient = loginData.getPatient();

        model.addAttribute("updateForm", new InfoUpdateForm());
        model.addAttribute("patient",patient);
        return "change-info";

    }

    /**
     * update info with data from form
     * @param auth
     * @param updateForm
     * @param bindingResult
     * @param model
     * @return appointments endpoint redirect
     */
    @PostMapping("/changeInfo")
    public String updatePatientInfo( Authentication auth,@ModelAttribute("updateForm") @Valid InfoUpdateForm updateForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "change-info";
        }

        String user = auth.getName();
        LoginData loginData = loginService.findByEmail(user);
        Patient patient = loginData.getPatient();

        if (!updateForm.getAddress().isEmpty())
            patient.setAddress(updateForm.getAddress());
        if (updateForm.getPhone() != null)
            patient.setPhone(updateForm.getPhone());
        if (!updateForm.getName().isEmpty())
            patient.setPatientName(updateForm.getName());

        patientService.savePatient(patient);


        return "redirect:/appointments";
    }
}
