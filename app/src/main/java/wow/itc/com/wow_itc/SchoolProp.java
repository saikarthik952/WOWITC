package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolProp extends AppCompatActivity {
    DataBaseHelpher helpher;
    List<Visit> dbList;
    int position;
    public Spinner ngospinner;
    public Spinner cityspinner;
    public Spinner proptype;
    public EditText nosas,scname;
    public EditText iec;
    public EditText other_benifits;
    public CheckBox treecheck;
    public CheckBox watercheck,wow;
String proptypes;
    String[] prop={"Assembly","Class To Class","PPT","Others-Please Specify"};
    public EditText city;
    public EditText ngo,members;
    public EditText area;
    public EditText ngoempnames;
    public String stree,ngoempname;
    public String swater,club,userImage;
    public Button scpropsubmit,image,edit;
    String num;
    String areavisit,personame,phonenumber,schoolname,email,schoolconfirmed,strength,attened,IEC,otherbenifits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_prop);
        ngospinner = findViewById(R.id.spinner);
        cityspinner = findViewById(R.id.city);
        proptype=findViewById(R.id.propType);
        nosas = findViewById(R.id.students_attended);
        iec =  findViewById(R.id.iec);

        image=findViewById(R.id.schoolpropimage);

        ngoempnames = findViewById(R.id.ngoempnamescprop);
        scpropsubmit = findViewById(R.id.scpropsubmit);
treecheck=findViewById(R.id.treeplantationcheck);
watercheck=findViewById(R.id.waterconservationcheck);
        ArrayAdapter<String> cc = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prop);
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proptype.setAdapter(cc);
       proptypes= proptype.getSelectedItem().toString();
members=findViewById(R.id.members);
        wow=findViewById(R.id.wowclcub);
edit=findViewById(R.id.edit);

        if(wow.isChecked())
        {
            club="Yes";
        }else
        {
            club ="No";

        }
        if (treecheck.isChecked())
        {
            stree = "Yes";

        }
        else
        {
            stree="No";        }
        if (watercheck.isChecked())
        {
            swater = "Yes";
        }
        else
        {
            swater="No";
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            position = bundle.getInt("position");
        }
        helpher = new DataBaseHelpher(this);
        dbList= new ArrayList<Visit>();
        dbList = helpher.getDataFromDB();
        if(dbList.size()>0){

            personame=dbList.get(position).getPersoname();
            email=dbList.get(position).getEmail();
            phonenumber=dbList.get(position).getPhonenumber();
            schoolname=dbList.get(position).getSchoolname();
            schoolconfirmed=dbList.get(position).getSchoolconfirmed();
            strength=dbList.get(position).getStrength();
            areavisit=dbList.get(position).getAreavisit();
ngoempname=dbList.get(position).getNgoname();

        }




        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                @SuppressLint("SdCardPath") Uri uri= Uri.parse("file:///sdcard/photo.jpg");
                photo.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(photo,1);

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(getApplicationContext(),PropagationActivity.class);
                startActivity(k);
            }
        });
        scpropsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ngoempname=ngoempnames.getText().toString();
                attened=nosas.getText().toString();
                IEC=iec.getText().toString();
                other_benifits=findViewById(R.id.otherbenifits);
                num=members.getText().toString();
                otherbenifits=other_benifits.getText().toString();
                drivesend();
                helpher.propinsert(areavisit,personame,schoolname,phonenumber,email,schoolconfirmed,strength
                ,attened,IEC,otherbenifits,stree,swater,club,num,ngoempname,proptypes);

            }
        });
    }

    private void drivesend() {
        Log.e("null","values"+userImage);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://script.google.com/macros/s/AKfycbySs60cndWfCLtuF1do4GIAOSHZUFr7-oGABCM6o8ZNzHHkql8S/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //loading.dismiss();
                        Log.e("null","values"+userImage);
                        Toast.makeText(SchoolProp.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SchoolProp.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("action","insert");
                params.put("uId",schoolname);
                params.put("uName",ngoempname+"- prop");
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
                    //bitmap = crupAndScale(bitmap, 300); // if you mind scaling
                    // pofileImageView.setImageBitmap(bitmap);
                    //  mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    //bmp = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);

                    byte[] byteArray = stream.toByteArray();
                    userImage= Base64.encodeToString(byteArray, Base64.DEFAULT);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }


    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i =new Intent(SchoolProp.this,SchoolActivity.class);
        startActivity(i);
    }
}
