package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView userdata;
    private EditText username;
    private EditText address;
    private EditText phonenumber;
    private Button saveInformation;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("profile");
        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        FirebaseUser user=firebaseAuth.getCurrentUser();
        userdata =(TextView) findViewById(R.id.textProfile);
        username=(EditText) findViewById(R.id.TextName);
        address=(EditText) findViewById(R.id.textAddress);
        phonenumber=(EditText)findViewById(R.id.textPhoneNumber);
        saveInformation=(Button)findViewById(R.id.saveInformation);
        saveInformation.setOnClickListener(this);
    }
    private void setSaveInformation()
    {
        String name=username.getText().toString().trim();
        String addressdata=address.getText().toString().trim();
        String value = phonenumber.getText().toString().trim();
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(addressdata))
        {
            Toast.makeText(this,"Please enter your address",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(value))
        {
            Toast.makeText(this,"Please enter your mobile number",Toast.LENGTH_SHORT).show();
            return;
        }
        ProfileModel profileInformation=new ProfileModel(name,addressdata,value);
        profileInformation.setName(name);
        profileInformation.setAddress(addressdata);
        profileInformation.setPhonenumber(value);
        FirebaseUser eUser=firebaseAuth.getCurrentUser();
        databaseReference.child("profile information").setValue(profileInformation);
        if(databaseReference!=null&&!name.isEmpty()&&!addressdata.isEmpty()) {
            Toast.makeText(this, "Information Saved....", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Error,Please try again", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        if(v==saveInformation)
        {
            setSaveInformation();
            finish();
            startActivity(new Intent(this,SaveProfileInformation.class));
        }
    }
}