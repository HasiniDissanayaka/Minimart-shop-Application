package com.example.minimart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ofcontact extends AppCompatActivity {
    EditText Cmgs,Ctp,Cemail,Cname;
    private android.app.ProgressDialog ProgressDialog;
    FirebaseDatabase firebaseDatabase;
    String currentUserNIC,coname,coemail,cotp,comgs;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ofcontact );

        Cname = findViewById ( R.id.name);
        Cemail = findViewById ( R.id.email);
        Ctp = findViewById ( R.id.tp);
        Cmgs = findViewById ( R.id.mgs);


    }
    void getCurrentUserUUID ( ) {

        FirebaseUser user = FirebaseAuth.getInstance ( ).getCurrentUser ( );
        if (user != null) {
            currentUserNIC = user.getUid ( );
        } else {
            startActivity ( new Intent ( getApplicationContext ( ) , logp.class ) );
            finish ( );
            currentUserNIC = "UserID";
        }
  }



    public void Tosavedatabase() {

        ProgressDialog = new ProgressDialog (ofcontact.this);
        ProgressDialog.show();
        ProgressDialog.setContentView ( R.layout.loadpg );
        ProgressDialog.getWindow ().setBackgroundDrawableResource (
                android.R.color.transparent
        );

        final String savename = Cname.getText ().toString ().trim ();
        final String savemail = Cemail.getText ().toString ().trim ();
        final String savetp = Ctp.getText ().toString ().trim ();
        final String savemgs = Cmgs.getText ().toString ().trim ();

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current_user.getUid();

        startActivity ( new Intent ( getApplicationContext (),ofmenu.class  ) );

        try {
            HashMap userMap = new HashMap<>();
            userMap.put("User Name", savename );
            userMap.put("User Email", savemail);
            userMap.put("User Telephone Number ", savetp);
            userMap.put("Messages", savemgs);
            userMap.put("User Id", uid);

            FirebaseDatabase.getInstance ( ).getReference ( "Emagency Contact" ).child ( savemail ).setValue( userMap );

            ProgressDialog.dismiss ();
            Toast.makeText ( ofcontact.this , "We Got Your Message ", Toast.LENGTH_SHORT ).show ( );
        } catch (Exception e) {

            Toast.makeText ( ofcontact.this , e.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
            ProgressDialog.dismiss ();
        }


    }

    public void onClick ( View view ) {
        coname = Cname .getText ().toString ().trim ();
        coemail  = Cemail.getText ().toString ().trim ();
        cotp = Ctp.getText ().toString ().trim ();
        comgs = Cmgs.getText ().toString ().trim ();

        if(TextUtils.isEmpty ( coname  )){
            Cname.setError("Name Is Required.");
            return;
        }
        if(TextUtils.isEmpty ( cotp  )){
            Ctp.setError("Phone Number Is Required.");
            return;
        }
        if(cotp.length ()==10){
            Ctp.setError("Please use long Number");
            return;
        }
        if(TextUtils.isEmpty ( coemail )){
            Cemail.setError("Email Is Required.");
            return;
        }
        if(TextUtils.isEmpty ( comgs  )){
            Cmgs.setError("Message Is Required.");
            return;
        }
        Tosavedatabase();
    }
}