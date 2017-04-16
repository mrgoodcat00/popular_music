package com.goodcat.popularmusicapp.net;

import com.goodcat.popularmusicapp.core.callback.NetCallback;
import com.goodcat.popularmusicapp.net.response.ServerResponse;
import com.goodcat.popularmusicapp.net.retrofit.API;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class ArtistManager {

    private final API api;
    private final ExecutorService executorService;

    public ArtistManager(API api) {
        this.api = api;
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

                    callback.putData(result.body().getTopartists());
                    callback.onSuccess();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
