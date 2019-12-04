package com.example.compiled;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 2/26/2017.
 */

public class UserList extends ArrayAdapter<UserDetails> {
    private Activity context;
    List<UserDetails> users;

    public UserList(Activity context, List<UserDetails> users) {
        super(context, R.layout.activity_user_list, users);
        this.context = context;
        this.users = users;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.activity_user_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAddress = (TextView) listViewItem.findViewById(R.id.textViewAddress);
        TextView textViewAccuracy = (TextView) listViewItem.findViewById(R.id.textViewAccuracy);
        TextView textViewLongitude = (TextView) listViewItem.findViewById(R.id.textViewLongitude);
        TextView textViewLatitude = (TextView) listViewItem.findViewById(R.id.textViewLatitude);
        TextView textViewRating = (TextView) listViewItem.findViewById(R.id.textViewRating);



        UserDetails user = users.get(position);
        textViewName.setText(user.getName());
        textViewAddress.setText(user.getAddress());
        textViewAccuracy.setText(String.valueOf(user.getAccuracy()));
        textViewLongitude.setText(String.valueOf(user.getLongitude()));
        textViewLatitude.setText(String.valueOf(user.getLattitude()));
        textViewRating.setText(String.valueOf( user.getRatings()));


        return listViewItem;
    }
}