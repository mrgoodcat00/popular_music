package com.goodcat.popularmusicapp.database;

import android.content.Context;

import com.goodcat.popularmusicapp.core.bridge.DbBridge;
import com.goodcat.popularmusicapp.model.AlbumModel;
import com.goodcat.popularmusicapp.model.ArtistModel;
import com.goodcat.popularmusicapp.model.CountryModel;

import java.util.List;
import java.util.Observable;

import io.realm.RealmList;

public class DbFacade extends Observable implements DbBridge{

    private Context context;
    private ArtistDbManager artistDbManager;

    public DbFacade(Context context) {
        this.context = context;
        this.artistDbManager = new ArtistDbManager(context);
    }


    @Override
    public List<ArtistModel> getArtistsByCountry(String country) {
        return artistDbManager.getArtistsByCountry(country);
    }

    @Override
    public void saveArtists(RealmList<ArtistModel> artistList, String country) {
        artistDbManager.saveArtists(artistList,country);
        setChanged();
        notifyObservers(DbBridge.ALL_ARTISTS_SAVED);
    }

    @Override
    public CountryModel getCountry(String countryName) {
        return artistDbManager.getCountry(countryName);
    }

    @Override
    public ArtistModel getArtist(String uId) {
        return artistDbManager.getArtist(uId);
    }

    @Override
    public void saveAlbums(RealmList<AlbumModel> list,String uid) {
        artistDbManager.saveAlbums(list,uid);
        setChanged();
        notifyObservers(DbBridge.ALL_ALBUMS_SAVED);
    }

    @Override
    public List<AlbumModel> getAlbums(String uid) {
        return artistDbManager.getAlbums(uid);
    }

    @Override
    public void notifyPreload() {
        setChanged();
        notifyObservers(DbBridge.PRELOAD);
    }
}
