package com.example.superleague;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.widget.Toast;

import com.example.superleague.leagueApi.ApiConnection;
import com.example.superleague.leagueApi.LeagueData;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LeagueDataAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private Toolbar mainToolbar;
    private String leagueName = "Süper Lig", leagueId = "322";
    //For league selection const league name and id.
    private final String[][] leaguesInfo = {
            {"Süper Lig", "Major League Soccer", "La Liga"},
            {"322", "332", "302"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<ItemsViewModel> data = new ArrayList<>();
        data.add(new ItemsViewModel(null, "Team", "0"));
        adapter = new LeagueDataAdapter(data, this);
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
    }

    private void getLeagueData() {
        ApiConnection connection = new ApiConnection();
        Call<List<LeagueData>> leagueDataCall = connection.leagueScore(leagueId);
        leagueDataCall.enqueue(new Callback<List<LeagueData>>() {
            @Override
            public void onResponse(@NonNull Call<List<LeagueData>> call,
                                   @NonNull Response<List<LeagueData>> response) {
                List<LeagueData> getLeagueData = response.body();
                if (getLeagueData != null) {
                    ArrayList<ItemsViewModel> data = new ArrayList<>();
                    for (LeagueData leagueData : getLeagueData) {
                        data.add(new ItemsViewModel(
                                leagueData.teamBadge, //image url
                                leagueData.teamName, //team name
                                leagueData.overallLeaguePTS //league score
                        ));
                    }
                    adapter.addList(data);
                }
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<LeagueData>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this,
                        "League couldn't be loaded.", Toast.LENGTH_LONG).show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void leagueSelection() {
        new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("Chose a league")
                .setItems(leaguesInfo[0], (dialogInterface, i) -> {
                    leagueName = leaguesInfo[0][i];
                    leagueId = leaguesInfo[1][i];
                    mainToolbar.setTitle(leagueName);
                    getLeagueData();
                })
                .show();
    }
}