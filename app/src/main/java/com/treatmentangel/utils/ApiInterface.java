package com.treatmentangel.utils;

import com.treatmentangel.model.SearchModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Vikesh on 06-Mar-18.
 */

public interface ApiInterface {

    //login with email
    @FormUrlEncoded
    @POST("api")
    Call<SearchModel> search(
            @Field("treatment_type") String treatment_type,
            @Field("specialty") String speciality,
            @Field("latitute") double latitude,
            @Field("longitute") double longitude);


}
