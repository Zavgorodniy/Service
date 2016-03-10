package com.zavgorodniy.service.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zavgorodniy.service.R;
import com.zavgorodniy.service.Service.Item;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ItemListAdapter extends ArrayAdapter<Item> {

    private List<Item> mListItems;
    private Context mContext;

    public ItemListAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        mListItems = items;
        mContext = context;
    }

    @Override
    public Item getItem(int position) {
        return mListItems.get(position);
    }

    @Override
    public int getPosition(Item item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = View.inflate(mContext, R.layout.item, null);

        Item item = mListItems.get(position);

        TextView mText = (TextView) convertView.findViewById(R.id.tv_item_name);
        mText.setText(item.getName());

        TextView mTextRating = (TextView) convertView.findViewById(R.id.tv_item_rating);
        mTextRating.setText(item.getRating());

        return convertView;
    }
}
