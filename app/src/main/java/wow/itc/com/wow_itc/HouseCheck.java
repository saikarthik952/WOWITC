package wow.itc.com.wow_itc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HouseCheck extends AppCompatActivity {
    public Button ft,fup,doorlocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_check);
        ft=findViewById(R.id.fttime);
        fup=findViewById(R.id.secondtime);
        doorlocked=findViewById(R.id.drlocked);
        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(HouseCheck.this,FirstTime.class);
                startActivity(i);
            }
        });
        fup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FollowUpPropagationActivity.class);
                startActivity(i);
            }
        });
        doorlocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k =new Intent(getApplicationContext(),HubCollectionActivity.class);
        startActivity(k);
            }
        });
    }

}
