package com.goodcat.popularmusicapp.core.bridge;

import com.goodcat.popularmusicapp.core.callback.NetCallback;

public interface NetBridge {

    void getArtists(String country, int page, NetCallback callback);

}
