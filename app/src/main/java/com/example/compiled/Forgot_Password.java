package com.example.compiled;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Forgot_Password extends AppCompatActivity {


    TextView t1;
    TextView t2;
    TextView t3;

    EditText e1;

    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forgot__password);
        super.onCreate(savedInstanceState);

        e1=findViewById(R.id._emailIn);

        b1=findViewById(R.id.resetPass);
        b2=findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!e1.getText().toString().isEmpty()) {
                    Intent goToReset = new Intent(Forgot_Password.this, ResetPassword.class);
                    startActivity(goToReset);
                } else {
                    Toast.makeText(Forgot_Password.this,
                            R.string.emailIn, Toast.LENGTH_LONG).show();

                }
            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent goToSignIn=new Intent(Forgot_Password.this,SignIn.class);
               startActivity(goToSignIn);

            }
        });
    }
}
