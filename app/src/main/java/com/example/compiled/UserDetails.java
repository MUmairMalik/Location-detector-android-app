package com.example.compiled;

public class UserDetails {
    String name;
    String address;
    float accuracy;
    float lattitude;
    float ratings;
    float longitude;

    UserDetails()
    {}

    public UserDetails(String name, String address, float accuracy, float lattitude, float ratings, float longitude) {
        this.name = name;
        this.address = address;
        this.accuracy = accuracy;
        this.lattitude = lattitude;
        this.ratings = ratings;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
