package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class FirstTime extends AppCompatActivity {
    Spinner ngo, city;

    Button b;
    String userImage;
    ProgressDialog md;
    RadioGroup door, interest, wetwaste, bagissue, check;
    LinearLayout linearLayout,lfd;
    String sdoor = "", sinterest = "", swetwaste = "", sbagiisue = "", scheck = "", mg;
    SharedPreferences sharedpreferences;
    Button image;
    EditText wardno, wardname, pename, householdcount, householdname, householdaddress, householdemail, householdnumber, landmarks, note;
    gpstracker gpsTracker;

    String lat, longitude, ngom, citym, swardno, swardname, spename, shouseholdcount, shouseholdname, shouseholdaddress, shouseholdemail, shouseholdnumber, slandmarks, snote;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        ngo = findViewById(R.id.spinner);
        door = findViewById(R.id.door);
        interest = findViewById(R.id.household);
        wetwaste = findViewById(R.id.wetwaste);
        bagissue = findViewById(R.id.bag);
        md = new ProgressDialog(this);
          gpsTracker = new gpstracker(this);
        linearLayout = (LinearLayout) findViewById(R.id.houseid);
        linearLayout.setVisibility(View.GONE);
        lfd=findViewById(R.id.lfp);
        city = findViewById(R.id.city);
        @SuppressLint("ResourceType") ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ngos));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ngo.setAdapter(aa);

        ArrayAdapter<String> bb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cities));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);
        b = findViewById(R.id.submit);
        pename = findViewById(R.id.pename);
        wardno = findViewById(R.id.ward);
        wardname = findViewById(R.id.wardname);
        householdcount = findViewById(R.id.householdno);
        householdname = findViewById(R.id.name);
        householdaddress = findViewById(R.id.address);
        householdemail = findViewById(R.id.email);
        householdnumber = findViewById(R.id.phoneno);
        landmarks = findViewById(R.id.landmarks);
        check = findViewById(R.id.check);
        image = findViewById(R.id.image);
        note = findViewById(R.id.Note);
        sharedpreferences = getSharedPreferences("DATAPE", Context.MODE_PRIVATE);

        Log.d("AddNewRecord", "getAll: " + sharedpreferences.getAll());
        Log.d("AddNewRecord", "Size: " + sharedpreferences.getAll().size());
        int x = sharedpreferences.getInt("NGO", 0);
        int y = sharedpreferences.getInt("City", 0);
        swardno = sharedpreferences.getString("wardno", "");
        swardname = sharedpreferences.getString("wardname", "");
        spename = sharedpreferences.getString("pename", "");
        ngo.setSelection(x);
        city.setSelection(y);
        pename.setText(spename);
        wardno.setText(swardno);
        wardname.setText(swardname);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/3/d/e/")
                .build();
        final HouseHoldFirstTime spreadsheetWebService = retrofit.create(HouseHoldFirstTime.class);
        image.setVisibility(View.GONE);
        note.setVisibility(View.GONE);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                @SuppressLint("SdCardPath") Uri uri= Uri.parse("file:///sdcard/photo.jpg");
                photo.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(photo,1);

            }
        });
        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PERegisterActivity.class);
                i.putExtra("editoption", "editing");
                startActivity(i);
            }
        });
        door.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1) {
                    sdoor = "not selected";
                } else if (checkedId == R.id.dooryes) {
                    sdoor = "Yes";
               image.setVisibility(View.GONE);
                 note.setVisibility(View.GONE);
           linearLayout.setVisibility(View.GONE);

           householdaddress.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.doorno) {
                    sdoor = "No";
                    image.setVisibility(View.VISIBLE);
                    note.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        interest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1) {
                    sinterest = "not selected";
                } else if (checkedId == R.id.houseyes) {
                    sinterest = "Yes";
                } else if (checkedId == R.id.houseno) {
                    sinterest = "No";
                }
            }
        });
        check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1) {
                    scheck = "not selected";
                } else if (checkedId == R.id.first) {
                    scheck = "First Time";
                    householdaddress.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.followup) {
                    scheck = "Follow Up";
                    householdaddress.setVisibility(View.GONE);
                }
            }
        });
        wetwaste.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1) {
                    swetwaste = "not selected";
                } else if (checkedId == R.id.wasteyes) {
                    swetwaste = "Yes";
                } else if (checkedId == R.id.wasteno) {
                    swetwaste = "No";
                }
            }
        });
        bagissue.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1) {
                    sbagiisue = "not selected";
                } else if (checkedId == R.id.bagyes) {
                    sbagiisue = "Yes";
                } else if (checkedId == R.id.bagno) {
                    sbagiisue = "No";
                }
            }
        });
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        md.setTitle("Loading");
                        md.setCancelable(false);

                        md.show();


                        swardname = wardname.getText().toString();
                        swardno = wardno.getText().toString();
                        shouseholdcount = householdcount.getText().toString();
                        shouseholdemail = householdemail.getText().toString();
                        shouseholdname = householdname.getText().toString();
                        spename = pename.getText().toString();
                        slandmarks = landmarks.getText().toString();
                        shouseholdnumber = householdnumber.getText().toString();
                        shouseholdaddress = householdaddress.getText().toString();
                        drivesend();
                        String ik=spename+"_"+shouseholdname+".jpeg";

                        ngom = ngo.getSelectedItem().toString();
                        citym = city.getSelectedItem().toString();
                        snote = note.getText().toString();
                        if (gpsTracker.getIsGPSTrackingEnabled()) {
                            lat = String.valueOf(gpsTracker.getLatitude());
                            longitude = String.valueOf(gpsTracker.getLongitude());
                            Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(ngom, citym, swardno, swardname
                                    , sdoor, sinterest, scheck, spename, shouseholdname, shouseholdaddress, shouseholdnumber,
                                    shouseholdemail, shouseholdcount, swetwaste, ik, slandmarks, lat, longitude, snote);

                            completeQuestionnaireCall.enqueue(callCallback);
                        }
                    }


                }
        );
    }

    private void drivesend() {
        Log.e("null","values"+userImage);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://script.google.com/macros/s/AKfycbw1fIzSwPdK_2A4mxE5SjLZCeZVz54aq-WecGHzRb8d2PKvMvAY/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //loading.dismiss();
                        Log.e("null","values"+userImage);
                        Toast.makeText(FirstTime.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FirstTime.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("action","insert");
                params.put("uId",spename);
                params.put("uName",shouseholdname);
                params.put("uImage",userImage);

                return params;
            }

        };

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private final Callback<Void> callCallback = new Callback<Void>() {


        @Override
        public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
            md.setTitle("Submitted");
            md.dismiss();
          /// public  AlertDialog kk = new AlertDialog(FirstTime.this);
            Intent lp = getIntent();
            lp.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            startActivity(lp);
        }

        @Override
        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
            Log.e("XXX", "Failed", t);
        }


    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(Environment.getExternalStorageDirectory().getPath(), "photo.jpg");
                Uri uri = Uri.fromFile(file);
                Bitmap bitmap;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);

                    byte[] byteArray = stream.toByteArray();
                    userImage=Base64.encodeToString(byteArray, Base64.DEFAULT);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FirstTime.this,HouseCheck.class));
    }
}
