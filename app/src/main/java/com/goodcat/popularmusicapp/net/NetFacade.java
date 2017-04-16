package com.goodcat.popularmusicapp.net;

import android.content.Context;

import com.goodcat.popularmusicapp.core.bridge.NetBridge;
import com.goodcat.popularmusicapp.core.callback.NetCallback;
import com.goodcat.popularmusicapp.net.retrofit.API;
import com.goodcat.popularmusicapp.net.retrofit.RetrofitHelper;

public class NetFacade implements NetBridge{

    private Context context;
    private ArtistManager artistManager;

    public NetFacade(Context context) {
        API api = new RetrofitHelper().getApi();

        this.context = context;
        this.artistManager = new ArtistManager(api);
    }


    @Override
    public void getArtists(String country, int page, NetCallback callback) {
        artistManager.getArtist(country,page, callback);
    }
}
