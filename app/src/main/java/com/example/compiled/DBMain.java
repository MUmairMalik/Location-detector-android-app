package com.example.compiled;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DBMain extends AppCompatActivity {


    //view objects
    EditText Addr;
    EditText Accu;
    EditText Latt;
    EditText Long;
    EditText Rate;
    EditText Name;
    Button save;


    //a list to store all the artist from firebase database
    List<UserDetails> userDetails;

    //our database reference object
    DatabaseReference databaseArtists;
    ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmain);

        //getting the reference of artists node
        databaseArtists = FirebaseDatabase.getInstance().getReference("users");

        //getting views

        Addr=(EditText)findViewById(R.id.address);
        Accu=(EditText)findViewById(R.id.accuracy);
        Latt=(EditText)findViewById(R.id.lattitude);
        Rate=(EditText)findViewById(R.id.rating);
        Long=(EditText)findViewById(R.id.longitude);
        Name=(EditText)findViewById(R.id.name);
        save=(Button) findViewById(R.id.save1);
        listViewUsers=(ListView) findViewById(R.id.listview);


        //list to store artists

        userDetails=new ArrayList<>();

        //adding an onclicklistener to button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addUser();
            }
        });
    }

    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addUser() {
        //getting the values to save
        String name = Name.getText().toString();
        String addr = Addr.getText().toString();
        String acc=Accu.getText().toString();
        float fAcc=Float.parseFloat(acc);
        String latti=Latt.getText().toString();
        float fLatt=Float.parseFloat(latti);
        String Ratings=Rate.getText().toString();
        float fRate=Float.parseFloat(Ratings);
        String Longi=Long.getText().toString();
        float fLongi=Float.parseFloat(Longi);


        //getting a unique id using push().getKey() method
        //it will create a unique id and we will use it as the Primary Key for our User
        String id = databaseArtists.push().getKey();

        //creating an User Object
        UserDetails u1 = new UserDetails(name,addr,fAcc,fLatt,fRate,fLongi);

        //Saving the user
        databaseArtists.child(id).setValue(u1);



    }


    protected void onStart() {
        super.onStart();

        //attaching value event listener
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                userDetails.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    UserDetails user = postSnapshot.getValue(UserDetails.class);
                    //adding artist to the list
                    userDetails.add(user);
                }

                //creating adapter
                UserList userAdapter = new UserList(DBMain.this, userDetails);

                listViewUsers.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}