package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserChooseActivity extends AppCompatActivity {


    Button user , resturent_owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user = (Button) findViewById(R.id.user);
        resturent_owner = (Button) findViewById(R.id.resturent_owner);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.minimart.LoginActivity.class));
            }
        });

        resturent_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , com.example.minimart.SignUpActivity.class));
            }
        });
    }
}
