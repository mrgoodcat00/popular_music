package com.goodcat.popularmusicapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AlbumModel extends RealmObject{

    @PrimaryKey
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("playcount")
    @Expose
    private int playcount;

    @SerializedName("mbid")
    @Expose
    private String mbid;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("artist")
    @Expose
    private ArtistModel artist;

    @SerializedName("image")
    @Expose
    private RealmList<ImageSizes> image;

    private String artistUidi;

    public String getArtistUidi() {
        return artistUidi;
    }

    public void setArtistUidi(String artistUidi) {
        this.artistUidi = artistUidi;
    }

    public String getName() {
        return name;
    }

    public int getPlaycount() {
        return playcount;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

    public ArtistModel getArtist() {
        return artist;
    }

    public RealmList<ImageSizes> getImage() {
        return image;
    }
}
