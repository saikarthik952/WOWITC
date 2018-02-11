package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CorporationFirstTime extends AppCompatActivity {

    public Button corpsubmit;
    public Spinner ngo;
    public Spinner city;
    public EditText areaofvisit;
    public EditText date;
    public EditText ngoempname;
    public EditText corpname;
    public EditText person;
    public EditText valueofmatexchanged;
    public EditText phoneno;
    public EditText mail;
    public EditText mou;
    public EditText locations;
    public EditText period;
    public EditText qty;
    public EditText noofemps;
    public EditText note;
gpstracker gpsTracker;
    public String sareaofvisit;
    public String sdate;
    public String sngoempname;
    public String scorpname;
    public String sperson;
    public String svalueofmatexchanged;
    public String sphoneno;
    public String smail;
    public String smou;
    public String slocations;
    public String speriod;
    public String sqty;
    ProgressDialog md;
    public String snoofemps;
    public String snote,lat,longitude,ngos,citys;
    ProgressDialog psd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporation_first_time);
        ngo = (Spinner) findViewById(R.id.ngospinner);
        city = (Spinner) findViewById(R.id.cityspinner);
        corpsubmit = (Button) findViewById(R.id.corpsubmit);
gpsTracker = new gpstracker(this);
        @SuppressLint("ResourceType") ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ngos));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ngo.setAdapter(aa);
        psd=new ProgressDialog(this,R.style.MyTheme);
        psd.setCancelable(false);
        psd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        ArrayAdapter<String> bb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cities));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);
md= new ProgressDialog(this);
        areaofvisit = (EditText) findViewById(R.id.areacorp);
      //  date = (EditText) findViewById(R.id.datecorp);
        ngoempname = (EditText) findViewById(R.id.ngoempnamecorp);
        person = (EditText) findViewById(R.id.personcontacted);
        valueofmatexchanged= (EditText) findViewById(R.id.valueofmaterialexchanged);
        phoneno = (EditText) findViewById(R.id.phonecorp);
        mail = (EditText) findViewById(R.id.emailcorp);
        mou = (EditText) findViewById(R.id.moucorp);
        corpname=findViewById(R.id.corporatename);
        locations = (EditText) findViewById(R.id.nooflocs);
        qty = (EditText) findViewById(R.id.qtyexpected);
        noofemps = (EditText) findViewById(R.id.noofemps);
        period = (EditText) findViewById(R.id.periodofagree);
        note = (EditText) findViewById(R.id.notecorp);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/3/d/e/")
                .build();
        final CorporateFirstInterface spreadsheetWebService = retrofit.create(CorporateFirstInterface.class);




        corpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngos=ngo.getSelectedItem().toString();
                citys=city.getSelectedItem().toString();
                sareaofvisit=areaofvisit.getText().toString();
               // sdate=date.getText().toString();
                sngoempname=ngoempname.getText().toString();
                scorpname=corpname.getText().toString();
                sperson=person.getText().toString();
               // svalueofmatexchanged=valueofmatexchanged.getText().toString();
                sphoneno=phoneno.getText().toString();
                smail=mail.getText().toString();
                smou=mou.getText().toString();
                slocations=locations.getText().toString();
                speriod=period.getText().toString();
                sqty=qty.getText().toString();
                snoofemps=noofemps.getText().toString();
                snote=note.getText().toString();
                if(check(areaofvisit)||check(ngoempname)||check(corpname)||check(person)||check(phoneno)||check(mail)||check(mou)||check(locations)||check(period)||
                        check(qty)||check(noofemps))
                {
                    Toast.makeText(CorporationFirstTime.this,"All Fields are Mandatory",Toast.LENGTH_LONG).show();
                }
                else {

                    md.setTitle("Submitting");
                    md.setCancelable(false);
                    md.show();
                    if (gpsTracker.getIsGPSTrackingEnabled()) {
                        lat = String.valueOf(gpsTracker.getLatitude());
                        longitude = String.valueOf(gpsTracker.getLongitude());
                        Call<Void> completeFollowup = spreadsheetWebService.CorporateFirst(ngos, citys, sareaofvisit,
                                sngoempname, scorpname, sperson, sphoneno, smail, smou, slocations, speriod, sqty, snoofemps, lat, longitude, snote);
                        completeFollowup.enqueue(callCallbac);
                    }
                }
            }
        });
    }
    final Callback<Void> callCallbac = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);

            md.dismiss();
            Intent lp= getIntent();
            lp.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            startActivity(lp);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
            psd.setMessage("Failed......!!!");
            psd.dismiss();
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
