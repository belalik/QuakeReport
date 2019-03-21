package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {

    private double magnitude;

    private String epicentre;

    private Date dateOfQuake;

    public Earthquake(double magnitude, String epicentre, Date dateOfQuake) {
        this.magnitude = magnitude;
        this.epicentre = epicentre;
        this.dateOfQuake = dateOfQuake;
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

    public Date getDateOfQuake() {
        return dateOfQuake;
    }

    public void setDateOfQuake(Date dateOfQuake) {
        this.dateOfQuake = dateOfQuake;
    }
}
