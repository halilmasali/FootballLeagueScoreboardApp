package com.example.superleague;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {
    public Call<LeagueData> CloudQueueInfo(String league) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.collectapi.com/sport/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ILeagueData leagueData = retrofit.create(ILeagueData.class);
        return leagueData.createGet(league);
    }
}
