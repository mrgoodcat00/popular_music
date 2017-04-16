package com.goodcat.popularmusicapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodcat.popularmusicapp.R;
import com.goodcat.popularmusicapp.model.ArtistModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ArtistAdapter  extends BaseAdapter{

    List<ArtistModel> artists = new ArrayList<>();
    Context context;

    public ArtistAdapter(List<ArtistModel> artists, Context context) {
        this.artists = artists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return artists.size();
    }

    @Override
    public ArtistModel getItem(int i) {
        return artists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ArtistModel model = getItem(i);

        View infView = LayoutInflater.from(context).inflate(R.layout.artist_list_item_layout,viewGroup,false);

        ImageView image  = (ImageView) infView.findViewById(R.id.main_screen_artist_avatar);
        Picasso.with(context).load(model.getImageUrl(ArtistModel.IMAGE_SIZE_MEDIUM)).placeholder(R.mipmap.ic_launcher_round).into(image);

        TextView name = (TextView) infView.findViewById(R.id.main_screen_artist_name);
        name.setText(model.getName());

        TextView listeners = (TextView) infView.findViewById(R.id.main_screen_artist_listeners_count);
        listeners.setText("("+String.valueOf(model.getListeners())+" "+context.getString(R.string.listeners)+")");

        return infView;
    }

    public void setArtists(List<ArtistModel> list){
        artists.clear();
        artists.addAll(list);
        notifyDataSetChanged();
    }
}
