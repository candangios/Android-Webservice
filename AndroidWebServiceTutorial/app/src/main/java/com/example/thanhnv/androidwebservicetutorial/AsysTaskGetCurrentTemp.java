package com.example.thanhnv.androidwebservicetutorial;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by thanhnv on 1/25/16.
 */
public class AsysTaskGetCurrentTemp extends AsyncTask<Void, Void, Void> {
    private final HttpClient Client = new DefaultHttpClient();
    private String Content;
    private String Error = null;

    Context mcontext;
    String address;
    String dataFind="";
    ProgressDialog dialog;
    private Location location;
    private OnTaskCompleted listener;

    public AsysTaskGetCurrentTemp(Context context, OnTaskCompleted listener, String addr, Location location) {
        this.listener = listener;
        this.mcontext = context;
        this.address = addr;
        this.location = location;
        dialog = new ProgressDialog(context);
    }
    protected void onPreExecute(){
        dialog.setMessage("Please wait..");
        dialog.show();

          address  +="&"+"lat="+this.location.Latitude+"&lon="+ this.location.Longitude+"&units=metric&APPID=4b1901b9b831ad90cf8c0cbaf31b45a7";


    }

    @Override

    protected Void doInBackground(Void... params) {

        /************ Make Post Call To Web Server ***********/
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL(this.address);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( dataFind );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "");
            }

            // Append Server Response To Content String
            Content = sb.toString();
        }
        catch(Exception ex)
        {
            Error = ex.getMessage();
        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        /*****************************************************/
        return null;
    }

    protected void onPostExecute(Void unused){
       dialog.dismiss();


        if (Error != null) {



        } else {



            /****************** Start Parse Response JSON Data *************/

            String OutputData = "";
            JSONObject jsonResponse;

            try {
                DataLocation data_location = new DataLocation();

                /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                jsonResponse = new JSONObject(Content);
                data_location.location_name = jsonResponse.optString("name");
                JSONArray arr_weathers = jsonResponse.optJSONArray("weather");
                if (arr_weathers.length() >0){
                    JSONObject weather = arr_weathers.getJSONObject(0);
                    data_location.description_weather = weather.optString("description");

                }
                JSONObject main = jsonResponse.optJSONObject("main");

                data_location.temp = main.optString("temp");

                JSONObject wind = jsonResponse.optJSONObject("wind");
                data_location.wind_speed = jsonResponse.optString("speed");

                JSONObject sys = jsonResponse.optJSONObject("sys");
                data_location.country = jsonResponse.optString("country");

            this.listener.onTaskCompleted(data_location);
            } catch (JSONException e) {


                e.printStackTrace();
            }
          cancel(true);


        }
    }
}
