package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class welpage extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_welpage );

        fAuth = FirebaseAuth.getInstance ();

        Button stbt = findViewById (R.id.stbt );
        stbt.setOnClickListener ( v -> {
            startActivity (  new Intent (this,logp.class) );
        } );

        if (fAuth.getCurrentUser ( ) != null) {
            startActivity ( new Intent ( getApplicationContext ( ) , Mainmenu.class ) );
            finish ( );
        }
    }
}