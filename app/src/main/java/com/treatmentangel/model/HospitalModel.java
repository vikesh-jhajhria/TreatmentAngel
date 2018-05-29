package com.treatmentangel.model;

/**
 * Created by Vikesh on 22-Jan-18.
 */

public class HospitalModel {
    private String id;
    private String name;
    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
