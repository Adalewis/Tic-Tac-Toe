package com.example.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;

import java.util.HashMap;


public class GameEmulatorActivity extends AppCompatActivity {
    private Button btn_player1Wins, btn_player2Wins, btn_ties;
    private TextView player1, player2, player1WinsDisplay, player2WinsDisplay, tieDisplay;
    HashMap<Integer, String> hashMap;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_emulator);

        dataBaseHelper = new DataBaseHelper(GameEmulatorActivity.this);
        btn_player1Wins = findViewById(R.id.btn_player1Wins);
        btn_player2Wins = findViewById(R.id.btn_player2Wins);
        btn_ties = findViewById(R.id.btn_ties);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        player1WinsDisplay = findViewById(R.id.player1WinsDisplay);
        player2WinsDisplay = findViewById(R.id.player2WinsDisplay);
        tieDisplay = findViewById(R.id.tieDisplay);
        Intent intentHashMap = getIntent();
        hashMap = (HashMap<Integer, String>)intentHashMap.getSerializableExtra("map");

        tieDisplay.setText("It's a tie!");
        player1.setText("Player 1 " + hashMap.get(1));
        player2.setText("Player 2 " + hashMap.get(2));
        player1WinsDisplay.setText(hashMap.get(1) + " wins!");
        player2WinsDisplay.setText(hashMap.get(2) + " wins!");

        btn_player1Wins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.updateWins(hashMap.get(1));
                dataBaseHelper.updateLosses(hashMap.get(2));
                scoreboardActivity();
            }
        });
        btn_player2Wins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.updateWins(hashMap.get(2));
                dataBaseHelper.updateLosses(hashMap.get(1));
                scoreboardActivity();
            }
        });
        btn_ties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.updateTies(hashMap.get(1));
                dataBaseHelper.updateTies(hashMap.get(2));
                scoreboardActivity();
            }
        });



    }
    public void scoreboardActivity() {
        Intent intentScore = new Intent(this, ScoreboardActivity.class);
        startActivity(intentScore);
    }
}
