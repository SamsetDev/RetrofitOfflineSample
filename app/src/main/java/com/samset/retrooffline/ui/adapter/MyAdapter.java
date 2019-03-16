package com.samset.retrooffline.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samset.retrooffline.R;
import com.samset.retrooffline.ui.listeners.OnItemClickListeners;
import com.samset.retrooffline.ui.model.BasicResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Copyright (C) RetrofitOfflineSample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential.
 * <p>
 * Created by samset on 16/03/19 at 1:24 PM for RetrofitOfflineSample .
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<BasicResponse> dataList;
    private OnItemClickListeners onItemClickListeners;

    public MyAdapter(Context ctx, List<BasicResponse> response) {
        this.context = ctx;
        this.dataList = response;
    }

    public void setListeners(OnItemClickListeners listeners) {
        this.onItemClickListeners = listeners;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvdatas.setText(dataList.get(position).getName());
        holder.tvname.setText(dataList.get(position).getOwner().getLogin());
        Picasso.with(context).load(dataList.get(position).getOwner().getAvatar_url()).fit().into(holder.ivavtar);
        holder.tvdatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListeners != null) {
                    onItemClickListeners.onItemClick(view, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tvdatas, tvname;
        private CircleImageView ivavtar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvdatas = itemView.findViewById(R.id.tvdata);
            tvname = itemView.findViewById(R.id.tvname);
            ivavtar = itemView.findViewById(R.id.ivavtar);

        }
    }
}
