package com.example.a16022774.c302_photostoreclient_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PhotoAdapter extends ArrayAdapter<Photo> {
    Context context;
    ArrayList<Photo> photos;
    int resource;

    public PhotoAdapter(Context context, int resource, ArrayList<Photo> photos) {
        super(context, resource, photos);
        this.context = context;
        this.photos = photos;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);
        TextView tvCreator = (TextView) rowView.findViewById(R.id.tvCreator);
        TextView tvImage = (TextView) rowView.findViewById(R.id.tvImage);

        Photo photo = photos.get(position);
        tvTitle.setText(photo.getTitle());
        tvDesc.setText(photo.getDescription());
        tvCreator.setText("Created by: " + photo.getCreator());
        tvImage.setText(photo.getImage());

        return rowView;
    }
}
