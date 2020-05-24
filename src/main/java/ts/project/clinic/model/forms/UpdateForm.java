package ts.project.clinic.model.forms;

import ts.project.clinic.model.forms.contrait.FieldMatch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * update password form class with custom field match annotation for new password confirm
 * @author alicj
 */
@FieldMatch(first = "newPassword", second = "confirmPassword", message = "The password fields must match")
public class UpdateForm {


    @Size(min = 6, message = "Password too short")
    @NotBlank(message = "Enter old password")
    private String oldPassword;

    @Size(min = 6, message = "Password too short")
    @NotBlank(message = "Enter new password")
    private String newPassword;


    @Size(min = 6, message = "Password too short")
    @NotBlank(message = "Confirm new password")
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
