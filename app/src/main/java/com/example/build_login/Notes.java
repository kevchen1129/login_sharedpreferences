package com.example.build_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Notes extends AppCompatActivity {
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
    }
//save function
    public void clickFunction(View view){

        
        Note note = HomeScreen.notes.get(noteid);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE,null);
        dbHelper = new DBHelper(sqLiteDatabase);

        String username  = sharedPreferences.getString("SHARED_PREF","");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        String title = "";

        if(noteid == -1){
            title = "NOTE_"+ (HomeScreen.notes.size()+1);
            dbHelper.saveNotes(username,title,note.getContent(),date);
        }else{
            title = "NOTE_"+(noteid+1);
            dbHelper.updateNote(title,date,note.getContent(),username);
        }

       Intent intent1 = new Intent(this,HomeScreen.class);
        startActivity(intent1);
    }




}