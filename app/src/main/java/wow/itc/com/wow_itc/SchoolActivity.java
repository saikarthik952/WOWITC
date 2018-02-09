package wow.itc.com.wow_itc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SchoolActivity extends AppCompatActivity {
    public Button visitbtn;
    public Button propbtn;
    public Button isrcbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        visitbtn = (Button) findViewById(R.id.visitbtn);
        propbtn = (Button) findViewById(R.id.propagationbtn);
        isrcbtn = (Button) findViewById(R.id.isrcbtn);

        visitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SchoolActivity.this,SchoolVisit.class);
                startActivity(in);
            }
        });

        propbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(SchoolActivity.this,VisitedSchools.class);
                startActivity(in);
            }
        });

        isrcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent in = new Intent(SchoolActivity.this,PropagationActivity.class);
               startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i= new Intent(SchoolActivity.this,HouseCheck.class);
        startActivity(i);
        finish();
    }
}

