package com.zavgorodniy.service.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        new DownloadImageTask((ImageView) findViewById(R.id.iv_image)).execute("http://image.tmdb.org/t/p/w300/" + getIntent().getStringExtra("imageId"));

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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
