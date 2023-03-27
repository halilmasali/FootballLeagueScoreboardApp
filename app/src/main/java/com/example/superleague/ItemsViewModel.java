package com.example.superleague;

public class ItemsViewModel {
    private String imageUrl;
    private String teamName;
    private String teamScore;

    public ItemsViewModel(String imageUrl, String teamName, String teamScore) {
        this.imageUrl = imageUrl;
        this.teamName = teamName;
        this.teamScore = teamScore;
    }

    public String getTeamScore() {
        return teamScore;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getTeamName() {
        return teamName;
    }
}
