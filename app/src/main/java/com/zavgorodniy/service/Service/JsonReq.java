package com.zavgorodniy.service.Service;

/**
 * Created by Lego on 02.03.2016.
 */

import android.os.AsyncTask;
import android.util.Log;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsonReq extends AsyncTask<Integer, Integer, String> {

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = "";
    Controller controller;

    public static String LOG_TAG = "my_log";


    @Override
    protected String doInBackground(Integer... params) {
        try {
            URL url = new URL("http://api.themoviedb.org/3/genre/"+params[0]+"/movies?api_key=ae55d48a6fab6cd75142c455dda352ab&language=ru");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);

        controller = Controller.getInstance();

        JSONObject dataJsonObj;

        try {
            dataJsonObj = new JSONObject(strJson);
            JSONArray movies = dataJsonObj.getJSONArray("results");

            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);

                String name = movie.getString("original_title");
                Log.d(LOG_TAG, "name: " + name);
                String genre = movie.getString("genre_ids");
                Log.d(LOG_TAG, "genre: " + genre);
                String date = movie.getString("release_date");
                Log.d(LOG_TAG, "date: " + date);
                String rating = movie.getString("vote_average");
                Log.d(LOG_TAG, "rating: " + rating);
                String description = movie.getString("overview");
                Log.d(LOG_TAG, "description: " + description);
                String imageId = movie.getString("poster_path");
                Log.d(LOG_TAG, "imageId: " + imageId);

                Item item =new Item(name,genre,date,rating,description,imageId);
                controller.setItems(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... result) {
        // [... Сообщите о результате через обновление пользовательского
        // интерфейса, диалоговое окно или уведомление ...]
    }
}

