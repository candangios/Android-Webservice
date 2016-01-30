package com.example.thanhnv.androidwebservicetutorial;

import android.app.ProgressDialog;
import android.util.Log;

import java.security.PublicKey;

/**
 * Created by thanhnv on 1/25/16.
 */
public class Location {
    public String Longitude;
    public String Latitude;

    public Location(String latitude, String longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }
}
