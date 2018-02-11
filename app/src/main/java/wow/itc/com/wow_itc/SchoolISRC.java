package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class SchoolISRC extends AppCompatActivity {
    public Button editinfo;
    DataBaseHelpher helpher;
    List<Propagation> dbList;
    int position,mDay,mMonth,mYear;
    public Spinner ngo;
    public Spinner city;
    int x,y,z;
    DatePickerDialog datePickerDialog;

    public EditText date;
String  imgprop,imgisrc;
    public EditText studentsattended;
    public EditText qtycollected;
    public EditText valueofmatexchanged;
    public Button submitisrc;
gpstracker gpsTracker;
    Calendar mcurrentDate;
EditText greenchampion,qtygreenchampion;

    String sdate,userImage,u;
    ProgressDialog md;
    Calendar dateSelected;
    String sstudentsattended;
    String sqtycollected,ngos,citys;
    Button image;
    ISRCInterface spreadsheetWebService;
    String svalue,greenchampions,qtygreen;
    public String areavisit,personame,schoolname,phonenumber,email,schoolconfirmed,strength,attened,IEC,otherbenifits,tree,water,wowclub,numofstudents,ngoempnames,proptype;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_isrc);
        ngo = findViewById(R.id.spinner);
        city = findViewById(R.id.city);
        @SuppressLint("ResourceType") ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ngos));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
md=new ProgressDialog(this);
        ngo.setAdapter(aa);
        gpsTracker = new gpstracker(this);
        city = findViewById(R.id.city);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cities));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);
        studentsattended = (EditText) findViewById(R.id.students_attended);
        qtycollected = (EditText) findViewById(R.id.quantitycollected);
        valueofmatexchanged = (EditText) findViewById(R.id.valueofmaterialexchanged);
        image = findViewById(R.id.schoolpropimage);


        greenchampion = findViewById(R.id.greenchampion);
        submitisrc = findViewById(R.id.scisrcsubmit);
        mcurrentDate = Calendar.getInstance();
        qtygreenchampion = findViewById(R.id.greenchampcollected);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/3/d/e/")
                .build();
        spreadsheetWebService = retrofit.create(ISRCInterface.class);
        date = findViewById(R.id.datescisrc);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            position = bundle.getInt("position");
        }


        helpher = new DataBaseHelpher(this);
        dbList = new ArrayList<Propagation>();
        dbList = helpher.propdata();
        if (dbList.size() > 0) {
            personame = dbList.get(position).getPersoname();
            email = dbList.get(position).getEmail();
            phonenumber = dbList.get(position).getPhonenumber();
            schoolname = dbList.get(position).getSchoolname();
            schoolconfirmed = dbList.get(position).getSchoolconfirmed();
            strength = dbList.get(position).getStrength();
            areavisit = dbList.get(position).getAreavisit();
            ngoempnames = dbList.get(position).getNgoempname();
            attened = dbList.get(position).getAttened();
            IEC = dbList.get(position).getIEC();
            otherbenifits = dbList.get(position).getOtherbenifits();
            tree = dbList.get(position).getTree();
            water = dbList.get(position).getWater();
            proptype = dbList.get(position).getTypeofprop();
            wowclub = dbList.get(position).getWowclub();
            numofstudents = dbList.get(position).getNumofstudents();

        }
        dateSelected = Calendar.getInstance();

        if (date.hasFocus()) {
            setDateTimeField();
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                @SuppressLint("SdCardPath") Uri uri = Uri.parse("file:///sdcard/photo.jpg");
                photo.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(photo, 1);

            }
        });

        submitisrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                greenchampions = greenchampion.getText().toString();
                qtygreen = qtycollected.getText().toString();
                ngos = ngo.getSelectedItem().toString();
                citys = city.getSelectedItem().toString();
                sstudentsattended = studentsattended.getText().toString();
                sqtycollected = qtycollected.getText().toString();
                svalue = valueofmatexchanged.getText().toString();
                if (check(studentsattended) || check(qtycollected) || check(valueofmatexchanged) || check(greenchampion) || check(valueofmatexchanged)) {
                    Toast.makeText(SchoolISRC.this, "All Fields are Mandatory", Toast.LENGTH_LONG).show();
                } else {
                    md.setTitle("submitting");
                    md.setCancelable(false);
                    md.show();
                    drivesend();
imgprop=ngoempnames="_"+schoolname+"-prop.jpeg";
                    imgisrc=ngoempnames="_"+schoolname+"-isrc.jpeg";
                    if (gpsTracker.getIsGPSTrackingEnabled()) {
                        String lat = String.valueOf(gpsTracker.getLatitude());
                        String longitude = String.valueOf(gpsTracker.getLongitude());
                        Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeSchoolISRC(ngos, citys, areavisit, ngoempnames, schoolname,
                                personame, phonenumber, email, schoolconfirmed, strength, attened, wowclub, numofstudents, proptype,
                                IEC, otherbenifits, tree, water, otherbenifits, sstudentsattended, sqtycollected, svalue,
                                greenchampions, qtygreen, lat, longitude,imgprop,imgisrc);

                        completeQuestionnaireCall.enqueue(callCallback);
                    }
                }

            }
        });
    }


    private final Callback<Void> callCallback = new Callback<Void>() {


        @Override
        public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);

        }

        @Override
        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
            Log.e("XXX", "Failed", t);
        }


    };

    private void drivesend() {
        Log.e("null","values"+userImage);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://script.google.com/macros/s/AKfycbySs60cndWfCLtuF1do4GIAOSHZUFr7-oGABCM6o8ZNzHHkql8S/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //loading.dismiss();
                        Log.e("null","values"+userImage);
                        Toast.makeText(SchoolISRC.this,response,Toast.LENGTH_LONG).show();
                        md.dismiss();
                        Intent p= new Intent(SchoolISRC.this,SchoolActivity.class);
                        finish();
                        startActivity(p);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SchoolISRC.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("action","insert");
                params.put("uId",schoolname);
                params.put("uName",ngoempnames);
                params.put("uImage",userImage);

                return params;
            }

        };

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }
    private void setDateTimeField() {
        Calendar newCalendar = dateSelected;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateSelected.set(year, monthOfYear, dayOfMonth);
                date.setText(dayOfMonth+""+monthOfYear+""+year);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i =new Intent(SchoolISRC.this,SchoolActivity.class);
        startActivity(i);
    }
    Bitmap bitmap;
    Uri uri;
    byte[] byteArray;
    File file;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                file = new File(Environment.getExternalStorageDirectory().getPath(), "photo.jpg");
                uri = Uri.fromFile(file);

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);

                    byteArray = stream.toByteArray();
                    userImage= Base64.encodeToString(byteArray, Base64.DEFAULT);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        }

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
