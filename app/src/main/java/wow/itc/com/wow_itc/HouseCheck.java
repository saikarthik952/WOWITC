package wow.itc.com.wow_itc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HouseCheck extends AppCompatActivity {
    public Button house,followup,school,corporate,hubcollection;

int x;
    boolean statusOfGPS;
    Activity mContext = HouseCheck.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_check);
        house = findViewById(R.id.household);
        followup = findViewById(R.id.followup);
        school = findViewById(R.id.school);
        corporate = findViewById(R.id.corporate);
        hubcollection = findViewById(R.id.hubcollection);
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
        if (manager != null) {
            statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
Intent x= getIntent();
        isNetworkConnectionAvailable();
        if(statusOfGPS)
        {
            Toast.makeText(getApplicationContext(),"GPS ALLOWED",Toast.LENGTH_LONG).show();
        }else {
            showSettingsAlert();
        }
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FirstTime.class);
                startActivity(i);
            }
        });
        followup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getApplicationContext(), FollowUpPropagationActivity.class);
                startActivity(k);
            }
        });
        hubcollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(getApplicationContext(), HubCollectionActivity.class);
                startActivity(l);
            }
        });
        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k= new Intent(getApplicationContext(),SchoolActivity.class);
                startActivity(k);
            }
        });
        corporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HouseCheck.this,CorporateCheck.class));
                finish();
            }
        });
    }
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //Setting Dialog Title
        alertDialog.setTitle("Enable GPS");

        //Setting Dialog Message
        alertDialog.setMessage("Please Enable the GPS");

        //On Pressing Setting button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

                mContext.startActivity(intent);
             restartThis();
            }
        });

        //On pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
                System.exit(0);
            }
        });

        alertDialog.show();
    }

    public void checkNetworkConnection(){
        android.app.AlertDialog.Builder builder =new android.app.AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setCancelable(false);
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                System.exit(0);
            }
        });
        builder.setPositiveButton("Turn on", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                restartThis();
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            Log.d("Network", "Connected");
            x=1;
        }
        else{
            checkNetworkConnection();
            Log.d("Network","Not Connected");
            x=2;
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
    private void restartThis() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
