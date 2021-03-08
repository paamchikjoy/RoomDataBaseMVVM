package com.example.roomdatabasemvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdatabasemvvm.Model.Actor;
import com.example.roomdatabasemvvm.Repository.ActorRepository;

import java.util.List;

public class ActorViewModel extends AndroidViewModel {

    private ActorRepository actorRepository;
    private LiveData<List<Actor>> getAllActors;



    public ActorViewModel(@NonNull Application application) {
        super(application);

        actorRepository=new ActorRepository(application);
        getAllActors=actorRepository.getAllActors();
    }
    public void insert(List<Actor> list)
    {
        actorRepository.insert(list);

    }
   public  LiveData<List<Actor>> getAllActor()
   {
       return getAllActors;
   }


}
