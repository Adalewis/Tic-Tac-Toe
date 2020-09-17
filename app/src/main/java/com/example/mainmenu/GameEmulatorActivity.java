package com.example.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;

import java.util.HashMap;


public class GameEmulatorActivity extends AppCompatActivity implements View.OnClickListener {


    HashMap<Integer, String> hashMap;
    DataBaseHelper dataBaseHelper;
    private Button[][] buttons = new Button[3][3];
    private boolean playerTurn = true;
    private String gameInfo = "";
    private TextView gameView;
    private int turns;
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_emulator);
        gameView = (TextView) findViewById(R.id.game_display);
        dataBaseHelper = new DataBaseHelper(GameEmulatorActivity.this);
        Intent intentHashMap = getIntent();
        hashMap = (HashMap<Integer, String>) intentHashMap.getSerializableExtra("map");
        gameView.setText(hashMap.get(1) + "'s turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int btnID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(btnID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonNG = findViewById(R.id.button_new_game);
        buttonNG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (playerTurn) {

            ((Button) v).setText("X");
            gameInfo = hashMap.get(2) + "'s turn";
            gameView.setText(gameInfo);
        } else {

            ((Button) v).setText("O");
            gameInfo = hashMap.get(1) + "'s turn";
            gameView.setText(gameInfo);
        }
        turns++;
        if (checkForWin()) {
            if (playerTurn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (turns == 9) {
            draw();
        } else {
            playerTurn = !playerTurn;
        }
    }


    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            //checks horizontally
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            //checks vertically
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        //checks diagonally left down
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        //checks diagonally right down
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void player1Wins() {
        gameInfo = hashMap.get(1) + " wins";
        gameView.setText(gameInfo);
        dataBaseHelper.updateWins(hashMap.get(1));
        dataBaseHelper.updateLosses(hashMap.get(2));
        scoreboardActivity();
    }

    private void player2Wins() {
        gameInfo = hashMap.get(2) + " wins";
        gameView.setText(gameInfo);
        dataBaseHelper.updateWins(hashMap.get(2));
        dataBaseHelper.updateLosses(hashMap.get(1));
        scoreboardActivity();
    }

    private void draw() {
        gameInfo = "It is a draw";
        gameView.setText(gameInfo);
        dataBaseHelper.updateTies(hashMap.get(1));
        dataBaseHelper.updateTies(hashMap.get(2));
        scoreboardActivity();
    }

    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        turns = 0;
        gameInfo = hashMap.get(1) + "'s turn";
        gameView.setText(gameInfo);
    }

    @Override
    public void onPause() {

        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("gameInfo", gameInfo);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int btnID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(btnID);
                String btnText = buttons[i][j].getText().toString();
                System.out.println(btnText);
                editor.putString("buttons" + i + j, btnText);
            }
        }
        editor.commit();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        gameView.setText(savedValues.getString("gameInfo", ""));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(savedValues.getString("buttons" + i + j, ""));
            }
        }
    }

    public void scoreboardActivity() {
        Intent intentScore = new Intent(this, ScoreboardActivity.class);
        startActivity(intentScore);
    }

}