package com.example.build_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    TextView textView;
    public static ArrayList<Note> notes = new ArrayList<>();

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        textView = (TextView)findViewById(R.id.welcome_text);
        Intent intent = getIntent();
        String username = intent.getStringExtra("message");
        textView.setText("Welcome "+ username + " !");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase =context.openOrCreateDatabase("notes",Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        notes = dbHelper.readNotes(username);

        System.out.println("note size:"+ notes.size());
        if(noteid != -1) {
            ArrayList<String> displayNotes = new ArrayList<>();
            for (Note note : notes) {
                displayNotes.add(String.format("Title:%a\nDate:%s", note.getTitle(), note.getData()));

                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
                ListView listView = (ListView) findViewById(R.id.notesListView);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent intent = new Intent(getApplicationContext(), Notes.class);
                        intent.putExtra("noteid",position);
                        startActivity(intent);
                    }
                });
            }

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id  = item.getItemId();
        if(id == R.id.menu1){
            goToLoginScreen();
            return true;
        }
        if(id == R.id.menu2){
            goToNotesScreen();
            return true;
        }
        return  true;
    }

    public void goToLoginScreen(){

        //SharedPreferences.Editor = pr
        Intent intent = new Intent(this,MainActivity.class);
       SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
       sharedPreferences.edit().clear().apply();
        startActivity(intent);

    }
    public void goToNotesScreen() {

        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);

    }
}