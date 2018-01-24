package wow.itc.com.wow_itc;

/**
 * Created by Karthik on 1/19/2018.
 */

public class Propagation {
    private String personame,phonenumber,email,schoolconfirmed,strength,attened,IEC,otherbenifits,tree,water,others,note;
    Propagation()
    {}

    public Propagation(String personame, String phonenumber, String email, String schoolconfirmed, String strength, String attened, String IEC, String otherbenifits, String tree, String water, String others, String note) {
        this.personame = personame;
        this.phonenumber = phonenumber;
        this.email = email;
        this.schoolconfirmed = schoolconfirmed;
        this.strength = strength;
        this.attened = attened;
        this.IEC = IEC;
        this.otherbenifits = otherbenifits;
        this.tree = tree;
        this.water = water;
        this.others = others;
        this.note = note;
    }

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

    public String getAttened() {
        return attened;
    }

    public void setAttened(String attened) {
        this.attened = attened;
    }

    public String getIEC() {
        return IEC;
    }

    public void setIEC(String IEC) {
        this.IEC = IEC;
    }

    public String getOtherbenifits() {
        return otherbenifits;
    }

    public void setOtherbenifits(String otherbenifits) {
        this.otherbenifits = otherbenifits;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
