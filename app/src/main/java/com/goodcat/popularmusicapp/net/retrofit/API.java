package com.goodcat.popularmusicapp.net.retrofit;

import com.goodcat.popularmusicapp.net.response.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("?method=geo.gettopartists&format=json")
    Call<ServerResponse> getArtistsByCountry( @Query("country") String country, @Query("page") int page);

    @GET("?method=artist.gettopalbums&format=json")
    Call<ServerResponse> getAlbumsByArtist( @Query("artist") String artist,@Query("mbid") String uid);

    @GET("?method=artist.getinfo&format=json")
    Call<ServerResponse> getArtistInfo( @Query("artist") String artist);

}
