package com.samset.retrooffline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samset.retrooffline.AppApplication;
import com.samset.retrooffline.R;
import com.samset.retrooffline.network.RetrofitManager;
import com.samset.retrooffline.ui.listeners.OnItemClickListeners;
import com.samset.retrooffline.ui.model.BasicResponse;
import com.samset.retrooffline.ui.model.JSONResponses;
import com.samset.retrooffline.network.ApiService;
import com.samset.retrooffline.ui.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.samset.retrooffline.utils.Utils.USERNAME;

public class MainActivity extends AppCompatActivity {


    private ApiService apiService;
    private ProgressBar progressBar;
    private RecyclerView recyclerview;
    private AppCompatButton btnget;
    private MyAdapter myAdapter;
    private FloatingActionButton fabbutton;
    public static ArrayList<JSONResponses> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitManager manager = new RetrofitManager(this);
        apiService=manager.getApiService();

        progressBar = findViewById(R.id.progress);
        btnget = findViewById(R.id.btngetdata);
        recyclerview = findViewById(R.id.recyclerview);
        fabbutton = findViewById(R.id.fab);


        recyclerview.setLayoutManager(new LinearLayoutManager(this));


        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_data();
            }
        });
        fabbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity();
            }
        });

    }


    private void get_data() {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<BasicResponse>> call = apiService.reposForuser(USERNAME);
        call.enqueue(new Callback<List<BasicResponse>>() {
            @Override
            public void onResponse(Call<List<BasicResponse>> call, Response<List<BasicResponse>> response) {
                progressBar.setVisibility(View.GONE);
                List<BasicResponse> repos = response.body();
                //Log.e("TAG", " response " + repos.size());
                setupAdapter(repos);
            }

            @Override
            public void onFailure(Call<List<BasicResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, " error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupAdapter(List<BasicResponse> data) {
        myAdapter = new MyAdapter(this, data);
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        myAdapter.setListeners(new OnItemClickListeners() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("TAG", " selected id " + data.get(position).getId());
                startNewActivity();
            }
        });


    }

    private void startNewActivity() {

        startActivity(new Intent(MainActivity.this, GithubListRxJavaActivity.class));
    }


}
