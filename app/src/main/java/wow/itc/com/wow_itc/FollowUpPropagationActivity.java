package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FollowUpPropagationActivity extends AppCompatActivity {
    Spinner ngo,city;
    SharedPreferences sharedpreferences;
    String l,nme,ngopenames,xd,yd,n,snote;
    EditText name,wardname,notes,ward,ngopename;


    int a,bx;
    String[] ngodata={"NGO-1","NGO-2","NGO-3","NGO-4","NGO-5","NGO-6","Others Please Specify"};
    Button b,sg,notsg;
    int seg=0,notseg=0;
    String[] citydata={"city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_propagation);
        ngo=findViewById(R.id.spinner);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,ngodata);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ngo.setAdapter(aa);
        b=findViewById(R.id.sub);
        city=findViewById(R.id.city);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,citydata);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);
notes=findViewById(R.id.note);
        wardname=findViewById(R.id.pename);
sg=findViewById(R.id.seg);
notsg=findViewById(R.id.notsg);
        nme=wardname.getText().toString();
         ngopename=findViewById(R.id.ngoempname);
        ward=findViewById(R.id.ward);
        ngopenames=ngopename.getText().toString();
        sharedpreferences   = getSharedPreferences("DATAPE", Context.MODE_PRIVATE);
        int x = sharedpreferences.getInt("NGO", 0);
        int  y= sharedpreferences.getInt("City",0);
        final String[] wardno = {sharedpreferences.getString("wardno", "")};
        ngo.setSelection(x);
        city.setSelection(y);
        ward.setText(wardno[0]);

        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.putString("ngoempname",ngopenames);
       editor.putString("wardname",nme);
       editor.apply();
        ngopename.setText(sharedpreferences.getString("ngoempname",""));
        sg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                seg++;
                sg.setText("Segregated :"+seg);

            }
        });
        notsg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                notseg++;
                notsg.setText("Not Segregrated :"+notseg);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/3/d/e/")
                .build();
        final FollowupPropagation spreadsheetWebService = retrofit.create(FollowupPropagation.class);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            snote=notes.getText().toString();
               l=wardname.getText().toString();
               n=ngopename.getText().toString();
                xd = ngo.getSelectedItem().toString();
                yd=city.getSelectedItem().toString();
                Call<Void> completeFollowup = spreadsheetWebService.completeFollowup(xd,yd,l,nme,seg,notseg,n,snote);
                completeFollowup.enqueue(callCallbac);
            }
        });

    }
    final Callback<Void> callCallbac = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }


    };
}
