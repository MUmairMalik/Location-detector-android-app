package com.example.location;

import android.location.Location;

public class CurrentLocation {
    private Location location=null;
    private static final CurrentLocation ourInstance = new CurrentLocation();

    public static CurrentLocation getInstance() {
        return ourInstance;
    }

    private CurrentLocation() {
    }

    public void setLocation(Location location){
        this.location=location;
    }

    public Location getLocation(){
        return this.location;
    }
}
