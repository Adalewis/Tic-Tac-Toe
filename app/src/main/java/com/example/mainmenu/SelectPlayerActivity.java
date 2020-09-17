package com.example.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class SelectPlayerActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    TextView textView;
    ListView lv_playerList;
    ArrayAdapter playerArrayAdapter;
    final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);
        lv_playerList = (ListView)findViewById(R.id.listView);
        textView = (TextView)findViewById(R.id.textView);
        dataBaseHelper = new DataBaseHelper(SelectPlayerActivity.this);
        final int playerNum;
        playerNum = getIntent().getExtras().getInt("Player_Num");
        textView.setText("Player " + playerNum);
        final String TAG = "MyActivity";
        Intent intentHashMap = getIntent();
        final HashMap<Integer, String> map = (HashMap<Integer, String>)intentHashMap.getSerializableExtra("map");
        showPlayerList(dataBaseHelper);


        lv_playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (map == null) {
                    Log.d(TAG, "map is empty");
                } else {
                    for (Map.Entry mapFromIntent : map.entrySet()) {
                        hashMap.put(mapFromIntent.getKey(), mapFromIntent.getValue());
                    }
                }

                String nameValuePassed = dataBaseHelper.justNames().get(position);
                if (hashMap.containsValue(nameValuePassed)){
                    Toast.makeText(getApplicationContext(), "Please select a different name", Toast.LENGTH_SHORT).show();
                } else {
                    hashMap.put(playerNum, nameValuePassed);
                    mainActivity();
                }

            }
        });

    };
   public void showPlayerList(DataBaseHelper dataBaseHelper) {
        playerArrayAdapter = new ArrayAdapter<>(SelectPlayerActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.justNames());
        lv_playerList.setAdapter(playerArrayAdapter);
    }

    public void mainActivity() {
        Intent intentMain = new Intent( this, MainActivity.class);
        intentMain.putExtra("map", hashMap);
        startActivity(intentMain);
    }


}
