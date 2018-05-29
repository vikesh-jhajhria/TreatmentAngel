package com.treatmentangel.model;

public class FavouriteModel {
    public String getUser_home_phone_no() {
        return user_home_phone_no;
    }

    public void setUser_home_phone_no(String user_home_phone_no) {
        this.user_home_phone_no = user_home_phone_no;
    }

    public String getUser_cell_phone_no() {
        return user_cell_phone_no;
    }

    public void setUser_cell_phone_no(String user_cell_phone_no) {
        this.user_cell_phone_no = user_cell_phone_no;
    }

    public String getUser_screen_name() {
        return user_screen_name;
    }

    public void setUser_screen_name(String user_screen_name) {
        this.user_screen_name = user_screen_name;
    }

    public String getUser_address_street1() {
        return user_address_street1;
    }

    public void setUser_address_street1(String user_address_street1) {
        this.user_address_street1 = user_address_street1;
    }

    public String getUser_address_city() {
        return user_address_city;
    }

    public void setUser_address_city(String user_address_city) {
        this.user_address_city = user_address_city;
    }

    public String getCrdt_date_time() {
        return crdt_date_time;
    }

    public void setCrdt_date_time(String crdt_date_time) {
        this.crdt_date_time = crdt_date_time;
    }

    public String getFav_to_user_id() {
        return fav_to_user_id;
    }

    public void setFav_to_user_id(String fav_to_user_id) {
        this.fav_to_user_id = fav_to_user_id;
    }

    private String user_home_phone_no;
    private String user_cell_phone_no;
    private String user_screen_name;
    private String user_address_street1;
    private String user_address_city;
    private String crdt_date_time;
    private String fav_to_user_id;
}
