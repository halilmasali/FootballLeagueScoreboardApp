package com.example.superleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LeagueDataAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private Toolbar mainToolbar;
    private ArrayList<Map<String,String >> leagues = new ArrayList<>();
    String leagueName="Süper Lig",leagueId="322";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<ItemsViewModel> data = new ArrayList<>();
        data.add(new ItemsViewModel(null,"Team","0"));
        adapter= new LeagueDataAdapter(data,this);
        recyclerView.setAdapter(adapter);
        swipeRefresh = findViewById(R.id.swipeRefreshLayout);
        swipeRefresh.setOnRefreshListener(this::getLeagueData);

        mainToolbar = findViewById(R.id.toolbar);
        mainToolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.league) {
                leagueSelection();
            }
            return true;
        });
        getLeagueData();
        leaguesInfo();
    }

    private void getLeagueData(){
        ApiConnection connection = new ApiConnection();
        Call<List<LeagueData>> leagueDataCall = connection.leagueScore(leagueId);
        leagueDataCall.enqueue(new Callback<List<LeagueData>>() {
            @Override
            public void onResponse(Call<List<LeagueData>> call, Response<List<LeagueData>> response) {
                List<LeagueData> getLeagueData = response.body();
                if (getLeagueData != null) {
                    ArrayList<ItemsViewModel> data = new ArrayList<>();
                    for (LeagueData leagueData:getLeagueData) {
                        data.add(new ItemsViewModel(
                                leagueData.teamBadge,
                                leagueData.teamName,
                                leagueData.overallLeaguePTS
                        ));
                    }
                    adapter.addList(data);
                }
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<LeagueData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "League couldn't be loaded.", Toast.LENGTH_LONG).show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void leagueSelection() {
        ArrayList<CharSequence> items =new  ArrayList<>();
        for (Map<String,String> store: leagues){
            items.add(Objects.requireNonNull(store.get("leagueName")));
        }
        CharSequence[] cs = items.toArray(new CharSequence[items.size()]);
        new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("Chose a league")
                .setItems(cs,(dialogInterface, i) -> {
                    leagueName = Objects.requireNonNull(leagues.get(i).get("leagueName"));
                    leagueId = Objects.requireNonNull(leagues.get(i).get("leagueId"));
                    mainToolbar.setTitle(leagueName);
                    getLeagueData();
                })
                .show();
    }

    private void leaguesInfo() {
        Map<String, String> league1 = new HashMap<>();
        league1.put("leagueName", "Süper Lig");
        league1.put("leagueId", "322");

        Map<String, String> league2 = new HashMap<>();
        league2.put("leagueName", "Major League Soccer");
        league2.put("leagueId", "332");

        Map<String, String> league3 = new HashMap<>();
        league3.put("leagueName", "La Liga");
        league3.put("leagueId", "302");

        leagues.add(league1);
        leagues.add(league2);
        leagues.add(league3);
    }
}