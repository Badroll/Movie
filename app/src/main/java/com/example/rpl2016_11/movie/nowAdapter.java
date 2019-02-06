package com.example.rpl2016_11.movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class nowAdapter extends RecyclerView.Adapter<nowAdapter.nowViewHolder> {
    private Context mContext;
    private ArrayList<nowItem>mNowList;

    public nowAdapter(Context context , ArrayList<nowItem> nowList){
        mContext = context;
        mNowList = nowList;
    }


    @NonNull
    @Override
    public nowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.blue, parent, false);
        return new nowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final nowViewHolder holder, int position) {
         nowItem currentItem = mNowList.get(position);

         String imageUrl = currentItem.getImageurl();
         String tittle = currentItem.getTittle();
         String rate = currentItem.getRate();
         String language = currentItem.getLanguage();
         String description = currentItem.getDescription();
         String popularity = currentItem.getPopularity();
         String date = currentItem.getDate();

       // final nowItem mo = new nowItem( imageUrl, tittle, rate, language, description, popularity, date);
         
        final nowItem data = mNowList.get(position);
        Glide.with(mContext).load(data.getImageurl()).into(holder.mImageView);
        holder.mTVtittle.setText(data.getTittle());
        holder.mTVrate.setText(data.getRate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               nowItem parc = new nowItem();
               Intent i = new Intent(mContext, detail.class);
               parc.setImageurl(data.getImageurl());
               parc.setTittle(data.getTittle());
               parc.setRate(data.getRate());
               parc.setLanguage(data.getLanguage());
               parc.setDescription(data.getDescription());
               parc.setDate(data.getDate());
               parc.setPopularity(data.getPopularity());
               i.putParcelableArrayListExtra("parcel", parc);
               mContext.startActivity(i);
           }
       });

    }

    @Override
    public int getItemCount() {
        return mNowList.size();
    }

    public class nowViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTVtittle, mTVrate; // mTVlanguage, mTVdescription mTVpopularity, mTVdate;

        public nowViewHolder(final View itemView) {
            super(itemView);
            mImageView =itemView.findViewById(R.id.image_view);
            mTVtittle =itemView.findViewById(R.id.tvtittle);
            mTVrate =itemView.findViewById(R.id.tvrate);
        }
    }
}
