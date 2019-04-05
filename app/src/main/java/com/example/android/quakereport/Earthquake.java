package com.example.android.quakereport;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Date;

public class Earthquake {

    private double magnitude;

    private String epicentre;

    //private Date dateOfQuake;
    //private LocalDateTime dateOfQuake;
    private DateTime dateOfQuake;

    /** Website URL of the earthquake */
    private String url;

    public Earthquake(double magnitude, String epicentre, DateTime dateOfQuake, String url) {
        this.magnitude = magnitude;
        this.epicentre = epicentre;
        this.dateOfQuake = dateOfQuake;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getEpicentre() {
        return epicentre;
    }

    public void setEpicentre(String epicentre) {
        this.epicentre = epicentre;
    }

    public DateTime getDateOfQuake() {
        return dateOfQuake;
    }

    public void setDateOfQuake(DateTime dateOfQuake) {
        this.dateOfQuake = dateOfQuake;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
