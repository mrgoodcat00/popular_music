package com.goodcat.popularmusicapp.net.response;

import com.goodcat.popularmusicapp.model.ArtistModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

public class TopArtistsResponse {

    @SerializedName("artist")
    @Expose
    private RealmList<ArtistModel> artist;

    @SerializedName("@attr")
    @Expose
    private Attributes attr;

    public RealmList<ArtistModel> getArtist() {
        return artist;
    }

    public Attributes getAttr() {
        return attr;
    }

    public class Attributes{

        @SerializedName("page")
        @Expose
        private int page;

        @SerializedName("perPage")
        @Expose
        private int perPage;

        @SerializedName("totalPages")
        @Expose
        private int totalPages;

        @SerializedName("total")
        @Expose
        private int total;

        @SerializedName("country")
        @Expose
        private String country;

        public int getPage() {
            return page;
        }

        public int getPerPage() {
            return perPage;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getTotal() {
            return total;
        }

        public String getCountry() {
            return country;
        }
    }
}
