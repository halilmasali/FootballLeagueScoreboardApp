package com.example.superleague.leagueApi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ILeagueData {
    //Generating Url query with league_id and APIKey.
    @GET("/?action=get_standings")
    Call<List<LeagueData>> createGet(
            @Query("league_id") String leagueId,
            @Query("APIkey") String apiKey
    );
}
