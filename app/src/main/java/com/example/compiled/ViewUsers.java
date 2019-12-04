package com.example.compiled;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class ViewUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);


        Gson gson = new Gson();
        Users ob = gson.fromJson(getIntent().getStringExtra("myjson"), Users.class);
        TextView nam=findViewById(R.id.RatenameView);
        TextView phnn=findViewById(R.id.RatePhnView);
        ImageView iv=findViewById(R.id.RateimageView);
        iv.setImageResource(ob.image);
        nam.setText(ob.name);
        phnn.setText(ob.phone);
        Button bt=findViewById(R.id.btn_rating);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewUsers.this , (String) "Rating", Toast.LENGTH_LONG).show();

            }
        });
    }
}
