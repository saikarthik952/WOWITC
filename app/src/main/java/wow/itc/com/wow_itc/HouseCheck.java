package wow.itc.com.wow_itc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HouseCheck extends AppCompatActivity {
    public Button house,followup,school,corporate,hubcollection;
gpstracker gpsTracker;
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
        // gpsTracker.showSettingsAlert();
        if(statusOfGPS)
        {
            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
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
            }
        });

        //On pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
