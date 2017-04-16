package com.goodcat.popularmusicapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.goodcat.popularmusicapp.R;
import com.goodcat.popularmusicapp.core.callback.SimpleCallback;
import com.goodcat.popularmusicapp.model.ArtistModel;
import com.goodcat.popularmusicapp.net.response.TopArtistsResponse;
import com.goodcat.popularmusicapp.ui.adapter.ArtistAdapter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ListView artistsList;
    private Spinner countriesIdSpinner;
    private ArtistAdapter artistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

    }

    private void bindViews(){
        artistsList = (ListView) findViewById(R.id.main_screen_popular_artists);
        artistAdapter = new ArtistAdapter(new ArrayList<ArtistModel>(),this);
        artistsList.setAdapter(artistAdapter);

        countriesIdSpinner = (Spinner) findViewById(R.id.main_screen_county_spinner);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countries_ids));
        countriesIdSpinner.setAdapter(spinnerCountShoesArrayAdapter);
        countriesIdSpinner.setOnItemSelectedListener(new SpinnerListener());
    }


    private class NetArtistsListener extends SimpleCallback{
        @Override
        public void onSuccess() {
            super.onSuccess();

            final TopArtistsResponse topArtists = getData();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    artistAdapter.setArtists(topArtists.getArtist());
                }
            });


        }

        @Override
        public void onError() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
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

            String selectedItem = (String) textView.getText();

            getNetBridge().getArtists(selectedItem, 1, new NetArtistsListener());

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
