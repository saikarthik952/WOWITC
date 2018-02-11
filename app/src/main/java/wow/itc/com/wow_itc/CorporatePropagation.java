package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CorporatePropagation extends AppCompatActivity {
    public Button editinfo;
    public Button corpcollecsubmit;
    public TextView corpngoname;
    public TextView corpcity;
    public Spinner ngo;
    public Spinner city;
    public EditText locationname;
    public EditText dateofcollec;
    public EditText ngoempname;
    public EditText corpname;
    public EditText qtymixwaste;
    public EditText qtywhitewaste;
    public EditText lvp;
    public EditText note;
    ProgressDialog md;

    gpstracker gpsTracker;
    public String scorpngoname;
    public String scorpcity;
    public String slocationname;
    public String sdateofcollec;
    public String sngoempname;
    public String scorpname;
    public String sqtymixwaste;
    public String sqtywhitewaste;
    public String slvp;
    public String snote,lat,longitude,sngo,scity;
    ProgressDialog psd;
 ProgressDialog pDialog;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_propagation);
        editinfo = (Button) findViewById(R.id.edit);
        ngo = (Spinner) findViewById(R.id.ngospinner);
        city = (Spinner) findViewById(R.id.cityspinner);
        md = new ProgressDialog(this);
        corpcollecsubmit = (Button) findViewById(R.id.corpcollecsubmit);
        @SuppressLint("ResourceType") ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ngos));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ngo.setAdapter(aa);
       // Activity activity=getActivity();

        ArrayAdapter<String> bb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cities));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);
gpsTracker =new gpstracker(this);
        corpname = (EditText) findViewById(R.id.corporatenamecollection);
     ///   dateofcollec = (EditText) findViewById(R.id.dateofcollection);
        ngoempname = (EditText) findViewById(R.id.ngoempnamecorpcollec);
        locationname = (EditText) findViewById(R.id.Locationname);
        qtymixwaste = (EditText) findViewById(R.id.qtymixwaste);
        qtywhitewaste = (EditText) findViewById(R.id.qtywhitewaste);
        lvp = (EditText) findViewById(R.id.lvpmllcollec);
        note = (EditText) findViewById(R.id.notecorpcollec);
 pDialog = new ProgressDialog(CorporatePropagation.this, R.style.AppTheme);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_Material_Light_ProgressBar_Small_Title);
        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        psd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/3/d/e/")
                .build();
        final CorporatePropInterface spreadsheetWebService = retrofit.create(CorporatePropInterface.class);



        corpcollecsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

sngo=ngo.getSelectedItem().toString();
scity=city.getSelectedItem().toString();
                scorpngoname = ngoempname.getText().toString();
//                scorpcity = corpcity.getText().toString();
                slocationname = locationname.getText().toString();
                scorpname = corpname.getText().toString();
                //sdateofcollec = dateofcollec.getText().toString();
                sngoempname = ngoempname.getText().toString();

                sqtymixwaste = qtymixwaste.getText().toString();
                sqtywhitewaste = qtywhitewaste.getText().toString();
                slvp = lvp.getText().toString();
                snote = note.getText().toString();
                if (check(ngoempname) || check(corpname) || check(locationname) || check(qtymixwaste) || check(qtywhitewaste) || check(lvp)) {
                    Toast.makeText(CorporatePropagation.this, "All Fields are mandatory", Toast.LENGTH_LONG).show();
                } else if (gpsTracker.getIsGPSTrackingEnabled()) {
                    md.setTitle("Loading");
                    md.setCancelable(false);

                    md.show();
                    lat = String.valueOf(gpsTracker.getLatitude());
                    longitude = String.valueOf(gpsTracker.getLongitude());
                    Call<Void> completeFollowup = spreadsheetWebService.CorporateProp(sngo,scity,sdateofcollec,
                            sngoempname,scorpname,slocationname,sqtymixwaste,sqtywhitewaste,slvp,snote);
                    completeFollowup.enqueue(callCallbac);
                }
            }
        });
    }
    final Callback<Void> callCallbac = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
          md.dismiss();
   startActivity(new Intent(CorporatePropagation.this,CorporateCheck.class
   ));
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);

        }


    };
    public boolean check(EditText edt)
    {

        if(edt.getText().toString().isEmpty()) {
            edt.setError("Field should not be empty");
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),CorporateCheck.class));
    }
}
