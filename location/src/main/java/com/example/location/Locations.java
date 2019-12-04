package com.example.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class Locations implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    //Intervals for resolution requests
    private static final long UPDATE_INTERVAL = 10000, FASTEST_INTERVAL = 10000; // = 2 seconds
    Context context;
    //Variables
    private String mLongitude, mLatitude, mAccuracy, mAddress;
    private Location mLocation;
    //For location updates
    private LocationRequest locationRequest;
    //Google Api client which is instantiated
    // later on the onCreate method
    private GoogleApiClient googleApiClient;

    public void start(Context context) {
        this.context = context;
        // We build google api client
        googleApiClient = new GoogleApiClient.Builder(context).
                addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        //Because location updates are required
        locationRequest = new LocationRequest();
        googleApiClient.connect();
    }

    public void stop() {
        if (googleApiClient != null && googleApiClient.isConnected()) {
            // Permissions is granted, we get last location
            FusedLocationProviderClient fusedLocationProviderClient = getFusedLocationProviderClient(context);
            //FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            //Above function is deprecated, bellow  is alternative
            LocationCallback locationCallback = new LocationCallback();
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        FusedLocationProviderClient fusedLocationProviderClient = getFusedLocationProviderClient(context);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations, this can be null.
                if (location != null) {
                    mLatitude = Double.toString(location.getLatitude());
                    mLongitude = Double.toString(location.getLongitude());
                    mAccuracy = Double.toString(location.getAccuracy());

                    //Getting location address from geo-coder
                    mAddress = getAddress(Double.parseDouble(mLatitude), Double.parseDouble(mLongitude));
                    //mLocation = location;

                    CurrentLocation currentLocation = CurrentLocation.getInstance();
                    currentLocation.setLocation(location);

//Database
                    //    LocationRepo locationRepo = new LocationRepo(context);
                    //    locationRepo.insertTask(mLatitude, mLongitude, mAccuracy, mAddress);
                    //   locationRepo.getTasks();
                }
            }
        });
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        //Requesting updates
        FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    public String getAddress(double latitude, double longitude) {
        String address = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            address = addresses.get(0).getAddressLine(0);
            Log.d("Address", address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    public String getmLongitude() {
        return mLongitude;
    }

    public String getmLatitude() {
        return mLatitude;
    }

    public Location getmLocation() {
        return mLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            mLatitude = Double.toString(location.getLatitude());
            mLongitude = Double.toString(location.getLongitude());
            mAccuracy = Double.toString(location.getAccuracy());

            //Getting location address from geo-coder
            mAddress = getAddress(Double.parseDouble(mLatitude), Double.parseDouble(mLongitude));
        }
        if (location != null) {

            CurrentLocation currentLocation = CurrentLocation.getInstance();
            currentLocation.setLocation(location);

            this.mLocation = location;

            float distance = location.distanceTo(mLocation);
            Log.d("Old: ", mLatitude + ", " + mLongitude);
            Log.d("New: ", location.getLatitude() + ", " + location.getLongitude());
//            Log.d("Distance: ", Double.toString(distance));
            if (distance >= 150) {
//                //database      LocationRepo locationRepo = new LocationRepo(context);
//                //locationRepo.insertTask(mLatitude, mLongitude, mAccuracy, mAddress);
//               //locationRepo.getTasks();
                Toast.makeText(this.context, "LOCATION CHANGED", Toast.LENGTH_SHORT).show();
//            }
            }
        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
