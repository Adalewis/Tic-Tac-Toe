package com.example.mainmenu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> names;
    private final List<String> wins;
    private final List<String> losses;
    private final List<String> ties;
    public CustomList(Activity context, List<String> names, List<String> wins, List<String> losses, List<String> ties) {
        super(context, R.layout.custum_row, names);
        this.context = context;
        this.names = names;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custum_row, null, true);
        TextView txt_name = (TextView) rowView.findViewById(R.id.player_name_score);
        TextView txt_wins = (TextView) rowView.findViewById(R.id.player_win_score);
        TextView txt_losses = (TextView) rowView.findViewById(R.id.player_losses_score);
        TextView txt_ties = (TextView) rowView.findViewById(R.id.player_ties_score);

        txt_name.setText(names.get(position));
        txt_wins.setText(wins.get(position));
        txt_losses.setText(losses.get(position));
        txt_ties.setText(ties.get(position));
        return rowView;
    }

}
