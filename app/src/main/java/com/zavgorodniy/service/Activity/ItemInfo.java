package com.zavgorodniy.service.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.zavgorodniy.service.R;

public class ItemInfo extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);
    }
}
