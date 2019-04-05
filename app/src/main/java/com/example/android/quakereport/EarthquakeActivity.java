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

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String EARTHQUAKES_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=1999-01-01&endtime=NOW&%20maxradiuskm=300.0&minmagnitude=5.5&latitude=39.89&longitude=23.72";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


        /*
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        */

        //QuakeAdapter adapter = new QuakeAdapter(this, earthquakes);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.

        //ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.

        //listView.setAdapter(adapter);

        // Kick off an {@link AsyncTask} to perform the network request
        //LeshiAsyncTask task = new LeshiAsyncTask();

        //EarthquakeAsyncTask task = new EarthquakeAsyncTask(EarthquakeActivity.this);

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute();

        //new myClass(YourActivity.this, "Hello!", 123).execute();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateUi(final ArrayList<Earthquake> earthquakes) {


        QuakeAdapter adapter = new QuakeAdapter(this, earthquakes);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        /*
        // ListView on item selected listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                Intent intent = new Intent(MainActivity.this, DailyFoods.class);

                Day day = days.get(position);

                intent.putExtra("dayObject", day);
                startActivity(intent);

            }
        });

        */
    }


    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        //private ProgressDialog dialog;




        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            //int numberOfWeek = findWeek();

            //ArrayList<Earthquake> earthquakes = Utils.fetchDaysData(EARTHQUAKES_REQUEST_URL);

            ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakes(EARTHQUAKES_REQUEST_URL);

            //days = Utils.fetchDaysData(LESHI_REQUEST_URL);

            //Day day = Utils.fetchDayData(LESHI_REQUEST_URL);



            /*
            // Create URL object
            URL url = createUrl(USGS_REQUEST_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            Event earthquake = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return earthquake;

            */

            return earthquakes;
        }

        /*
        @Override
        protected void onPreExecute() {
            dialog.setMessage("Κάτι ψήνεται..\n Υπομονή !");
            dialog.setIndeterminate(true);
            dialog.show();
        }
        */

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            if (earthquakes == null) {
                return;
            }

            updateUi(earthquakes);
            /*
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            */
        }




    }


}
