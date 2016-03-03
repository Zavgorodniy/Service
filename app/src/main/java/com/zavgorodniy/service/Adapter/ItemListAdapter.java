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

public class ItemListAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> mListItems;
    private Context mContext;

    public ItemListAdapter(Context context, int resource, ArrayList<Item> items) {
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
            convertView = View.inflate(mContext, R.layout.request_item, null);
        }
        @DrawableRes int id = mContext.getResources().getIdentifier(mListItems.get(position).getImageId(), "drawable", mContext.getPackageName());

        TextView mText = (TextView) convertView.findViewById(R.id.tv_request_name);
        mText.setText(mListItems.get(position).getName());
        ImageView mImage = (ImageView) convertView.findViewById(R.id.iv_request_image);
        mImage.setImageDrawable(mContext.getResources().getDrawable(id));

        return convertView;
    }
}
