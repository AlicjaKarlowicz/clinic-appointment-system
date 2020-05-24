package ts.project.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ts.project.clinic.model.entity.Patient;
import ts.project.clinic.model.entity.LoginData;
import ts.project.clinic.model.forms.RegistrationForm;
import ts.project.clinic.service.login.LoginService;
import ts.project.clinic.service.PatientService;

import javax.validation.Valid;

/**
 * Controller class for logging in and registering users
 * @author alicj
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PatientService patientService;

    /**
     * display index page
     * @param model
     * @return index template
     */

    @GetMapping(value = {"/", "/index"})
    public String welcome(Model model) {
        return "index";
    }

    /**
     * display login page
     * @param model
     * @return login template
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    /**
     * display register page
     * @param model
     * @return registration template
     */
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new RegistrationForm());
        return "registration";
    }


    /**
     * register user to database
     * @param userForm
     * @param bindingResult
     * @param model
     * @return login template
     */
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Valid RegistrationForm userForm, BindingResult bindingResult, Model model) {

        LoginData loginValidate = new LoginData();

        loginService.isRegistered(userForm.getEmail());

        loginValidate.setEmail(userForm.getEmail());
        loginValidate.setPassword(userForm.getPassword());

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Patient patient = new Patient();

        patient.setPatientName(userForm.getName());
        patient.setGender(userForm.getGender());
        patient.setPhone(userForm.getPhone());
        patient.setAddress(userForm.getAddress());
        patient.setDateOfBirth(userForm.getBirth());

        patient.setLoginData(loginValidate);

        loginService.save(loginValidate);
        patientService.savePatient(patient);


        return "redirect:/login";
    }






}
