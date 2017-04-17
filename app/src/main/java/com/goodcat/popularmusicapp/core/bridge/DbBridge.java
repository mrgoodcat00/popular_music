package com.goodcat.popularmusicapp.core.bridge;

import com.goodcat.popularmusicapp.model.AlbumModel;
import com.goodcat.popularmusicapp.model.ArtistModel;
import com.goodcat.popularmusicapp.model.CountryModel;

import java.util.List;
import java.util.Observer;

import io.realm.RealmList;

public interface DbBridge {

    String PRELOAD = "preload data";
    String ALL_ARTISTS_SAVED = "artists saved";
    String ALL_ALBUMS_SAVED = "albums saved";

    List<ArtistModel> getArtistsByCountry(String country);
    void saveArtists(RealmList<ArtistModel> artistList, String country);
    CountryModel getCountry(String countryName);
    ArtistModel getArtist(String uId);
    void saveAlbums(RealmList<AlbumModel> list, String uid);
    List<AlbumModel> getAlbums(String uid);


    void notifyPreload();
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
}
