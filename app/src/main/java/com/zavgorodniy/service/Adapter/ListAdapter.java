package com.zavgorodniy.service.Adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zavgorodniy.service.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {

    private List<String> mList;
    private Context mContext;

    public ListAdapter(Context context, int resource, List<String> list) {
        super(context, resource, list);
        mList = list;
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
            convertView = View.inflate(mContext, R.layout.item, null);
        }
        String name = mList.get(position);
        @DrawableRes int id = mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());

        TextView mText = (TextView) convertView.findViewById(R.id.tv_item);
        mText.setText(name);
//        ImageView mImage = (ImageView) convertView.findViewById(R.id.iv_item);
//        mImage.setImageDrawable(mContext.getResources().getDrawable(id));

        return convertView;
    }
}
