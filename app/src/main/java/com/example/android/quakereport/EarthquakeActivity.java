/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /** Adapter for the list of earthquakes */
    private QuakeAdapter mAdapter;

    // this is for athens
   // private static final String EARTHQUAKES_REQUEST_URL =
     //       "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=1999-01-01&endtime=NOW&%20maxradiuskm=300.0&minmagnitude=5.5&latitude=39.89&longitude=23.72";

    // this is for the top 10 most recent earthquakes in the world with at least a magnitude of 6.
    private static final String EARTHQUAKES_REQUEST_URL =
            //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6.3&limit=10";
    //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&starttime=2018-01-01&minmag=7";
    // https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&starttime=2018-01-01&minmag=7

    "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-01-01&maxradiuskm=200.0&latitude=39.89&longitude=23.72&orderby=time";
    // recent earthquakes around athens (all magnitudes, since beginning of 2018)
    // https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-01-01&maxradiuskm=200.0&latitude=39.89&longitude=23.72

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new QuakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


        // Start the AsyncTask to fetch the earthquake data
        EarthquakeAsyncTask task = new EarthquakeAsyncTask(EarthquakeActivity.this);
        task.execute(EARTHQUAKES_REQUEST_URL);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(LOG_TAG, "MPAINW STIN "+"OnCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(LOG_TAG, "MPAINW STIN "+"onOptionsItemSelected");
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        private ProgressDialog dialog;

        public EarthquakeAsyncTask (Activity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Κάτι ψήνεται..\n Υπομονή !");
            dialog.setIndeterminate(true);
            dialog.show();
        }


        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link Earthquake}s as the result.
         */
        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Earthquake> result = QueryUtils.fetchEarthquakes(urls[0]);
            return result;
        }



        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * query to USGS. Then we update the adapter with the new list of earthquakes,
         * which will trigger the ListView to re-populate its list items.
         */
        @Override
        protected void onPostExecute(List<Earthquake> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }

    }


}




/*

    old onCreate:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute();

    }



old doInBackground


@Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {

            ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakes(EARTHQUAKES_REQUEST_URL);

            return earthquakes;
        }



old onPostExecute


@Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            if (earthquakes == null) {
                return;
            }

            updateUi(earthquakes);

            //if (dialog.isShowing()) {
              //  dialog.dismiss();
            //}

        }


        old updateUI (not used anymore)

        private void updateUi(final List<Earthquake> earthquakes) {


        QuakeAdapter adapter = new QuakeAdapter(this, earthquakes);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


    }

 */