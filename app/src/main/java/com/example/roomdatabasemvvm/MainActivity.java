package com.example.roomdatabasemvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.roomdatabasemvvm.Model.Actor;
import com.example.roomdatabasemvvm.Network.Api;
import com.example.roomdatabasemvvm.Repository.ActorRepository;
import com.example.roomdatabasemvvm.ViewModel.ActorViewModel;
import com.example.roomdatabasemvvm.adapter.ActorAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActorViewModel actorViewModel;
    private RecyclerView recyclerView;
    //private ActorAdapter actorAdapter;
    private List<Actor> actorList;
    private ActorRepository actorRepository;
    private static final String BaseUrl="http://www.codingwithjks.tech/data.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actorList= new ArrayList<>();
        actorRepository=new ActorRepository(getApplication());

        actorViewModel=new ViewModelProvider(this).get(ActorViewModel.class);

        actorViewModel.getAllActor().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actorList) {
                //Toast.makeText(MainActivity.this, " Working fine", Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new ActorAdapter(getApplicationContext(),actorList));
                //might create problem here
                Log.d("main", "onChanged: ");

            }
        });
        networkRequest();

    }

    private void networkRequest() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);

        Call<List<Actor>> call=api.getAllActors();
        call.enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {

                if(response.isSuccessful())
                {
                    actorRepository.insert(response.body());
                }


            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });


    }
}