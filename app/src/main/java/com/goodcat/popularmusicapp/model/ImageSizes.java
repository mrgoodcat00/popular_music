package com.goodcat.popularmusicapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class ImageSizes extends RealmObject {

    @SerializedName("#text")
    @Expose
    private String url;

    @SerializedName("size")
    @Expose
    private String size;

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }

}
