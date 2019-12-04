package com.example.compiled;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    EditText user;
    EditText pass;

    Button signIn;
    Button forgotPass;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_sign_in);
        super.onCreate(savedInstanceState);

        mFirebaseAuth=FirebaseAuth.getInstance();

        user = (EditText) findViewById(R.id.mailIn_);
        pass = (EditText) findViewById(R.id.passwordIn_);

        signIn = (Button) findViewById(R.id.signIn_);
        forgotPass = findViewById(R.id.forgotPass_);


        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(mFirebaseUser!=null) {
                    Toast.makeText(SignIn.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent goToHome = new Intent(SignIn.this, DBMain.class);
                    startActivity(goToHome);
                }


            }
        };

        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean trigger = false;
                if (user.getText().toString().isEmpty()) {
                    Toast.makeText(SignIn.this,
                            R.string.usernameIn, Toast.LENGTH_LONG).show();
                    trigger = true;
                }

                if (pass.getText().toString().isEmpty()) {
                    Toast.makeText(SignIn.this,
                            R.string.passIn, Toast.LENGTH_LONG).show();
                    trigger = true;
                }


                if (!pass.getText().toString().isEmpty() && !user.getText().toString().isEmpty()) {
                    trigger = false;
                }
                if (!trigger) {
                    mFirebaseAuth.signInWithEmailAndPassword(user.getText().toString(),pass.getText().toString()).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignIn.this, "Sign In unsuccessful.Please try again later.", Toast.LENGTH_LONG).show();
                            } else {
                                Intent goToHome = new Intent(SignIn.this, Main2Activity.class);
                                startActivity(goToHome);
                            }
                        }
                    });

                }

            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToForgotPass=new Intent(SignIn.this,Forgot_Password.class);
                startActivity(goToForgotPass);
            }
        });


    }

    protected  void onStart() {

        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}

