package com.example.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;


public class AddPlayerActivity extends AppCompatActivity {
    EditText editText;

    ListView lv_playerList;
    DataBaseHelper dataBaseHelper;
    Button btn_add, btn_delete;
    ArrayAdapter playerArrayAdapter;
    final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        editText = (EditText) findViewById(R.id.et_name);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        lv_playerList = findViewById(R.id.lv_playerList);
        dataBaseHelper = new DataBaseHelper(AddPlayerActivity.this);



        btn_add.setOnClickListener(new View.OnClickListener() {
            String name;
            @Override
            public void onClick(View v) {
                try {
                    name = editText.getText().toString();
                    ;
                } catch (Exception e) {
                    name = "error";
                }
                dataBaseHelper = new DataBaseHelper(AddPlayerActivity.this);
                dataBaseHelper.addName(name);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlayerList(dataBaseHelper);
            }
        });
        lv_playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameValuePassed = dataBaseHelper.justNames().get(position);
                dataBaseHelper.deleteName(nameValuePassed);
                showPlayerList(dataBaseHelper);
            }
        });

    }

    public void showPlayerList(DataBaseHelper dataBaseHelper) {
        playerArrayAdapter = new ArrayAdapter<>(AddPlayerActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.justNames());
        lv_playerList.setAdapter(playerArrayAdapter);
    }

}