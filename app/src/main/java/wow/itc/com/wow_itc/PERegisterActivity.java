package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class PERegisterActivity extends AppCompatActivity {
   Spinner ngo,city;
    SharedPreferences sharedpreferences;
    String pe,nme,bags,wardno,swardname,x,y;
   EditText  name,pename,wardname,ward,address;

    String k;
   int a,bx;
  // String[] ngodata={"NGO-1","NGO-2","NGO-3","NGO-4","NGO-5","NGO-6","Others Please Specify"};
Button b;
   //String[] citydata={"city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1"};
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peregister);
        ngo=findViewById(R.id.spinner);
        Bundle m =getIntent().getExtras();
       // assert i != null;
        if (m != null) {
            k= m.getString("editoption");
        }
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ngos));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ngo.setAdapter(aa);
b=findViewById(R.id.submit);
        city=findViewById(R.id.city);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.cities
        ));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);

                pename=findViewById(R.id.pename);

wardname=findViewById(R.id.wardname);
                        ward=findViewById(R.id.ward);

        sharedpreferences   = getSharedPreferences("DATAPE", Context.MODE_PRIVATE);
        pename.setText(sharedpreferences.getString("pename",""));
        ward.setText(sharedpreferences.getString("wardno",""));
        wardname.setText(sharedpreferences.getString("wardname",""));
        ngo.setSelection(sharedpreferences.getInt("NGO",0));
        city.setSelection(sharedpreferences.getInt("City",0));
        @SuppressLint("CommitPrefEdits") final SharedPreferences.Editor editor = sharedpreferences.edit();
      ngo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               a=position;
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });
       city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


               bx=position;
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pe=pename.getText().toString();
//        nme=name.getText().toString();
        wardno=ward.getText().toString();
        swardname=wardname.getText().toString();
      //  bags=nobags.getText().toString();
   editor.putString("pename",pe);

        editor.putString("wardno",wardno);
        editor.putInt("NGO",a);
        editor.putInt("City",bx);
        editor.putString("wardname",swardname);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
if(Objects.equals(k, "editing"))
{
    Intent k= new Intent(getApplicationContext(),FirstTime.class);
    startActivity(k);
}
else {
    Intent i = new Intent(PERegisterActivity.this, HubCollectionRegister.class);
    startActivity(i);
}

    }
});

    }


    }



