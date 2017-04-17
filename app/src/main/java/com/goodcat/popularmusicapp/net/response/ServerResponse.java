package com.goodcat.popularmusicapp.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("topartists")
    @Expose
    private TopArtistsResponse topartists;

    @SerializedName("topalbums")
    @Expose
    private TopAlbumsResponse topalbums;

    @SerializedName("artist")
    @Expose
    private ArtistInfoResponse artist;

    public ArtistInfoResponse getArtist() {
        return artist;
    }

    public TopArtistsResponse getTopartists() {
        return topartists;
    }

    public TopAlbumsResponse getTopAlbums() {
        return topalbums;
    }
}
