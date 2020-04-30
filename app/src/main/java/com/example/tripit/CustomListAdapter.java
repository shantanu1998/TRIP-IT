package com.example.tripit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomListAdapter extends ArrayAdapter<HomeStay_Photos> {

     ArrayList<HomeStay_Photos> homeStay_photos;
    Context context;
    int resource;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HomeStay_Photos> homeStay_photos) {

        super(context, resource, homeStay_photos);
        this.homeStay_photos= homeStay_photos;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            assert layoutInflater != null;
            convertView=layoutInflater.inflate(R.layout.custom_list_layout ,null,true);


        }
        HomeStay_Photos homeStay_photos=getItem(position);

        ImageView imageView=(ImageView) convertView.findViewById(R.id.imgview);
        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(homeStay_photos.getImage()).fit().into(imageView);



        TextView txtname = (TextView) convertView.findViewById(R.id.homestay_name);
        txtname.setText(homeStay_photos.getName());

        TextView txtID = (TextView) convertView.findViewById(R.id.location);
        txtID.setText(homeStay_photos.getHSID());


        return convertView;
    }
}
