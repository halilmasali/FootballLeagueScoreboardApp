package com.example.superleague;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLeagueData();
    }

    private void getLeagueData(){
        ApiConnection connection = new ApiConnection();
        Call<List<LeagueData>> leagueDataCall = connection.leagueScore("322");
        leagueDataCall.enqueue(new Callback<List<LeagueData>>() {
            @Override
            public void onResponse(Call<List<LeagueData>> call, Response<List<LeagueData>> response) {
                List<LeagueData> getLeagueData = response.body();
                if (getLeagueData != null) {
                    System.out.println(getLeagueData.get(0).teamName);
                }
            }

            @Override
            public void onFailure(Call<List<LeagueData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "League couldn't be loaded.", Toast.LENGTH_LONG).show();
            }
        });
    }
}