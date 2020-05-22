package com.example.sneaktion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<DataHome> dataModelArrayList;


        public ListAdapter(Context context, ArrayList<DataHome>dataModelArrayList){
            this.context = context;
            this.dataModelArrayList = dataModelArrayList;
        }

        @Override
        public int getViewTypeCount(){
            return getCount();
        }

        @Override
        public int getItemViewType(int position){
            return position;
        }

        @Override
        public  int getCount(){
            return dataModelArrayList.size();
        }

        @Override
        public Object getItem(int position){
            return dataModelArrayList.get(position);
        }

        @Override
        public long getItemId(int position){
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listhome,null,true);
                holder.animepoto = (ImageView) convertView.findViewById(R.id.animpoto);
                holder.judul = (TextView) convertView.findViewById(R.id.judul);
                holder.score = (TextView) convertView.findViewById(R.id.score);
                holder.rank = (TextView) convertView.findViewById(R.id.rank);
                holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
                convertView.setTag(holder);
            }else{
                holder =(ViewHolder)convertView.getTag();
            }
            Picasso.get().load(dataModelArrayList.get(position).getImage_url()).into(holder.animepoto);
            holder.score.setText(""+dataModelArrayList.get(position).getScore());
            holder.judul.setText(""+dataModelArrayList.get(position).getTitle());
            holder.rank.setText(""+dataModelArrayList.get(position).getRank());
            holder.ratingBar.setRating(Float.parseFloat(""+dataModelArrayList.get(position).getScore())/2);
            return convertView;
        }

        private class ViewHolder {
            protected TextView judul,score,rank;
            protected ImageView animepoto;
            protected RatingBar ratingBar;
        }
    }


