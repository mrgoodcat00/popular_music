package com.goodcat.popularmusicapp.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodcat.popularmusicapp.R;
import com.goodcat.popularmusicapp.model.AlbumModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.goodcat.popularmusicapp.model.ArtistModel.IMAGE_SIZE_MEDIUM;

public class AlbumAdapter extends BaseAdapter {

    private List<AlbumModel> albumsList;
    private Context context;

    public AlbumAdapter(Context context, List<AlbumModel> albumsList) {
        this.context = context;
        this.albumsList = albumsList;
    }

    @Override
    public int getCount() {
        return albumsList.size()-1;
    }

    @Override
    public AlbumModel getItem(int i) {
        return albumsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return albumsList.get(i).hashCode();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        AlbumModel model = getItem(i);

        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.album_list_item_layout,viewGroup,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String path = TextUtils.isEmpty(model.getImage().get(IMAGE_SIZE_MEDIUM).getUrl()) ?
                "..." : model.getImage().get(IMAGE_SIZE_MEDIUM).getUrl();
        Picasso.with(context).load(path).placeholder(R.mipmap.ic_launcher_round).into(holder.albumAvatar);

        holder.albumName.setText(model.getName());

        holder.albumListeners.setText("("+String.valueOf(model.getPlaycount())+" "+context.getString(R.string.listeners)+")");

        return convertView;
    }

    public void setAlbums(List<AlbumModel> list){
        albumsList.clear();
        albumsList.addAll(list);
        notifyDataSetChanged();
    }

    private class ViewHolder{

        private ImageView albumAvatar;
        private TextView albumName;
        private TextView albumListeners;

        public ViewHolder(View view) {
            this.albumAvatar = (ImageView) view.findViewById(R.id.album_avatar);
            this.albumName = (TextView) view.findViewById(R.id.album_name);
            this.albumListeners = (TextView) view.findViewById(R.id.album_listeners);
        }
    }
}
