package com.example.dezhi.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class RatingResults implements Serializable, Comparable {

    private String name;
    private float rating;

    public RatingResults(String name, float rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "name: " + name + "   Rating: " + rating + "\n";
    }




    @Override
    public int compareTo(@NonNull Object o) {
        RatingResults otherObject = (RatingResults) o;
        return (this.rating-otherObject.getRating())>0?1:-1;
    }
}
