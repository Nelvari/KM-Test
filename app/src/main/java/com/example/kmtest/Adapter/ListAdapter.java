package com.example.kmtest.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kmtest.Model.ListModel;
import com.example.kmtest.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<ListModel> dataList;
    View viewku;
    Context context;
    Activity activity;

    public ListAdapter(List<ListModel> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.list_detail, parent, false);
        return new ListAdapter.ViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListModel model = dataList.get(position);
        holder.tvFirstName.setText(model.getFirst_name());
        holder.tvLastName.setText(model.getLast_name());
        holder.tvEmail.setText(model.getEmail());

        Glide
                .with(holder.ivProfile.getContext())
                .load(dataList.get(position).getAvatar())
                .placeholder(R.mipmap.ic_photo)
                .into(holder.ivProfile);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = model.getFirst_name() + " " + model.getLast_name();
                Intent intent = new Intent();
                intent.putExtra("name", message);
                activity.setResult(1,intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFirstName, tvLastName, tvEmail;
        ImageView ivProfile;
        RelativeLayout relativeLayout;

        ViewHolder(View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            relativeLayout = itemView.findViewById(R.id.rv);
        }
    }

}
