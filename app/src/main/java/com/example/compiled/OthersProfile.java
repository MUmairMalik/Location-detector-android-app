package com.example.compiled;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

public class OthersProfile extends AppCompatActivity {


    CircularImageView img1;
    CircularImageView img2;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_profile);

        img1=(CircularImageView)findViewById(R.id.Othersprof1);
        img2=(CircularImageView)findViewById(R.id.Otherscam1);
        t1=(TextView)findViewById(R.id.Othersname);
        t2=(TextView)findViewById(R.id.Othersaddress);
        t3=(TextView)findViewById(R.id.Othersoverall);
        t4=(TextView)findViewById(R.id.Othersperc);

    }


}
