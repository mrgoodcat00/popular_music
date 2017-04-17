package com.goodcat.popularmusicapp.core.bridge;

import com.goodcat.popularmusicapp.core.callback.NetCallback;

public interface NetBridge {

    void getArtists(String country, int page, NetCallback callback);
    void getAlbums(String artist, String uid, NetCallback callback);
    void getArtistInfo(String name, NetCallback callback);
}
