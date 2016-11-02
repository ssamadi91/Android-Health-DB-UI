package com.example.shamool.HeartRate;

/**
 * Created by Shamool on 10/31/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    Context context;
    List<UserData> dataList = new ArrayList<>();
    LayoutInflater inflater;
    Listener listener;


    public ListAdapter(Context context, List<UserData> dataList1) {

        this.context = context;
        this.dataList = dataList1;
        this.listener= (Listener) context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.row, parent, false);
        return new ListViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {


        holder.getIv_delete().setTag(position);
        holder.myHR.setText(dataList.get(position).heart_rate);
        holder.myDescription.setText("gender:"+dataList.get(position).gender
                + ", temperature:" + dataList.get(position).body_temp);

        holder.getIv_delete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.nameToChnge(dataList.get((Integer) v.getTag()).heart_rate);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView myHR;
        private TextView myDescription;
        private ImageView iv_delete;

        public ListViewHolder(View itemView) {
            super(itemView);
            myHR = (TextView) itemView.findViewById(R.id.myHR);
            //myHR.setTextSize(25);
            myDescription = (TextView) itemView.findViewById(R.id.myDescription);
           // myDescription.setTextSize(20);
            setIv_delete((ImageView) itemView.findViewById(R.id.iv_delete));

        }

        public ImageView getIv_delete() {
            return iv_delete;
        }

        public void setIv_delete(ImageView iv_delete) {
            this.iv_delete = iv_delete;
        }
    }

}