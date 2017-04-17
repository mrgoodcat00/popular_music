package com.goodcat.popularmusicapp.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CountryModel extends RealmObject{

    private String country;

    @PrimaryKey
    private String isoName;

    private RealmList<ArtistModel> artists;

    public String getCountry() {
        return country;
    }

    public String getIsoName() {
        return isoName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public List<ArtistModel> getArtists() {
        return artists;
    }

    public void setArtists(RealmList<ArtistModel> artists) {
        this.artists = artists;
    }
}
