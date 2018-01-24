package wow.itc.com.wow_itc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    int flag = 0;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
     SharedPreferences m;
   SharedPreferences.Editor k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                    {
                            CAMERA,
                            WRITE_EXTERNAL_STORAGE,
                            ACCESS_FINE_LOCATION,
                            ACCESS_COARSE_LOCATION,
                            ACCESS_NETWORK_STATE
                    }, REQUEST_CODE_ASK_PERMISSIONS);

            //Do not need to check the permission
        }



        }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults.length > 0 ) {

                    Toast.makeText(this, "ALl PErmitted", Toast.LENGTH_LONG).show();
                    m = getPreferences(MODE_PRIVATE);
                    flag = m.getInt("flag", 0);
                    k = m.edit();
                    if (flag == 0) {
                        flag = 1;
                        k.putInt("flag", flag);
                        k.apply();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(MainActivity.this, PERegisterActivity.class);
                                startActivity(i);

                            }
                        }, 2000);

                    }else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent o = new Intent(getApplicationContext(), HouseCheck.class);
                                startActivity(o);
                            }
                        }, 2000);
                    }

                }
                    //You did not accept the request can not use the functionality.
                break;
        }
    }
}
