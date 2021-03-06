package com.example.roomdatabasemvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roomdatabasemvvm.Model.Actor;

import java.util.List;

@Dao
public interface ActorDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
      void insert(List<Actor> actorList);
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//      void update();
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//      void delete();
    @Query("SELECT * FROM actor")
   LiveData <List<Actor> >getAllActors();
    @Query("DELETE FROM actor")
    void deleteAll();
}
