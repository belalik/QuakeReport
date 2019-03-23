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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.

        /*
        ArrayList<Earthquake> earthquakes = new ArrayList<>();


        earthquakes.add(new Earthquake(7.0, "San Francisco", new LocalDate(1998, 9, 22)));
        earthquakes.add(new Earthquake(7.0, "London", new LocalDate(2002, 6, 7)));
        earthquakes.add(new Earthquake(7.0, "Moscow", new LocalDate(1998, 12, 12)));
        earthquakes.add(new Earthquake(7.0, "Tokyo", new LocalDate(1976, 10, 25)));

        earthquakes.add(new Earthquake(7.0, "Athens", new LocalDate(2011, 3, 6)));
        earthquakes.add(new Earthquake(7.0, "Paris", new LocalDate(2006, 8, 18)));
        earthquakes.add(new Earthquake(7.0, "Syros", new LocalDate(1993, 6, 27)));
        earthquakes.add(new Earthquake(7.0, "Rio de Janeiro", new LocalDate(1998, 4, 4)));
        */

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

        QuakeAdapter adapter = new QuakeAdapter(this, earthquakes);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

    }
}
