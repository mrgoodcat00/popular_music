package com.goodcat.popularmusicapp.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("topartists")
    @Expose
    private TopArtistsResponse topartists;

    public TopArtistsResponse getTopartists() {
        return topartists;
    }
}
