package com.example.mainmenu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button btn_add, btn_selectP1, btn_selectP2, btn_gameEmulator, btn_scoreboard;
    private int playerNum;
    private boolean player1;
    HashMap<Integer, String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.btn_add);
        btn_gameEmulator = findViewById(R.id.btn_gameEmulator);
        btn_selectP1 = findViewById(R.id.btn_selectP1);
        btn_selectP2 = findViewById(R.id.btn_selectP2);
        btn_scoreboard = findViewById(R.id.btn_scoreboard);
        Intent intentHashMap = getIntent();
        hashMap = (HashMap<Integer, String>)intentHashMap.getSerializableExtra("map");


        btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNameActivity();
            }
        });
        btn_gameEmulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hashMap == null) {
                    player1 = true;
                    selectPlayerActivity();
                } else {
                    String value1 = hashMap.get(1);
                    String value2 = hashMap.get(2);
                    if (value1 == null) {
                        player1 = true;
                        selectPlayerActivity();
                    } else if (value2 == null) {
                        player1 = false;
                        selectPlayerActivity();
                    } else {
                        gameActivity();

                    }
                }
            }
        });
        btn_selectP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player1 = true;
                selectPlayerActivity();
            }
            });
        btn_selectP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1 = false;
                selectPlayerActivity();
            }
        });
        btn_scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreboardActivity();
            }
        });
    }



    public void addNameActivity() {
       Intent intentAddName = new Intent(this, AddPlayerActivity.class);
        startActivity(intentAddName);
    }

    public void gameActivity() {
        Intent intentGame = new Intent( this, GameEmulatorActivity.class);
        intentGame.putExtra("map", hashMap);
        startActivity(intentGame);
    }

    public void selectPlayerActivity() {
        Intent intentSelectPlayer = new Intent( this, SelectPlayerActivity.class);
        if(player1 == true) {
            playerNum = 1;
        } else {
            playerNum = 2;
        }
        intentSelectPlayer.putExtra("Player_Num", playerNum);
        intentSelectPlayer.putExtra("map", hashMap);
        startActivity(intentSelectPlayer);
    }
    public void scoreboardActivity() {
        Intent intentScore = new Intent(this, ScoreboardActivity.class);
        startActivity(intentScore);
    }


}
