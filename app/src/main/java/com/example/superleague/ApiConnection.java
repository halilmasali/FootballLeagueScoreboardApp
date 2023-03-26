package com.example.superleague;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {
    //The Api key required for the API connection is pulled from the enum.
    String apiKey= ApiKey.API_KEY.getKey();
    public Call<List<LeagueData>> leagueScore(String leagueId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiv3.apifootball.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ILeagueData leagueData = retrofit.create(ILeagueData.class);
        return leagueData.createGet(leagueId,apiKey);
    }
}
