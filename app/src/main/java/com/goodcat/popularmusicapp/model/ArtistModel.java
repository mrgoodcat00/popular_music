package com.goodcat.popularmusicapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class ArtistModel extends RealmObject {
    @Ignore
    public static final int IMAGE_SIZE_SMALL = 0;
    @Ignore
    public static final int IMAGE_SIZE_MEDIUM = 1;
    @Ignore
    public static final int IMAGE_SIZE_LARGE = 2;
    @Ignore
    public static final int IMAGE_SIZE_ESTRALARGE = 3;
    @Ignore
    public static final int IMAGE_SIZE_MEGA = 4;

    @SerializedName("listeners")
    @Expose
    private long listeners;

    @SerializedName("name")
    @Expose
    private String name;

    @PrimaryKey
    @SerializedName("mbid")
    @Expose
    private String mbid;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("streamable")
    @Expose
    private String streamable;

    @SerializedName("image")
    @Expose
    private RealmList<ImageSizes> image;

    private String country;

    public String getName() {
        return name;
    }

    public long getListeners() {
        return listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

    public String getStreamable() {
        return streamable;
    }

    public String getImageUrl(int size){
        return image.get(size).getUrl();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

