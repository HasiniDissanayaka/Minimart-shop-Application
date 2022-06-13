package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SaveProfileInformation extends AppCompatActivity implements View.OnClickListener{
    private TextView userdata;
    private Button viewData;
    private Button buttonlogout;
    private Button saveInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_toggle);
        userdata =(TextView) findViewById(R.id.textProfile);
        viewData=(Button)findViewById(R.id.buttonView);
        saveInformation=(Button)findViewById(R.id.saveInformation);
        saveInformation.setOnClickListener(this);
            viewData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SaveProfileInformation.this, com.example.minimart.ViewProfileInformation.class);
                    startActivity(intent);
                }
            });
            }


    @Override
    public void onClick(View v) {
        if(v==saveInformation)
        {
            finish();
            startActivity(new Intent(this, com.example.minimart.ProfileActivity.class));
        }
    }

}
