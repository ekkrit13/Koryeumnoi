package com.koryeumnoi.koryeumnoi;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter {

    private List<CardModel> dataset;
    private Activity activity;

    public CustomAdapter(Activity activity, List<CardModel> dataset) {
        this.dataset = dataset;
        this.activity = activity;
    }

    public void setData(List<CardModel> dataset) {
        this.dataset = dataset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        //viewมาจากไหน? ต้องมีตัวมาช่วย
        MenuViewHoler current = new MenuViewHoler(view);
        return current;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardModel model = dataset.get(position);
        MenuViewHoler cv_holder = (MenuViewHoler)holder;

        ServerConnector connector = new ServerConnector();
        String cover_path = connector.seturl_cover;

        cv_holder.title_tv.setText(String.valueOf(model.getTitle()));
        cv_holder.isbn_tv.setText(String.valueOf(model.getIsbn()));
        cv_holder.rent_date_tv.setText(String.valueOf(model.getRent_date()));
        cv_holder.deadline_date_tv.setText(String.valueOf(model.getDeadline_date()));

        Glide.with(activity).load(cover_path+model.getCover()).centerCrop().into(cv_holder.cover_iv);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class MenuViewHoler extends RecyclerView.ViewHolder {
        //public ImageView IVImg;
        public TextView title_tv, isbn_tv, rent_date_tv, deadline_date_tv;
        public ImageView cover_iv;

        public MenuViewHoler(View itemView) {
            super(itemView);
            //IVImg = (ImageView) itemView.findViewById(R.id.IVImg);
            title_tv = (TextView)itemView.findViewById(R.id.title);
            isbn_tv = (TextView)itemView.findViewById(R.id.isbn);
            rent_date_tv = (TextView)itemView.findViewById(R.id.rent_date);
            deadline_date_tv = (TextView)itemView.findViewById(R.id.deadline_date);
            cover_iv = (ImageView)itemView.findViewById(R.id.cover);
        }
    }
}