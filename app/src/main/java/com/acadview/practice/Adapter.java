package com.acadview.practice;
 import android.content.Context;
 import android.support.annotation.NonNull;
 import android.support.v7.widget.RecyclerView;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.TextView;

 import com.bumptech.glide.Glide;

 import java.util.ArrayList;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<News> newsLlist;
    Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return newsLlist == null?0:newsLlist.size();
    }

    public void swap(ArrayList<News> newsArrayList) {

        this.newsLlist = newsArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView,descriptionTextView;
        ImageView newsImage;



        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.description);
            newsImage = itemView.findViewById(R.id.newsImage);
        }

        public void bind(int position) {

            titleTextView.setText(newsLlist.get(position).getTitle());
            descriptionTextView.setText(newsLlist.get(position).getDiscription());
            Glide.with(context).load(newsLlist.get(position).getImgUrl()).into(newsImage);
        }
    }
 }
