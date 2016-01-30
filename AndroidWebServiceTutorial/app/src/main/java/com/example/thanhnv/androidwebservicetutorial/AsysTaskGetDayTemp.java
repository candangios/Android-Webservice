package com.example.thanhnv.androidwebservicetutorial;

import android.os.AsyncTask;

/**
 * Created by thanhnv on 1/26/16.
 */
public class AsysTaskGetDayTemp extends AsyncTask<Void, Void, Void> {
    private OnTaskCompleted listener;
    private String addr;

    public AsysTaskGetDayTemp(String addr, OnTaskCompleted listener,String id){


    }

    protected void onPreExecute(){

    }
    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
    protected void onPostExecute(Void unused){


    }
}
