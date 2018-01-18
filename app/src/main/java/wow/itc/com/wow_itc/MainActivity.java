package wow.itc.com.wow_itc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences m= getPreferences(MODE_PRIVATE);
        flag=m.getInt("flag",0);
        final SharedPreferences.Editor k= m.edit();
        if (flag==0) {
            flag=1;
            k.putInt("flag", flag);
            k.apply();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this,PERegisterActivity.class);
                    startActivity(i);

                }
            },2000);
        }else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent o= new Intent(getApplicationContext(),HouseCheck.class);
                    startActivity(o);
                }
            },2000);

        }


    }
}
