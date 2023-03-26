package com.example.superleague;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ILeagueData {
    //super-lig
    @GET("league?data.league={league}")
    Call<LeagueData> createGet(@Path("league") String league);
}
