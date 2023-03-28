package com.example.superleague.leagueApi;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {
    //The Api key required for the API connection is pulled from the enum.
    private final String apiKey= ApiKey.API_KEY.getKey();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://apiv3.apifootball.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    public Call<List<LeagueData>> leagueScore(String leagueId) {
        ILeagueData leagueData = retrofit.create(ILeagueData.class);
        return leagueData.createGet(leagueId,apiKey);
    }
}
