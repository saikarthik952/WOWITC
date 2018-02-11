package wow.itc.com.wow_itc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VisitedSchools extends AppCompatActivity {
Context context;
    DataBaseHelpher helpher;
    List<Visit> dbList;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_schools);

        helpher = new DataBaseHelpher(this);
        dbList= new ArrayList<Visit>();
        dbList = helpher.getDataFromDB();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);

        mRecyclerView.setHasFixedSize(true);
context=VisitedSchools.this;
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new RecylerAdapter(context, dbList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
