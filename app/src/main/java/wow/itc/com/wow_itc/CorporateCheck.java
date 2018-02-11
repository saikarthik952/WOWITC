package wow.itc.com.wow_itc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CorporateCheck extends AppCompatActivity {
    Button a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate_check);
        a = findViewById(R.id.firsttime);
        b = findViewById(R.id.collection);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CorporateCheck.this, CorporationFirstTime.class));
                finish();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CorporateCheck.this, CorporatePropagation.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i =  new Intent(CorporateCheck.this,HouseCheck.class);
        startActivity(i);
    }
}
