package com.goodcat.popularmusicapp.net;

import com.goodcat.popularmusicapp.core.bridge.DbBridge;
import com.goodcat.popularmusicapp.core.callback.NetCallback;
import com.goodcat.popularmusicapp.net.response.ServerResponse;
import com.goodcat.popularmusicapp.net.retrofit.API;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class ArtistManager {

    private final API api;
    private DbBridge dbBridge;
    private final ExecutorService executorService;

    public ArtistManager(API api,DbBridge dbBridge) {
        this.api = api;
        this.dbBridge = dbBridge;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void getArtist(final String country, final int page, final NetCallback callback) {

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {

                    Response<ServerResponse> result = api.getArtistsByCountry(country,page).execute();

                    if(result == null){
                        callback.onError();
                        return;
                    }

                    if(result.errorBody() != null){
                        callback.onError();
                        return;
                    }

                    if(result.body().getTopartists().getArtist().size() == 0){
                        callback.onError();
                        return;
                    }

                    dbBridge.saveArtists(result.body().getTopartists().getArtist(),country);

                    callback.onSuccess();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getArtistAlbums(final String artist, final String uid, final NetCallback callback) {

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {

                    Response<ServerResponse> result = api.getAlbumsByArtist(artist,uid).execute();

                    if(result == null){
                        callback.onError();
                        return;
                    }

                    if(result.errorBody() != null){
                        callback.onError();
                        return;
                    }

                    if(result.body().getTopAlbums().getAlbums().size() == 0){
                        callback.onError();
                        return;
                    }

                    //dbBridge.saveAlbums(result.body().getTopAlbums().getAlbums(),uid);

                    callback.putData(result.body().getTopAlbums().getAlbums());
                    callback.onSuccess();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getArtistInfo(final String name, final NetCallback callback){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {

                    Response<ServerResponse> result = api.getArtistInfo(name).execute();

                    if(result == null){
                        callback.onError();
                        return;
                    }

                    if(result.errorBody() != null){
                        callback.onError();
                        return;
                    }

                    callback.putData(result.body().getArtist());
                    callback.onSuccess();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
