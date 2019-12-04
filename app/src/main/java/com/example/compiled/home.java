
package com.example.compiled;
/*
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView list = findViewById(R.id.recycle);
        list.setLayoutManager(new LinearLayoutManager(this));

        Users u1=new Users("umair Sajjad","56789",R.drawable.pic);
        Users u2=new Users("umair MAlik","667899",R.drawable.pic);

        List<Users> u=new ArrayList<Users>();
        u.add(u1);
        u.add(u2);
        list.setAdapter(new RAdapter(u, new RAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Users item) {
                Toast.makeText(home.this , (String) "checking", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(home.this,ViewUsers.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(item);
                intent.putExtra("myjson", myJson);
                startActivity(intent);

            }
        }));


    }
}
*/
