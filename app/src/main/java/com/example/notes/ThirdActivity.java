package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {

    DBHelper dbHelper;
    private EditText newNoteText;
    private Button saveButton;

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        // 1. Get EditText view
        newNoteText = findViewById(R.id.noteText);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSaveButton(view);
            }
        });
        // 2. Get Intent
        Intent intent = getIntent();
        // 3. Get the value of integer "noteid" from intent
        int id = intent.getIntExtra("noteid", -1 );
        // 4. Initialise class variable "noteid" with the value from intent
        if (id != -1){
            // Display content of note by retrieving "notes" ArrayList in SecondActivity
            Note note = ResultActivity.notes.get(id);
            String noteContent = note.getContent();
            // Use editText.setText() to display the contents of this note on screen
            newNoteText.setText(noteContent);
        }
    }

    public void setSaveButton(View view){
        // 1. Get editText view and the content that user entered
        EditText text = findViewById(R.id.noteText);
        String note1 = text.getText().toString();
        // 2. Initialise SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        // 3. Initialise DBHelper class
        dbHelper = new DBHelper(sqLiteDatabase);
        // 4. Set username in the following variable by fetching it from SharedPreferences.
        SharedPreferences sharedPref = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        String username = sharedPref.getString("username","");

        // 5. Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        Note note = new Note(date, username, null, note1);
        if (noteid == -1){ // Add note
            title = "NOTE_" + (ResultActivity.notes.size() + 1);
            dbHelper.saveNotes(username, title, note.getContent(), date);
        }else{ // Update note
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, note.getContent(), note.getUsername());
        }
        // 6. Go to second activity using intents
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}