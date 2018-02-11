package wow.itc.com.wow_itc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SchoolVisit extends AppCompatActivity {

    public EditText scname;
    public EditText person;
    public EditText phone;
    public EditText mail;
    public CheckBox scconfirm;

    public EditText studentstrength;
    ProgressDialog md;
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
        helpher = new DataBaseHelpher(SchoolVisit.this);
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
String ngoemp;
ngoemp=ngoempname.getText().toString();
                String h;
                String m=person.getText().toString();
               // Log.e("xxx",visit.setPersoname(person.getText().toString())
String s= scname.getText().toString();
                //);
                String are=area.getText().toString();
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
if(check(ngoempname)||check(person)||check(scname)||check(mail)||check(phone)||check(studentstrength)||check(area))
{
    Toast.makeText(SchoolVisit.this,"All fields are Mandatory",Toast.LENGTH_LONG).show();
}
else {
    helpher.insertIntoDB(m, k, o, s, h, a, are, ngoemp);
}           }
        });
    }
    public boolean check(EditText edt)
    {

        if(edt.getText().toString().isEmpty()) {
            edt.setError("Field should not be empty");
            return true;
        }
        return false;
    }
}
