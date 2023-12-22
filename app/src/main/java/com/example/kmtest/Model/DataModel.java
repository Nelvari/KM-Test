package com.example.kmtest.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {

    @SerializedName("data")
    List<ListModel> response;

    public List<ListModel> getResponse() {
        return response;
    }

}
