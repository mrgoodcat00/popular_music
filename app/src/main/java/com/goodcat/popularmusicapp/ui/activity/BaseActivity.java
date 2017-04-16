package com.goodcat.popularmusicapp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.goodcat.popularmusicapp.R;
import com.goodcat.popularmusicapp.core.PopularMusicApp;
import com.goodcat.popularmusicapp.core.bridge.DbBridge;
import com.goodcat.popularmusicapp.core.bridge.NetBridge;

public class BaseActivity extends AppCompatActivity implements ActivityBridge{

    private NetBridge netBridge;
    private DbBridge dbBridge;
    private PopularMusicApp application;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (PopularMusicApp)getApplication();
        dbBridge = application.getDbManager();
        netBridge = application.getNetFacade();


    }

    @Override
    public PopularMusicApp getPopularApp() {
        return application;
    }

    @Override
    public NetBridge getNetBridge() {
        return netBridge;
    }

    @Override
    public DbBridge getDbBridge() {
        return dbBridge;
    }

    protected void showProgressDialog(){
        if (progressDialog != null) {
            return;
        }

        progressDialog = ProgressDialog.show(this, "", getString(R.string.dialog_text), true, false);
    }

    protected void hideProgressDialog() {
        if (progressDialog == null) {
            return;
        }

        progressDialog.dismiss();
        progressDialog = null;
    }
}
