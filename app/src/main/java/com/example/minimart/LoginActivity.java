package com.example.minimart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonsignIn;
    private EditText email;
    private EditText password;
    private TextView signUp,passwordforgot;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        email=(EditText) findViewById(R.id.TextEmail);
        password =(EditText)findViewById(R.id.TextPassword);
        buttonsignIn=(Button)findViewById(R.id.buttonSignIn);
        signUp=(TextView)findViewById(R.id.Signup);
        passwordforgot=(TextView)findViewById(R.id.forgotpassword);
        buttonsignIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        passwordforgot.setOnClickListener(this);
    }

    private void userLogin()
    {
        String emailuser= email.getText().toString().trim();
        String pwduser=password.getText().toString().trim();
        if(TextUtils.isEmpty(emailuser))
        {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pwduser)) {
            Toast.makeText(this, "Please enter passsword", Toast.LENGTH_SHORT).show();
            return;

        }
        com.example.minimart.UserModel.email=emailuser;
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(emailuser,pwduser).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressDialog.dismiss();
                if(task.isSuccessful())
                {
                    finish();
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),HomeDashBoard.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login Failed,Please try again",Toast.LENGTH_SHORT).show();
                    email.setText("");
                    password.setText("");
                    return;
                }
            }
            });

    }
    @Override
    public void onClick(View v) {
        if(v==buttonsignIn)
        {
            userLogin();
        }
        if(v==signUp)
        {
            finish();
            startActivity(new Intent(this, com.example.minimart.SignUpActivity.class));
        }
        if(v==passwordforgot)
        {
            finish();
            startActivity(new Intent(this, ResetPasswordActivity.class));
        }
    }
}
