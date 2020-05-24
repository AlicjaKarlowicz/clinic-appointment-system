package ts.project.clinic.model.forms;

/**
 * update info form
 * @author alicj
 */
public class InfoUpdateForm {

    private String name;

    private String address;

    private Integer phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
