package com.example.thanhnv.androidwebservicetutorial;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import android.widget.LinearLayout;
public class MainActivity extends FragmentActivity  {
    private CurrentTempFragment fragmentCurrentTemp ;
    private DayTempFragment fragmentDayTemp ;
    private WeedTempFrament framentWeedTemp ;


    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);



    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(Object object) {
            Log.i("position1", String.valueOf(super.getItemPosition(object)));

            return super.getItemPosition(object);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            Log.i("position", String.valueOf(position));
            switch (position){
                case 0:
                    if (fragmentCurrentTemp == null)
                        fragmentCurrentTemp = new CurrentTempFragment();
                    return fragmentCurrentTemp;
                case 1:
                    if (fragmentDayTemp == null)
                        fragmentDayTemp = new DayTempFragment();
                    return fragmentDayTemp;
                case 2:
                    if (framentWeedTemp  == null)
                        framentWeedTemp = new WeedTempFrament();
                    return framentWeedTemp;
                default:
                    return null;

            }



        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
