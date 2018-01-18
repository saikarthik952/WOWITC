package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FirstTime extends AppCompatActivity {
    Spinner ngo,city;

    Button b,edit;
ProgressDialog md;
    RadioGroup door,interest,wetwaste,bagissue,check;
LinearLayout linearLayout;
String sdoor="",sinterest="",swetwaste="",sbagiisue="",scheck="",mg;
    SharedPreferences sharedpreferences;
Button image;
EditText wardno,wardname,pename,householdcount,householdname,householdaddress,householdemail,householdnumber,landmarks,note;

int srnum;
String lat,longitude,ngom,citym,swardno,swardname,spename,shouseholdcount,shouseholdname,shouseholdaddress,shouseholdemail,shouseholdnumber,slandmarks,snote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        ngo=findViewById(R.id.spinner);
door=findViewById(R.id.door);
interest=findViewById(R.id.household);
wetwaste=findViewById(R.id.wetwaste);
bagissue=findViewById(R.id.bag);
md=new ProgressDialog(this);
        final gpstracker gpsTracker = new gpstracker(this);
        linearLayout=(LinearLayout)findViewById(R.id.houseid);
        linearLayout.setVisibility(View.GONE);
        @SuppressLint("ResourceType") ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.ngos));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ngo.setAdapter(aa);
        b=findViewById(R.id.submit);
        city=findViewById(R.id.city);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.cities));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);

        pename=findViewById(R.id.pename);
        wardno=findViewById(R.id.ward);
        wardname=findViewById(R.id.wardname);
        householdcount=findViewById(R.id.householdno);
        householdname=findViewById(R.id.name);
        householdaddress=findViewById(R.id.address);
        householdemail=findViewById(R.id.email);
        householdnumber=findViewById(R.id.phoneno);
        landmarks=findViewById(R.id.landmarks);
check=findViewById(R.id.check);
image=findViewById(R.id.image);
note=findViewById(R.id.Note);
        sharedpreferences   = getSharedPreferences("DATAPE", Context.MODE_PRIVATE);

        Log.d("AddNewRecord", "getAll: " + sharedpreferences.getAll());
        Log.d("AddNewRecord", "Size: " + sharedpreferences.getAll().size());
        int x = sharedpreferences.getInt("NGO", 0);
        int  y= sharedpreferences.getInt("City",0);
        swardno = sharedpreferences.getString("wardno", "");
        swardname=sharedpreferences.getString("wardname","");
          spename= sharedpreferences.getString("pename","");
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
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "ITC");
        imagesFolder.mkdirs();
        spename=pename.getText().toString();// <----
        shouseholdname  =householdname.getText().toString();
         mg=spename+"_"+(shouseholdname)+""+".jpg";
        File image = new File(imagesFolder, mg);
        Uri uriSavedImage = Uri.fromFile(image);
        Log.e("XXX",mg);

    //    String pathMedia = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyImages/image_001.png";
     //   Uri uriSavedImage = Uri.fromFile(new File(pathMedia));
        Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(imageIntent,1);
    }
});
findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),PERegisterActivity.class);
        i.putExtra("editoption","editing");
        startActivity(i);
    }
});
door.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==-1)
        {
            sdoor="not selected";
        }
        else if(checkedId==R.id.dooryes)
        {
            sdoor="Yes";
            image.setVisibility(View.GONE);
            note.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
        }
        else if(checkedId==R.id.doorno)
        {
         sdoor="No";
            image.setVisibility(View.VISIBLE);
            note.setVisibility(View.VISIBLE);
         linearLayout.setVisibility(View.VISIBLE);
        }
    }
});
interest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==-1)
        {
            sinterest="not selected";
        }
        else if(checkedId==R.id.houseyes)
        {
            sinterest="Yes";
        }
        else if(checkedId==R.id.houseno)
        {
            sinterest="No";
        }
    }
});
check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==-1)
        {
            scheck="not selected";
        }
        else if(checkedId==R.id.first)
        {
            scheck="First Time";
            householdaddress.setVisibility(View.VISIBLE);
        }
        else if(checkedId==R.id.followup)
        {
            scheck="Follow Up";
            householdaddress.setVisibility(View.GONE);
        }
    }
});
wetwaste.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==-1)
        {
            swetwaste="not selected";
        }
        else if(checkedId==R.id.wasteyes)
        {
            swetwaste="Yes";
        }
        else if(checkedId==R.id.wasteno)
        {
            swetwaste="No";
        }
    }
});
bagissue.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==-1)
        {
            sbagiisue="not selected";
        }
        else if(checkedId==R.id.bagyes)
        {
            sbagiisue="Yes";
        }
        else if(checkedId==R.id.bagno)
        {
            sbagiisue="No";
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




       swardname=wardname.getText().toString();
       swardno=wardno.getText().toString();
       shouseholdcount=householdcount.getText().toString();
       shouseholdemail=householdemail.getText().toString();
       shouseholdname  =householdname.getText().toString();
       spename=pename.getText().toString();
slandmarks=landmarks.getText().toString();
       shouseholdnumber=householdnumber.getText().toString();
                        shouseholdaddress=householdaddress.getText().toString();
                       // bags=nobags.getText().toString();
                        //wardnos=ward.getText().toString();
                       ngom = ngo.getSelectedItem().toString();
                       citym=city.getSelectedItem().toString();
                       snote=note.getText().toString();
                       if(gpsTracker.getIsGPSTrackingEnabled()) {
                           lat=String.valueOf(gpsTracker.getLatitude());
                           longitude=String.valueOf(gpsTracker.getLongitude());
                          Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(ngom,citym,swardno,swardname
                                  ,sdoor,sinterest,scheck,spename,shouseholdname,shouseholdaddress,shouseholdnumber,
                                  shouseholdemail,shouseholdcount,swetwaste,mg,slandmarks,lat,longitude,snote);

                           completeQuestionnaireCall.enqueue(callCallback);
                       }
                    }


                }
        );
    }

    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
md.setTitle("Submitted");
            md.dismiss();
Intent lp= getIntent();
lp.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
finish();
startActivity(lp);

        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }


    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK)
            Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
    }
}

