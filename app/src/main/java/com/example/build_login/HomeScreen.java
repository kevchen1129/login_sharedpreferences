package com.example.build_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
    TextView textView;
    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

      //  preferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
//
        textView = (TextView)findViewById(R.id.welcome_text);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
       // String str  = preferences.getString("Name","");
        textView.setText("Welcome "+ str + " !");



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
}