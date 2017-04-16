package com.goodcat.popularmusicapp.ui.activity;

import com.goodcat.popularmusicapp.core.PopularMusicApp;
import com.goodcat.popularmusicapp.core.bridge.DbBridge;
import com.goodcat.popularmusicapp.core.bridge.NetBridge;

public interface ActivityBridge {

    PopularMusicApp getPopularApp();
    NetBridge getNetBridge();
    DbBridge getDbBridge();
}
