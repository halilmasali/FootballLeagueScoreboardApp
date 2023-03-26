package com.example.superleague;

import java.util.ArrayList;

public class LeagueData {
    public ArrayList<Result> result;
    public boolean success;
}

class Result {
    public int rank;
    public int draw;
    public int lose;
    public int win;
    public int play;
    public int point;
    public int goalfor;
    public int goalagainst;
    public int goaldistance;
    public String team;
}
