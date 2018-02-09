package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthik on 1/26/2018.
 */

public class SavedPropagation extends RecyclerView.Adapter<SavedPropagation.ViewHolders> {
    private static List<Propagation> dbList;

    static Context context;
    SavedPropagation(Context context, List<Propagation> dbList ){
       SavedPropagation.dbList = new ArrayList<>();
        SavedPropagation.context = context;
        SavedPropagation.dbList = dbList;

    }
    @Override
    public SavedPropagation.ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_listview, null);

        // create ViewHolder

       ViewHolders viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SavedPropagation.ViewHolders holder, int position) {
        holder.name.setText(dbList.get(position).getSchoolname());
        holder.email.setText(dbList.get(position).getPersoname());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }
    public static class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name,email;

        public ViewHolders(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView
                    .findViewById(R.id.rvname);
            email = (TextView)itemLayoutView.findViewById(R.id.rvemail);
            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,SchoolISRC.class);

            Bundle extras = new Bundle();
            extras.putInt("position",getAdapterPosition());
            intent.putExtras(extras);

            /*
            int i=getAdapterPosition();
            intent.putExtra("position", getAdapterPosition());*/
            context.startActivity(intent);
          //  Toast.makeText(RecylerAdapter.context, "you have clicked Row " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }
}
