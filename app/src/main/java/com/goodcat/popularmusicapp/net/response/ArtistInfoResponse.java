package com.goodcat.popularmusicapp.net.response;

import com.goodcat.popularmusicapp.model.ImageSizes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistInfoResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mbid")
    @Expose
    private String mbid;

    @SerializedName("image")
    @Expose
    private List<ImageSizes> image;

    public String getName() {
        return name;
    }

    public String getMbid() {
        return mbid;
    }

    public List<ImageSizes> getImage() {
        return image;
    }
}
