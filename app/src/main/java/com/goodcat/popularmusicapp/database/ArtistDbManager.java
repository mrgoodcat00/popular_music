package com.goodcat.popularmusicapp.database;

import android.content.Context;
import android.os.Handler;

import com.goodcat.popularmusicapp.model.AlbumModel;
import com.goodcat.popularmusicapp.model.ArtistModel;
import com.goodcat.popularmusicapp.model.CountryModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;

public class ArtistDbManager {

    private Context context;
    private Realm realm;
    private Handler mainHandler;

    public ArtistDbManager(Context context) {
        this.context = context;
        this.mainHandler = new Handler(context.getMainLooper());

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        this.realm = Realm.getDefaultInstance();
    }

    public List<ArtistModel> getArtistsByCountry(String country){
        return realm.where(ArtistModel.class).equalTo("country",country).findAll();
    }

    public CountryModel getCountry(String country){
        return realm.where(CountryModel.class).equalTo("isoName",country).findFirst();
    }

    public ArtistModel getArtist(String uid){
        return realm.where(ArtistModel.class).equalTo("mbid",uid).findFirst();
    }

    public void saveArtists(RealmList<ArtistModel> artists, String country){

        CountryModel countryModel = new CountryModel();
        countryModel.setIsoName(country);
        countryModel.setArtists(artists);

        saveObject(countryModel);

    }

    public List<AlbumModel> getAlbums(String artistUid){
        return realm.where(AlbumModel.class).equalTo("artistUidi",artistUid).findAll();
    }

    public void saveAlbums(RealmList<AlbumModel> list, String artistUid){
        for(int i=0;i<list.size();i++){
            list.get(i).setArtistUidi(artistUid);
            saveObject(list.get(i));
        }
    }

    private void saveObject(final RealmObject realmObject) {
        Runnable savingRunnable = new Runnable() {
            @Override
            public void run() {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(realmObject);
                realm.commitTransaction();
                //realm.close();
            }
        };
        mainHandler.post(savingRunnable);
    }
}
