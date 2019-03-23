package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.LocalDateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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

        //magnitudeTextView.setText(Double.toString(currentQuake.getMagnitude()));
        magnitudeTextView.setText(formatMagnitude(currentQuake.getMagnitude()));

//        // Set the proper background color on the magnitude circle.
//        // Fetch the background from the TextView, which is a GradientDrawable.
//        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
//        // Get the appropriate background color based on the current earthquake magnitude
//        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
//        // Set the color on the magnitude circle
//        magnitudeCircle.setColor(magnitudeColor);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);





        // Find the TextView in the list_item.xml layout with the ID version_number

        // TextView epicentreTextView = (TextView) listItemView.findViewById(R.id.epicentre_textview);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView

        String wholeLocation = currentQuake.getEpicentre();

        String primaryLocation = getPrimaryLocation(wholeLocation);
        String locationOffset = getOffsetLocation(wholeLocation);

        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation);

        TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetTextView.setText(locationOffset);

        //epicentreTextView.setText(currentQuake.getEpicentre());


        //TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_textview);

        //String quakeDateAsString = df.format(currentQuake.getDateOfQuake());

        //dateTextView.setText(quakeDateAsString);



        LocalDateTime dateObject = new LocalDateTime(currentQuake.getDateOfQuake());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = dateObject.toString("MMM d, yyyy");
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        // String formattedTime = formatTime(dateObject);



        //String formattedTime = dateObject.toString("HH:mm", new Locale("GR"));


        String formattedTime = dateObject.toString("HH:mm");

        Log.i("Formatting time", "at position " + position+ " time is "+formattedTime);


        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        //dateTextView.setText(currentQuake.getDateOfQuake().toString("MMM d, yyyy"));

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;

        //return super.getView(position, convertView, parent);

    }



    // getMagnitudeColor(currentEarthquake.getMagnitude());

    private int getMagnitudeColor(double magnitude) {

        // so that 6.0 to 6.9 will become 6.
        int baseMagnitude = (int) Math.floor(magnitude);

        int color;

        switch(baseMagnitude) {
            case 0:
                color = R.color.magnitude1;
                break;
            case 1:
                color = R.color.magnitude1;
                break;
            case 2:
                color = R.color.magnitude2;
                break;
            case 3:
                color = R.color.magnitude3;
                break;
            case 4:
                color = R.color.magnitude4;
                break;
            case 5:
                color = R.color.magnitude5;
                break;
            case 6:
                color = R.color.magnitude1;
                break;
            case 7:
                color = R.color.magnitude1;
                break;
            case 8:
                color = R.color.magnitude2;
                break;
            case 9:
                color = R.color.magnitude9;
                break;
            case 10:
                color = R.color.magnitude10plus;
                break;
            default:
                color = R.color.magnitude10plus;
                break;

        }



        return ContextCompat.getColor(getContext(), color);


    }

    // 215km SW of Tomatlan, Mexico
    /*
    contains(CharSequence cs) - Returns true or false depending on whether or not the input CharSequence (or input String) is contained within the original String

    indexOf(String string) - Returns the index of where the input String first appears in the original String, or returns -1 if there input String is not found in the original String

    split(String string) - Returns an array of String parts by splitting the original String at the locations specified in the input String.

    substring(int start, int end) - Returns a new String that starts at the start index and goes up to (but doesn’t include) the end index.
     */

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private boolean hasOffsetDetails(String wholeLocation) {
        //Character.isDigit(myString.charAt(0));

        return Character.isDigit(wholeLocation.charAt(0));
    }

    /**
     *  TODO refactor this and offset location to something better ...
     *
     */
    private String getPrimaryLocation(String wholeLocation) {

        String[] words = wholeLocation.split("\\s");

        String primaryLocation = "";

        if (hasOffsetDetails(wholeLocation)) {

            for (int i = 3; i < words.length; i++) {
                primaryLocation += (words[i] + " ");
            }
        }

        else {
            primaryLocation = wholeLocation;
        }

        return primaryLocation;
    }

    private String getOffsetLocation(String wholeLocation) {

        if (hasOffsetDetails(wholeLocation)) {
            /*
            from https://stackoverflow.com/questions/4674850/converting-a-sentence-string-to-a-string-array-of-words-in-java

            String s = "This is a sample sentence.";
            String[] words = s.split("\\s+");
            */

            // IT SEEMS THAT OFFSET IS ALWAYS DESCRIBED IN EXACTLY THE FIRST THREE WORDS - CHECK MORE JSON RESULTS TO BE CERTAIN
            String[] words = wholeLocation.split("\\s");
            return words[0] + " " + words[1] + " " + words[2];
        }
        else {
            return "Near the";
        }
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     *
     * NOT USED
     */
    private String formatDate(LocalDate dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     *
     * NOT USED
     */
    private String formatTime(LocalDate dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
