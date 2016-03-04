package com.zavgorodniy.service.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zavgorodniy.service.R;

import java.io.InputStream;
import java.net.URL;

public class ItemInfo extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        Button back = (Button)findViewById(R.id.bt_back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });

//        ImageView mImage = (ImageView) findViewById(R.id.iv_item_image);
//        mImage.setImageDrawable(loadImage(getIntent().getStringExtra("imageId")));
        TextView description = (TextView) findViewById(R.id.tv_description);
        description.setText(getIntent().getStringExtra("description"));

        TextView name = (TextView) findViewById(R.id.tv_name);
        name.setText(getIntent().getStringExtra("name"));

        TextView rating = (TextView) findViewById(R.id.tv_rating);
        rating.setText("Рейтинг: " + getIntent().getStringExtra("rating"));

        TextView genre = (TextView) findViewById(R.id.tv_genre);
        genre.setText("Жанр: " + getIntent().getStringExtra("genre"));

        TextView date = (TextView) findViewById(R.id.tv_date);
        date.setText("Дата: " + getIntent().getStringExtra("date"));
    }

    private Drawable loadImage(String src) {
        try {
            InputStream is = (InputStream) new URL("http://image.tmdb.org/t/p/w300/" + src).getContent();
            return Drawable.createFromStream(is, src);
        } catch (Exception e) {
            return null;
        }
    }
}
