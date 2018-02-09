package wow.itc.com.wow_itc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HubCollectionData extends AppCompatActivity {
ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_collection_data);
        Bundle m= getIntent().getExtras();
        data=new ArrayList<>();
        if (m != null) {
            data=  m.getStringArrayList("data");
        }


        ListView lv = findViewById(R.id.lv);
        ArrayAdapter<String> x= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv.setDividerHeight(1);
        lv.animate();
        lv.setAdapter(x);
x.notifyDataSetChanged();
    }
}
