package com.example.thanhnv.androidwebservicetutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by thanhnv on 1/26/16.
 */
public class CurrentTempFragment extends Fragment implements OnTaskCompleted{
    TextView mtemp;
    TextView mWindspeed;
    TextView mweatherdescription;
    LinearLayout mmainScreen;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(
                R.layout.current_temp_fragment, container, false);
        mmainScreen = (LinearLayout)rootView.findViewById(R.id.view_screen);
        mmainScreen.setVisibility(View.INVISIBLE);

        GPSTracker tracker = new GPSTracker(this.getContext());
        if (tracker.canGetLocation){
            Location location = new Location(Double.toString(tracker.getLatitude()),Double.toString(tracker.getLongitude()));
            String serverURL =  "http://api.openweathermap.org/data/2.5/weather?";
            AsysTaskGetCurrentTemp asyncTask = new AsysTaskGetCurrentTemp(this.getContext(),this,serverURL,location);
            asyncTask.execute();

        }else {
            Toast.makeText(this.getContext(), "Unabletofind", Toast.LENGTH_LONG).show();
        }
        mmainScreen = (LinearLayout)rootView.findViewById(R.id.view_screen);
        mtemp = (TextView)rootView.findViewById(R.id.text_temp);
        mWindspeed = (TextView) rootView.findViewById(R.id.text_windspeed);
        mweatherdescription = (TextView) rootView.findViewById(R.id.text_weatherdescription);

        return rootView;
    }

    @Override
    public void onTaskCompleted(DataLocation data_location) {

        mmainScreen.setVisibility(View.VISIBLE);

        mtemp.setText(data_location.temp);
        mWindspeed.setText(data_location.wind_speed);
        mweatherdescription.setText("Wind Speed: "+data_location.description_weather+"m/s");
        this.getActivity().setTitle(data_location.location_name);
    }
}
