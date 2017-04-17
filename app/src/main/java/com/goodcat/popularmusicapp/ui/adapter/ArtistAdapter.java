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
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ArtistModel model = getItem(i);

        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.artist_list_item_layout,viewGroup,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(model.getImageUrl(ArtistModel.IMAGE_SIZE_LARGE)).placeholder(R.mipmap.ic_launcher_round).into(holder.artistAvatar);

        holder.artistName.setText(model.getName());

        holder.listeners.setText("("+String.valueOf(model.getListeners())+" "+context.getString(R.string.listeners)+")");

        return convertView;
    }

    public void setArtists(List<ArtistModel> list){
        artists.clear();
        artists.addAll(list);
        notifyDataSetChanged();
    }

    private class ViewHolder{

        private ImageView artistAvatar;
        private TextView artistName;
        private TextView listeners;

        public ViewHolder(View view) {
            this.artistAvatar = (ImageView) view.findViewById(R.id.main_screen_artist_avatar);
            this.artistName = (TextView) view.findViewById(R.id.main_screen_artist_name);
            this.listeners = (TextView) view.findViewById(R.id.main_screen_artist_listeners_count);
        }
    }
}
