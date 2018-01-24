package wow.itc.com.wow_itc;

/**
 * Created by Karthik on 1/19/2018.
 */

public class Visit {
    public Visit() {
    }

    private String personame;
    private String phonenumber;
    private String email;

    public Visit(String personame, String phonenumber, String email, String schoolname, String schoolconfirmed, String strength) {
        this.personame = personame;
        this.phonenumber = phonenumber;
        this.email = email;
        this.schoolname = schoolname;
        this.schoolconfirmed = schoolconfirmed;
        this.strength = strength;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    private String schoolname;
    private String schoolconfirmed;
    private String strength;



    public String getPersoname() {
        return personame;
    }

    public void setPersoname(String personame) {
        this.personame = personame;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolconfirmed() {
        return schoolconfirmed;
    }

    public void setSchoolconfirmed(String schoolconfirmed) {
        this.schoolconfirmed = schoolconfirmed;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }
}
