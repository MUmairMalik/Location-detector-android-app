package com.example.compiled;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.location.CurrentLocation;
import com.example.location.Locations;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Frag1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1_layout, container, false);

        RecyclerView list = view.findViewById(R.id.recycle);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        Users u1 = new Users("umair Sajjad", "56789", R.drawable.pic);
        Users u2 = new Users("umair MAlik", "667899", R.drawable.pic);

        List<Users> u = new ArrayList<Users>();
        u.add(u1);
        u.add(u2);
        list.setAdapter(new RAdapter(u, new RAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Users item) {
                Toast.makeText(getActivity(), (String) "checking", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ViewUsers.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(item);
                intent.putExtra("myjson", myJson);
                getActivity().startActivity(intent);

            }
        }));

        Locations location = new Locations();
        location.start(getActivity());

        CurrentLocation currentLocation = CurrentLocation.getInstance();
        Location l = currentLocation.getLocation();

        String s=" No Output";

        if(l!=null) {
            s = getAddress(l.getLatitude(), l.getLongitude());
        }

        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

//        Location l=location.getmLocation();
//        String s=location.getAddress(l.getLatitude(),l.getLongitude());
//        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
        return view;
    }

    public String getAddress(double latitude, double longitude) {
        String address = null;
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            address = addresses.get(0).getAddressLine(0);
            Log.d("Address", address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}