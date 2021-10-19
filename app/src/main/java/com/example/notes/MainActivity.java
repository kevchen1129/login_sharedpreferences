package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.note", Context.MODE_PRIVATE);
        if (!sharedPreferences.getString(usernameKey, "").equals("")){
            String user = sharedPreferences.getString(usernameKey, "");
            goToResultPage(user);
        }else{
            setContentView(R.layout.activity_main);
            usernameText = (EditText) findViewById(R.id.username);
            passwordText = (EditText) findViewById(R.id.password);
            loginButton = findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(view);
                }
            });
//        String user = sharedPreferences.getString(usernameKey, "");
//        String usernameKey = usernameText.getText().toString();
        }
    }

    public void onButtonClick(View view){
        String username = usernameText.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username).apply();
        goToResultPage(username);
    }

    public void goToResultPage(String s){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("username", s);
        startActivity(intent);
    }
}