package com.zavgorodniy.service.Adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zavgorodniy.service.R;

import java.util.ArrayList;

public class GenresAdapter extends ArrayAdapter<String> {

    private ArrayList<String> mListNames;
    private ArrayList<String> mListImages;
    private Context mContext;

    public GenresAdapter(Context context, int resource, ArrayList<String> names, ArrayList<String> images) {
        super(context, resource, names);
        mListNames = names;
        mListImages = images;
        mContext = context;
    }


    @Override
    public String getItem(int position) {
        return String.valueOf(position);
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.genre, null);
        }
        @DrawableRes int id = mContext.getResources().getIdentifier(mListImages.get(position), "drawable", mContext.getPackageName());

        TextView mText = (TextView) convertView.findViewById(R.id.tv_genre_name);
        mText.setText(mListNames.get(position));
        ImageView mImage = (ImageView) convertView.findViewById(R.id.iv_genre_image);
        mImage.setImageDrawable(mContext.getResources().getDrawable(id));

        return convertView;
    }
}
