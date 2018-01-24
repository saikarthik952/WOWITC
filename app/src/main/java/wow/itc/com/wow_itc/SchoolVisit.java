package wow.itc.com.wow_itc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;



import java.util.ArrayList;

public class SchoolVisit extends AppCompatActivity {

    public EditText scname;
    public EditText person;
    public EditText phone;
    public EditText mail;
    public CheckBox scconfirm;
 SharedPreferences msharedpreferences;
 SharedPreferences.Editor editor;
    public EditText studentstrength;

    public EditText note;
    public EditText ngoempname;
    public EditText area;
    public EditText city;
    public EditText ngo;
    public Button schoolvisitsubmit,saved;
    DataBaseHelpher helpher;
public ArrayList<Visit> vx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_school_visit);

        scname = (EditText) findViewById(R.id.schoolname);

        person = (EditText) findViewById(R.id.personcontacted);
        phone = (EditText) findViewById(R.id.phone);
        mail = (EditText) findViewById(R.id.mailid);
       scconfirm=findViewById(R.id.Schoolcon);
       studentstrength=findViewById(R.id.studentstrength);
      ///  note = (EditText) findViewById(R.id.notescvisit);

        area = (EditText) findViewById(R.id.area);
        ngoempname = (EditText) findViewById(R.id.ngoempname);
        schoolvisitsubmit =  findViewById(R.id.submit);
        saved=findViewById(R.id.edit);
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),VisitedSchools.class);
                startActivity(i);
            }
        });
        schoolvisitsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Log.d("XXX","Clicekd");
                Visit visit= new Visit();

                String h;
                String m=person.getText().toString();
               // Log.e("xxx",visit.setPersoname(person.getText().toString())
String s= scname.getText().toString();
                //);
               String k=mail.getText().toString();
                String o=phone.getText().toString();
                if(scconfirm.isChecked())
                {
                   h="Yes";
                }else
                {
                    h="No";
                }
               String a=studentstrength.getText().toString();
                helpher = new DataBaseHelpher(SchoolVisit.this);
                helpher.insertIntoDB(m, k, o, s, h,a);
            }
        });
    }
}
