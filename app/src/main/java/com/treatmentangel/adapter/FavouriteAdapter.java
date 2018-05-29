package com.treatmentangel.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.treatmentangel.FavouriteActivity;
import com.treatmentangel.FullProfileActivity;
import com.treatmentangel.R;
import com.treatmentangel.model.FavouriteModel;
import com.treatmentangel.model.HospitalModel;
import com.treatmentangel.utils.AppPreferences;
import com.treatmentangel.utils.Config;
import com.treatmentangel.utils.HTTPUrlConnection;
import com.treatmentangel.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<FavouriteModel> mList;

    public FavouriteAdapter(Context context, ArrayList list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item_favourite, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FavouriteModel model = mList.get(position);
        holder.name.setText(model.getUser_screen_name());
        holder.address.setText(model.getUser_address_street1());
        holder.city.setText(model.getUser_address_city());
        holder.date.setText(model.getCrdt_date_time());

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showDecisionDialog(mContext, "Alert!", "Do you really want to remove it from your favourite?", new Utils.AlertCallback() {
                    @Override
                    public void callback() {
                        String user_id = "";
                        try {
                            user_id = AppPreferences.getAppPreferences(mContext).getUserDataObject().getString("user_id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        markFavourite(user_id, model.getFav_to_user_id(), "0", position);
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //View holder class
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, city, date, remove;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            address = itemView.findViewById(R.id.txt_address);
            city = itemView.findViewById(R.id.txt_city);
            date = itemView.findViewById(R.id.txt_add_date);
            remove = itemView.findViewById(R.id.txt_remove);
        }
    }

    private void markFavourite(String user_id, String listing_id, String status, final int position) {

        new AsyncTask<String, Void, String>() {

            int pos = position;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> map = new HashMap<>();
                map.put("user_id", params[0]);
                map.put("listing_id", params[1]);
                map.put("fav_status", params[2]);
                return HTTPUrlConnection.getInstance().loadPost(Config.BASE_URL + "api/add_fav_list", map);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getString("status_id").equalsIgnoreCase("1")) {
                        mList.remove(pos);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(mContext, obj.getString("status_msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute(user_id, listing_id, status);
    }
}
