package com.example.a16022774.c302_photostoreclient_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {
    Context context;
    ArrayList<Category> categories;
    int resource;

    public CategoryAdapter(Context context, int resource, ArrayList<Category> categories) {
        super(context, resource, categories);
        this.context = context;
        this.categories = categories;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView tvCat = (TextView) rowView.findViewById(R.id.tvCategory);
        TextView tvDesc = (TextView) rowView.findViewById(R.id.tvDescription);

        Category category = categories.get(position);
        tvCat.setText(category.getName());
        tvDesc.setText(category.getDescription());

        return rowView;
    }
}
