package com.example.mainmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String PLAYER_TABLE = "PLAYER_TABLE";
    public static final String COLUMN_PLAYER_ID = "PLAYER_ID";
    public static final String COLUMN_PLAYER_NAME = "PLAYER_NAME";
    public static final String COLUMN_PLAYER_WINS = "PLAYER_WINS";
    public static final String COLUMN_PLAYER_LOSSES = "PLAYER_LOSSES";
    public static final String COLUMN_PLAYER_TIES = "PLAYER_TIES";
    public DataBaseHelper(Context context) { super(context, "player.db", null, 1);}


    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PLAYER_TABLE + " " +
                "(" + COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PLAYER_NAME + " TEXT, " + COLUMN_PLAYER_WINS + " INT, " +
                COLUMN_PLAYER_LOSSES + " INT, " + COLUMN_PLAYER_TIES + " INT)";
        db.execSQL(createTableStatement);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PLAYER_NAME, name);
        long insert = db.insert(PLAYER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateWins(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PLAYER_TABLE + " WHERE " + COLUMN_PLAYER_NAME + " =" +"'"+ name +"'";
        Log.d("check query", query);
        Cursor cursor = db.rawQuery(query, null);
        int playerWins = 0;
        if (cursor.moveToFirst()) {
            playerWins = cursor.getInt(2);
        } else {
        }
        playerWins = playerWins + 1;
        String queryUpdate = "UPDATE " + PLAYER_TABLE + " SET " + COLUMN_PLAYER_WINS + " = " + playerWins +" WHERE " + COLUMN_PLAYER_NAME + " =" +"'"+ name +"'";
        Log.d("check query", queryUpdate);
        Cursor csrUpdate = db.rawQuery(queryUpdate, null);
        if (csrUpdate.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateLosses(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PLAYER_TABLE + " WHERE " + COLUMN_PLAYER_NAME + " =" +"'"+ name +"'";
        Cursor cursor = db.rawQuery(query, null);
        int playerLosses = 0;
        if (cursor.moveToFirst()) {
            playerLosses = cursor.getInt(3);
        } else {
        }
        playerLosses = playerLosses + 1;
        String queryUpdate = "UPDATE " + PLAYER_TABLE + " SET " + COLUMN_PLAYER_LOSSES + " = " + playerLosses +" WHERE " + COLUMN_PLAYER_NAME + " =" +"'"+ name +"'";
        Cursor csrUpdate = db.rawQuery(queryUpdate, null);
        if (csrUpdate.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateTies(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PLAYER_TABLE + " WHERE " + COLUMN_PLAYER_NAME + " =" +"'"+ name +"'";
        Cursor cursor = db.rawQuery(query, null);
        int playerTies = 0;
        if (cursor.moveToFirst()) {
            playerTies = cursor.getInt(4);
        } else {
        }
        playerTies = playerTies + 1;
        String queryUpdate = "UPDATE " + PLAYER_TABLE + " SET " + COLUMN_PLAYER_TIES + " = " + playerTies +" WHERE " + COLUMN_PLAYER_NAME + " =" +"'"+ name +"'";
        Cursor csrUpdate = db.rawQuery(queryUpdate, null);
        if (csrUpdate.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }




    public boolean deleteName(String nameValuePassed) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PLAYER_TABLE + " WHERE " + COLUMN_PLAYER_NAME + " = " + "'"+ nameValuePassed +"'";
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> justWins() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> playWinPts = new ArrayList<>();
        String query = "SELECT * FROM " + PLAYER_TABLE + " ORDER BY " + COLUMN_PLAYER_WINS + " DESC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Integer playerWin = cursor.getInt(2);
                playWinPts.add(playerWin.toString());
            } while (cursor.moveToNext());
        } else {
        }
        cursor.close();
        db.close();
        return playWinPts;
    }

    public List<String> justLosses() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> playLossPts = new ArrayList<>();
        String query = "SELECT * FROM " + PLAYER_TABLE + " ORDER BY " + COLUMN_PLAYER_WINS + " DESC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Integer playerLose = cursor.getInt(3);
                playLossPts.add(playerLose.toString());
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return playLossPts;
    }

    public List<String> justTies() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> playerTiePts = new ArrayList<>();
        String query = "SELECT * FROM " + PLAYER_TABLE + " ORDER BY " + COLUMN_PLAYER_WINS + " DESC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Integer playerTies = cursor.getInt(4);
                playerTiePts.add(playerTies.toString());
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return playerTiePts;
    }

    public List<String> justNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> playNames = new ArrayList<>();
        String query = "SELECT * FROM " + PLAYER_TABLE + " ORDER BY " + COLUMN_PLAYER_WINS + " DESC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String playerName = cursor.getString(1);
                playNames.add(playerName);
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return playNames;
    }
}
