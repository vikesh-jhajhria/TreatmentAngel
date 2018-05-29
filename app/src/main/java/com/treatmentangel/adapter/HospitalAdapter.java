package com.treatmentangel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treatmentangel.BaseActivity;
import com.treatmentangel.FullProfileActivity;
import com.treatmentangel.R;
import com.treatmentangel.model.HospitalModel;

import java.util.ArrayList;


public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<HospitalModel> mList;

    public HospitalAdapter(Context context, ArrayList list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_hospital, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HospitalModel model = mList.get(position);
        holder.name.setText(model.getName());
        holder.review.setText(model.getRating() + " Reviews");
        holder.detail.setText(model.getDetail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext,FullProfileActivity.class)
                .putExtra("ID",model.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //View holder class
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, review, detail;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            review = itemView.findViewById(R.id.txt_rating);
            detail = itemView.findViewById(R.id.txt_detail);
        }
    }
}
