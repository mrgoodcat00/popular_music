package com.goodcat.popularmusicapp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.goodcat.popularmusicapp.R;
import com.goodcat.popularmusicapp.core.bridge.DbBridge;
import com.goodcat.popularmusicapp.core.callback.SimpleCallback;
import com.goodcat.popularmusicapp.model.ArtistModel;
import com.goodcat.popularmusicapp.ui.adapter.ArtistAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends BaseActivity {

    private ArtistAdapter artistAdapter;
    private Observer observer;
    private String currentCountry;
    private List<ArtistModel> artists;
    private boolean firstStartFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

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
            case DbBridge.ALL_ARTISTS_SAVED:
                artists = getDbBridge().getCountry(currentCountry).getArtists();
                artistAdapter.setArtists( getDbBridge().getCountry(currentCountry).getArtists());
                break;
        }
    }

    private void bindViews(){
        ListView artistsList = (ListView) findViewById(R.id.main_screen_popular_artists);
        artistAdapter = new ArtistAdapter(new ArrayList<ArtistModel>(),this);
        artistsList.setAdapter(artistAdapter);
        artistsList.setOnItemClickListener(new ItemClickListener());

        Spinner countriesIdSpinner = (Spinner) findViewById(R.id.main_screen_county_spinner);
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this,
                                            android.R.layout.simple_list_item_1,
                                            getResources().getStringArray(R.array.countries_ids));
        countriesIdSpinner.setAdapter(countriesAdapter);
        countriesIdSpinner.setOnItemSelectedListener(new SpinnerListener());
    }

    private void updateArtistsFromInet(){

        if(!isOnline()){
            return;
        }

        showProgressDialog();
        getNetBridge().getArtists(currentCountry, 1, new NetArtistsListener());
    }

    private void updateArtistsFromDb(boolean flag){

        if(getDbBridge().getCountry(currentCountry) == null && flag){
            updateArtistsFromInet();
            return;
        }

        artists = getDbBridge().getCountry(currentCountry).getArtists();
        artistAdapter.setArtists(artists);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(observer !=null){
            getDbBridge().deleteObserver(observer);
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

    private class NetArtistsListener extends SimpleCallback{
        @Override
        public void onSuccess() {
            super.onSuccess();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgressDialog();
                }
            });

        }

        @Override
        public void onError() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgressDialog();
                    Toast.makeText(MainActivity.this, "Can't get artists, try request later", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class SpinnerListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            TextView textView = (TextView) view;

            if (textView == null) {
                return;
            }

            currentCountry = (String) textView.getText();

            if(firstStartFlag){
                updateArtistsFromDb(firstStartFlag);
                firstStartFlag = false;
                return;
            }

            updateArtistsFromInet();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ArtistActivity.start(MainActivity.this, artists.get(i).getName().toLowerCase(), artists.get(i).getMbid());
        }
    }
}
