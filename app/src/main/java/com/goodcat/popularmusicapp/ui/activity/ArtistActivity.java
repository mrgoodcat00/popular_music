package com.goodcat.popularmusicapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.goodcat.popularmusicapp.R;
import com.goodcat.popularmusicapp.core.bridge.DbBridge;
import com.goodcat.popularmusicapp.core.callback.SimpleCallback;
import com.goodcat.popularmusicapp.model.AlbumModel;
import com.goodcat.popularmusicapp.model.ArtistModel;
import com.goodcat.popularmusicapp.net.response.ArtistInfoResponse;
import com.goodcat.popularmusicapp.ui.adapter.AlbumAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ArtistActivity extends BaseActivity {

    private static String artistName;
    private static String artistId;

    private Observer observer;
    private AlbumAdapter adapter;
    private ImageView artistAvatar;
    private ListView albumsList;

    public static void start(Context context, String artName, String artId) {
        artistName = artName;
        artistId = artId;
        context.startActivity(new Intent(context, ArtistActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_activity);

        initViews();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        showProgressDialog();
        getNetBridge().getAlbums(artistName, artistId, new NetArtistsListener());
        getNetBridge().getArtistInfo(artistName, new NetArtistsListener());
    }

    private void initViews() {
        albumsList = (ListView) findViewById(R.id.artist_screen_top_album);
        artistAvatar = (ImageView) findViewById(R.id.artist_screen_artist_avatar);
        adapter = new AlbumAdapter(this, new ArrayList<AlbumModel>());
        albumsList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(observer ==null){
            observer = new DbObserver();
            getDbBridge().addObserver(observer);
        }

    }

    private void onDbUpdated(String o) {
        switch (o){
            case DbBridge.ALL_ALBUMS_SAVED:
                updateFromDb();
                break;
        }
    }

    private void updateFromDb() {
        List<AlbumModel> albumsList = getDbBridge().getAlbums(artistId);
        adapter.setAlbums(albumsList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(observer !=null){
            getDbBridge().deleteObserver(observer);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class NetArtistsListener extends SimpleCallback {
        @Override
        public void onSuccess() {
            super.onSuccess();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgressDialog();

                    if(getData() instanceof List) {
                        List<AlbumModel> data = getData();

                        adapter.setAlbums(data);
                    }

                    if(getData() instanceof ArtistInfoResponse){
                        ArtistInfoResponse response = getData();
                        Picasso.with(ArtistActivity.this)
                                .load(response.getImage()
                                .get(ArtistModel.IMAGE_SIZE_MEGA)
                                .getUrl()).placeholder(R.mipmap.ic_launcher)
                                .into(artistAvatar);
                    }

                }
            });

        }

        @Override
        public void onError() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgressDialog();
                    Toast.makeText(ArtistActivity.this, "Can't get artists, try request later", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private final class DbObserver implements Observer {

        @Override
        public void update(Observable observable, final Object o) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    onDbUpdated((String) o);
                }
            });
        }
    }
}
