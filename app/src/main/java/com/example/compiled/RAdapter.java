package com.example.compiled;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.RViewHolder>{

    List<Users> users;
    OnItemClickListener listener;

    public RAdapter(List<Users> c, OnItemClickListener listener) {
        this.listener = listener;
        users=c;
    }


    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item_layout,parent,false);
        return new RViewHolder(view);
    }


    public interface OnItemClickListener {
        void onItemClick(Users item);
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        String title=users.get(position).name;
        holder.naam.setText(title);
        title =users.get(position).phone ;
        holder.num.setText(title);
        int img=users.get(position).image;
        holder.Iv.setImageResource(img);
        holder.bind(users.get(position),listener);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class RViewHolder extends RecyclerView.ViewHolder{
        ImageView Iv;
        TextView naam;
        TextView num;



        public RViewHolder(@NonNull View itemView) {
            super(itemView);
            Iv=itemView.findViewById(R.id.imgIcon);
            naam=itemView.findViewById(R.id.nameTxt);
            num=itemView.findViewById(R.id.numTxt);


        }

        public void bind(final Users item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
