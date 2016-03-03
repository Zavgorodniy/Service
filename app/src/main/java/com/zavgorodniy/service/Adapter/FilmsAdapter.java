package com.zavgorodniy.service.Adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zavgorodniy.service.R;
import com.zavgorodniy.service.Service.Item;

import java.util.ArrayList;

public class FilmsAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> mListItems;
    private Context mContext;

    public FilmsAdapter(Context context, int resource, ArrayList<Item> items) {
        super(context, resource, items);
        mListItems = items;
        mContext = context;
    }

    @Override
    public Item getItem(int position) {
        return mListItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.genre, null);
        }
        @DrawableRes int id = mContext.getResources().getIdentifier(mListItems.get(position).imageId, "drawable", mContext.getPackageName());

        TextView mText = (TextView) convertView.findViewById(R.id.tv_genre_name);
        mText.setText(mListItems.get(position).name);
        ImageView mImage = (ImageView) convertView.findViewById(R.id.iv_genre_image);
        mImage.setImageDrawable(mContext.getResources().getDrawable(id));

        return convertView;
    }
}
