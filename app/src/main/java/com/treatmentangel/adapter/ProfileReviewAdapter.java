package com.treatmentangel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treatmentangel.R;
import com.treatmentangel.model.HospitalModel;

import java.util.ArrayList;


public class ProfileReviewAdapter extends RecyclerView.Adapter<ProfileReviewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<HospitalModel> mList;

    public ProfileReviewAdapter(Context context, ArrayList list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_review, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HospitalModel model = mList.get(position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //View holder class
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, detail;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            detail = itemView.findViewById(R.id.txt_detail);
        }
    }
}
