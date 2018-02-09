package wow.itc.com.wow_itc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PropagationActivity extends AppCompatActivity {
    DataBaseHelpher helpher;
    List<Propagation> dbList;
    RecyclerView mRecyclerView;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propagation);
        helpher = new DataBaseHelpher(this);
        dbList= new ArrayList<>();
        dbList = helpher.propdata();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);
context=PropagationActivity.this;
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new SavedPropagation(context, dbList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
