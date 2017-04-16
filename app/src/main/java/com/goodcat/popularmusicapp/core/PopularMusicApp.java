package com.goodcat.popularmusicapp.core;

import android.app.Application;

import com.goodcat.popularmusicapp.core.bridge.DbBridge;
import com.goodcat.popularmusicapp.core.bridge.NetBridge;
import com.goodcat.popularmusicapp.database.DbFacade;
import com.goodcat.popularmusicapp.net.NetFacade;

public class PopularMusicApp extends Application {

    private NetBridge netBridge;
    private DbBridge dbBridge;

    @Override
    public void onCreate() {
        super.onCreate();
        netBridge = new NetFacade(this);
        dbBridge = new DbFacade(this);
    }

    public NetBridge getNetFacade() {
        return netBridge;
    }

    public DbBridge getDbManager() {
        return dbBridge;
    }
}
