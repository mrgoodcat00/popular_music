package com.goodcat.popularmusicapp.database;

import android.content.Context;

import com.goodcat.popularmusicapp.core.bridge.DbBridge;

public class DbFacade implements DbBridge{

    private Context context;

    public DbFacade(Context context) {
        this.context = context;
    }


}
