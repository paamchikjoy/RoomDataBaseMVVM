package com.example.roomdatabasemvvm.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdatabasemvvm.Dao.ActorDao;
import com.example.roomdatabasemvvm.Model.Actor;


@Database(entities = {Actor.class}, version = 2)
public abstract class ActorDataBase extends RoomDatabase {


    private static final String DATABASE_NAME = "ActorDatabase";

    public abstract ActorDao actorDao();

    public static volatile ActorDataBase INSTANCE;

    public static ActorDataBase getInstance(Context context) {
        if (INSTANCE == null) {

            synchronized (ActorDataBase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, ActorDataBase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();


                }

            }
        }
        return INSTANCE;
    }
   static Callback callback =new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };
   static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>
   {
       private ActorDao actorDao;
        PopulateAsyncTask(ActorDataBase actorDataBase)
        {
            actorDao=actorDataBase.actorDao();
        }
       @Override
       protected Void doInBackground(Void... voids) {
            actorDao.deleteAll();
           return null;
       }
   }

}




