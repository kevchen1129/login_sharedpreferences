package com.example.build_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;




    public void clickFunction(View view){
        EditText editText = findViewById(R.id.name);
        String str  = editText.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString("username",str).apply();

        goToHomeScreen(str);
    }

    public void goToHomeScreen (String s){
        Intent intent = new Intent(this,HomeScreen.class);
        intent.putExtra("message",s);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE);

        if(!sharedPreferences.getString(usernameKey,"").equals("")){
            String str = sharedPreferences.getString(usernameKey,"");
             goToHomeScreen(str);

        }else{
            setContentView(R.layout.activity_main);
        }
      //  setContentView(R.layout.activity_main);

    }


}