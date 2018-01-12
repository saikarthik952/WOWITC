package wow.itc.com.wow_itc;

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
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FirstTime extends AppCompatActivity {
    Spinner ngo,city;
    SharedPreferences sharedpreferences;
    String pe,nme,bags,wardnos,addresss,xd,yd,snote;
    EditText name,pename,nobags,ward,address,note;
    Button image;


    int sr=0,a,bx;
    String[] ngodata={"NGO-1","NGO-2","NGO-3","NGO-4","NGO-5","NGO-6","Others Please Specify"};
    Button b;
    String[] citydata={"city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        ngo=findViewById(R.id.spinner);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,ngodata);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ngo.setAdapter(aa);
        b=findViewById(R.id.submit);
        city=findViewById(R.id.city);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,citydata);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);

        pename=findViewById(R.id.pename);

image=findViewById(R.id.image);
        ward=findViewById(R.id.ward);
 name=findViewById(R.id.name);
 address=findViewById(R.id.address);
 nobags=findViewById(R.id.bags);
note=findViewById(R.id.Note);
        sharedpreferences   = getSharedPreferences("DATAPE", Context.MODE_PRIVATE);

        Log.d("AddNewRecord", "getAll: " + sharedpreferences.getAll());
        Log.d("AddNewRecord", "Size: " + sharedpreferences.getAll().size());
        int x = sharedpreferences.getInt("NGO", 0);
        int  y= sharedpreferences.getInt("City",0);
        final String[] wardno = {sharedpreferences.getString("wardno", "")};
        final String  penames= sharedpreferences.getString("pename","");
        ngo.setSelection(x);
        city.setSelection(y);
        ward.setText(wardno[0]);
        pename.setText(penames);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/3/d/e/")
                .build();
        final HouseHoldFirstTime spreadsheetWebService = retrofit.create(HouseHoldFirstTime.class);
image.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "ITC_WOW");
        imagesFolder.mkdirs(); // <----
        String mg=penames+"_"+(++sr)+""+".jpg";
        File image = new File(imagesFolder, mg);
        Uri uriSavedImage = Uri.fromFile(image);
        Log.e("XXX",mg);
        Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    //    String pathMedia = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyImages/image_001.png";
     //   Uri uriSavedImage = Uri.fromFile(new File(pathMedia));
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(imageIntent,1);
    }
});
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pe=pename.getText().toString();
       nme=name.getText().toString();
       addresss=address.getText().toString();
                        bags=nobags.getText().toString();
                        wardnos=ward.getText().toString();
                       xd = ngo.getSelectedItem().toString();
                       yd=city.getSelectedItem().toString();
                       snote=note.getText().toString();
                        Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(xd,yd,wardnos,penames,nme,addresss,bags,snote);
                        completeQuestionnaireCall.enqueue(callCallback);
                    }


                }
        );
    }

    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
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

