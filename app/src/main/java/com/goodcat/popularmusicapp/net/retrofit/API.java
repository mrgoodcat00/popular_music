package com.goodcat.popularmusicapp.net.retrofit;

import com.goodcat.popularmusicapp.net.response.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("?method=geo.gettopartists&format=json")
    Call<ServerResponse> getArtistsByCountry( @Query("country") String country, @Query("page") int page);

}
