package com.example.thanhnv.androidwebservicetutorial;

/**
 * Created by thanhnv on 1/27/16.
 */
public class currentLocationOBJ {
    private static currentLocationOBJ ourInstance = new currentLocationOBJ();

    public static currentLocationOBJ getInstance() {
        return ourInstance;
    }

    private currentLocationOBJ() {
    }
}
