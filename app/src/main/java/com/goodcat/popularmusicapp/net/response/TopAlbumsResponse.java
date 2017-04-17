package com.goodcat.popularmusicapp.net.response;

import com.goodcat.popularmusicapp.model.AlbumModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

public class TopAlbumsResponse {

    @SerializedName("album")
    @Expose
    private RealmList<AlbumModel> albums;

    public RealmList<AlbumModel> getAlbums() {
        return albums;
    }
}
