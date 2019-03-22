package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QuakeAdapter extends ArrayAdapter<Earthquake> {

    private String pattern = "dd/mm/yyyy";

    DateFormat df = new SimpleDateFormat(pattern);

    //String formattedDate = myLocalDate.toString("MM/dd/yyyy");

    public QuakeAdapter(Activity context, ArrayList<Earthquake> quakes) {
        super(context, 0, quakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }



        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentQuake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_textview);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        magnitudeTextView.setText(Double.toString(currentQuake.getMagnitude()));

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView epicentreTextView = (TextView) listItemView.findViewById(R.id.epicentre_textview);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        epicentreTextView.setText(currentQuake.getEpicentre());


        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_textview);

        //String quakeDateAsString = df.format(currentQuake.getDateOfQuake());

        //dateTextView.setText(quakeDateAsString);

        //dateTextView.setText(currentQuake.getDateOfQuake().toString("dd/MM/yyyy"));
        dateTextView.setText(currentQuake.getDateOfQuake().toString("MMM d, yyyy"));

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;

        //return super.getView(position, convertView, parent);

    }
}
