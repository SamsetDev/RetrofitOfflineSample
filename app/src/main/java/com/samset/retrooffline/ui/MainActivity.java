package com.samset.retrooffline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.samset.retrooffline.R;
import com.samset.retrooffline.model.BasicResponse;
import com.samset.retrooffline.model.JSONResponses;
import com.samset.retrooffline.network.ApiService;
import com.samset.retrooffline.network.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RetrofitManager manager;
    private ApiService apiService;
    private ProgressBar progressBar;
    private AppCompatTextView tvdata;
    private AppCompatButton btnget;
    public static ArrayList<JSONResponses> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        btnget = findViewById(R.id.btngetdata);
        tvdata = findViewById(R.id.tvdata);
        manager = new RetrofitManager(this);
        apiService = manager.getApiService();


        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_data();
            }
        });

    }


    private void get_data() {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<BasicResponse>> call = apiService.reposForuser("raamkumr-valentino");
        call.enqueue(new Callback<List<BasicResponse>>() {
            @Override
            public void onResponse(Call<List<BasicResponse>> call, Response<List<BasicResponse>> response) {
                progressBar.setVisibility(View.GONE);
                List<BasicResponse> repos = response.body();
                Log.e("TAG", " response " + repos.size());
                tvdata.setText(repos.get(0).getName());
                // listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, -1, repos));
            }

            @Override
            public void onFailure(Call<List<BasicResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, " error ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
