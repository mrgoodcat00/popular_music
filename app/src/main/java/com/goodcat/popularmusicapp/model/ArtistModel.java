package com.goodcat.popularmusicapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArtistModel {

    public static final int IMAGE_SIZE_SMALL = 0;
    public static final int IMAGE_SIZE_MEDIUM = 1;
    public static final int IMAGE_SIZE_LARGE = 2;
    public static final int IMAGE_SIZE_ESTRALARGE = 3;
    public static final int IMAGE_SIZE_MEGA = 4;

    @SerializedName("listeners")
    @Expose
    private long listeners;

    @SerializedName("name")
    @Expose
    private String name;

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
    private List<Map<String, String>> image;

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

    public List<Map<String, String>> getImage() {
        return image;

    }

    public String getImageUrl(int size){
        Map<String, String> map = getImage().get(size);

        String url = null;

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()){

            Map.Entry pair = iterator.next();
            if(pair.getKey().equals("#text")) {
                url = (String) pair.getValue();
                break;
            }
        }

        return url;
    }
}
