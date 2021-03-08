package com.example.roomdatabasemvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdatabasemvvm.Dao.ActorDao;
import com.example.roomdatabasemvvm.Database.ActorDataBase;
import com.example.roomdatabasemvvm.Model.Actor;

import java.util.List;

public class ActorRepository {
    private ActorDataBase dataBase;
    private LiveData<List<Actor>> getAllActors;
    public ActorRepository(Application application) {

        dataBase=ActorDataBase.getInstance(application);
        getAllActors=dataBase.actorDao().getAllActors();

    }

 public    void insert(List<Actor> actorList)
    {

        new InsertAsyncTask(dataBase).execute(actorList);
    }

  public   LiveData<List<Actor>> getAllActors()
    {
        return getAllActors;
    }
    class InsertAsyncTask extends AsyncTask<List<Actor>,Void,Void>{
        private ActorDao actorDao;
        InsertAsyncTask (ActorDataBase actorDataBase)
        {
            actorDao=actorDataBase.actorDao();
        }

        @Override
        protected Void doInBackground(List<Actor>... lists) {
            actorDao.insert(lists[0]);
            return null;
        }
    }
}
