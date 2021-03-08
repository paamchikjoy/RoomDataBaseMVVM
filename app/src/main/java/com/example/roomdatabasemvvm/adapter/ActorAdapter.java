package com.example.roomdatabasemvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roomdatabasemvvm.Model.Actor;
import com.example.roomdatabasemvvm.R;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewholder>{

    private Context context;
    private List<Actor> actorList;

    public ActorAdapter(Context context, List<Actor> actorList) {
        this.context = context;
        this.actorList = actorList;
    }

    @NonNull
    @Override
    public ActorViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new ActorViewholder
                (LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.each_row
                                ,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewholder holder, int position) {

        Actor actor=actorList.get(position);
        holder.id.setText("Id " + actor.getId());
        holder.name.setText("Name " + actor.getName());
        holder.age.setText("Age " + actor.getAge());
        Glide.with(context)
                .load(actor.getImage() )
                .into(holder.image);



    }
    public void getAllActors(List<Actor> actorList)
    {
        this.actorList=actorList;
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public  static class  ActorViewholder extends RecyclerView.ViewHolder{
        public TextView id,name,age;
        public ImageView image;
        public ActorViewholder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id_tv);
            name=itemView.findViewById(R.id.name_tv);
            age=itemView.findViewById(R.id.age_tv);
            image=itemView.findViewById(R.id.image);
        }
    }
}
