package com.example.compiled;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;

    Button b1;
    Button b2;

    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;

    ImageView logo;
    CheckBox viewPass;
    CheckBox acceptTerms;
    CheckBox gender1;
    CheckBox gender2;
    CheckBox gender3;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);


        b1 = (Button) findViewById(R.id.signIn);
        b2 = (Button) findViewById(R.id.signUp);

        e1 = (EditText) findViewById(R.id.conPasswordIn);
        e2 = (EditText) findViewById(R.id.passwordIn);
        e3 = (EditText) findViewById(R.id.fullNameIn);
        e4 = (EditText) findViewById(R.id.emailIn);
        acceptTerms = (CheckBox) findViewById(R.id.checkBox);
        gender1 = (CheckBox) findViewById(R.id.checkBox2);
        gender2 = (CheckBox) findViewById(R.id.checkBox3);
        gender3 = (CheckBox) findViewById(R.id.checkBox4);

        mFirebaseAuth=FirebaseAuth.getInstance();


        super.onCreate(savedInstanceState);

        viewPass = (CheckBox) findViewById(R.id.passCheck);
        viewPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    e1.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    e2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    e1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=e4.getText().toString();
                String pass=e2.getText().toString();

                if (!e1.getText().toString().isEmpty() && !e2.getText().toString().isEmpty() && !e3.getText().toString().isEmpty() && !e4.getText().toString().isEmpty() && acceptTerms.isChecked() && (gender1.isChecked() || gender2.isChecked() || gender3.isChecked())) {
                    if (e1.getText().toString().equals(e2.getText().toString())) {
                        mFirebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this,"Sign Up unsuccessful.Please try again later.", Toast.LENGTH_LONG).show();

                                }
                                else
                                {
                                    Intent goToHome = new Intent(MainActivity.this, Main2Activity.class);
                                    startActivity(goToHome);
                                }

                            }
                        });
                    }
                    else {
                        Toast.makeText(MainActivity.this,
                                R.string.passNotEqual, Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.missingField, Toast.LENGTH_LONG).show();
                }
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(goToSignIn);
            }
        });
    }

}

