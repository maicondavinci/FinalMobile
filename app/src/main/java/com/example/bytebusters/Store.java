package com.example.bytebusters;

import com.google.android.gms.maps.model.LatLng;

public class Store {
    private String name;
    private String mall;
    private String street;
    private String city;
    private String number;
    private LatLng location;

    public Store(String name, String mall, String street, String city, String number, LatLng location) {
        this.name = name;
        this.mall = mall;
        this.street = street;
        this.city = city;
        this.number = number;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getMall() {
        return mall;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

    public LatLng getLocation() {
        return location;
    }
}
