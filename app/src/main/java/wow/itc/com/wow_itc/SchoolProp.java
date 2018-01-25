package wow.itc.com.wow_itc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

public class SchoolProp extends AppCompatActivity {
    DataBaseHelpher helpher;
    List<Visit> dbList;
    int position;
    public EditText scname;
    public EditText person;
    public EditText phone;
    public EditText mail;
    public CheckBox scconfirm;

    public EditText studentstrength;

    public EditText note;
    public EditText ngoempname;
    public EditText area;
    public EditText city;
    public EditText ngo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_prop);
    }
}
