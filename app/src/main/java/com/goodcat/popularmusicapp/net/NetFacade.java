package com.goodcat.popularmusicapp.net;

import android.content.Context;

import com.goodcat.popularmusicapp.core.bridge.DbBridge;
import com.goodcat.popularmusicapp.core.bridge.NetBridge;
import com.goodcat.popularmusicapp.core.callback.NetCallback;
import com.goodcat.popularmusicapp.net.retrofit.API;
import com.goodcat.popularmusicapp.net.retrofit.RetrofitHelper;

public class NetFacade implements NetBridge{

    private Context context;
    private ArtistManager artistManager;
    private DbBridge dbBridge;

    public NetFacade(Context context, DbBridge dbBridge) {
        API api = new RetrofitHelper().getApi();

        this.context = context;
        this.artistManager = new ArtistManager(api,dbBridge);
        this.dbBridge = dbBridge;
    }


    @Override
    public void getArtists(String country, int page, NetCallback callback) {
        artistManager.getArtist(country,page, callback);
    }

    @Override
    public void getAlbums(String artist, String uid, NetCallback callback) {
        artistManager.getArtistAlbums(artist, uid, callback);
    }

    @Override
    public void getArtistInfo(String name, NetCallback callback) {
        artistManager.getArtistInfo(name,callback);
    }


}
