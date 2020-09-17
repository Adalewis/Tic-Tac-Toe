package com.example.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardActivity extends Activity {
    private Button btn_return;
    ListView lv_playerList;
    DataBaseHelper dataBaseHelper;
    ListAdapter playerArrayAdapter;
    final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        dataBaseHelper = new DataBaseHelper(ScoreboardActivity.this);
        btn_return = findViewById(R.id.btn_return);
        List<String> names = dataBaseHelper.justNames();
        List<String> wins = dataBaseHelper.justWins();
        List<String> losses = dataBaseHelper.justLosses();
        List<String> ties = dataBaseHelper.justTies();
        CustomList listAdapter = new
                CustomList(ScoreboardActivity.this, names, wins, losses, ties);
        lv_playerList =(ListView)findViewById(R.id.lv_playerList);
        lv_playerList.setAdapter(listAdapter);
        btn_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainActivity();
            }
        });
    }

    public void mainActivity() {
        Intent intentMain = new Intent( this, MainActivity.class);
        startActivity(intentMain);
    }



}
