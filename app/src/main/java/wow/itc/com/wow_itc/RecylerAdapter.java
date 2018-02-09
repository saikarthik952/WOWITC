package wow.itc.com.wow_itc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthik on 1/20/2018.
 */

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder>  {
    static List<Visit> dbList;
    static Context context;
    RecylerAdapter(Context context, List<Visit> dbList ){
        RecylerAdapter.dbList = new ArrayList<>();
        RecylerAdapter.context = context;
        RecylerAdapter.dbList = dbList;

    }
    @Override
    public RecylerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_listview, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecylerAdapter.ViewHolder holder, int position) {
        holder.name.setText(dbList.get(position).getSchoolname());
        holder.email.setText(dbList.get(position).getSchoolconfirmed());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name,email;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView
                    .findViewById(R.id.rvname);
            email = (TextView)itemLayoutView.findViewById(R.id.rvemail);
            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
           Intent intent = new Intent(context,SchoolProp.class);

            Bundle extras = new Bundle();
            extras.putInt("position",getAdapterPosition());
            intent.putExtras(extras);



            //int i=getAdapterPosition();
            //intent.putExtra("position", getAdapterPosition());
            context.startActivity(intent);
            Toast.makeText(RecylerAdapter.context, "you have clicked Row " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }


}


