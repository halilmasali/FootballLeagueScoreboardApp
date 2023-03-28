package com.example.superleague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LeagueDataAdapter extends RecyclerView.Adapter<LeagueDataAdapter.ViewHolder> {

    private ArrayList<ItemsViewModel> mList;
    Context mContext;

    public LeagueDataAdapter(ArrayList<ItemsViewModel> mList, Context context) {
        this.mList = mList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public LeagueDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueDataAdapter.ViewHolder holder, int position) {

        ItemsViewModel itemsViewModel = mList.get(position);
        holder.teamName.setText(itemsViewModel.getTeamName());
        holder.teamScore.setText(itemsViewModel.getTeamScore());
        //Glide is automatic image download tool from url.
        Glide.with(mContext).load(itemsViewModel.getImageUrl()).into(holder.teamImage);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    public void addList(ArrayList<ItemsViewModel> addList) {
        if (!mList.containsAll(addList)) {
            mList.clear();
            mList = addList;
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView teamName, teamScore;
        public ImageView teamImage;

        public ViewHolder(View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.textTeamName);
            teamScore = itemView.findViewById(R.id.textTeamScore);
            teamImage = itemView.findViewById(R.id.imageView);
        }
    }
}
