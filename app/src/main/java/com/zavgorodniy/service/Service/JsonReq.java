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
        Log.d(LOG_TAG, strJson);

        JSONObject dataJsonObj = null;
        String secondName = "";

        try {
            dataJsonObj = new JSONObject(strJson);
            JSONArray movies = dataJsonObj.getJSONArray("results");

            JSONObject secondFriend = movies.getJSONObject(1);
            secondName = secondFriend.getString("title_russian");
            Log.d(LOG_TAG, "pew pew " + strJson);
            // 2. перебираем и выводим контакты каждого друга
            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);

                JSONObject contacts = movie.getJSONObject("contacts");

                String phone = contacts.getString("mobile");
                String email = contacts.getString("email");
                String skype = contacts.getString("skype");

                Log.d(LOG_TAG, "phone: " + phone);
                Log.d(LOG_TAG, "email: " + email);
                Log.d(LOG_TAG, "skype: " + skype);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


//        {"adult":false,
//                "backdrop_path":"/uS1SkjVviraGfFNgkDwe7ohTm8B.jpg",
//                "genre_ids":[37,18,12,53],
//            "id":281957,
//                "original_language":"en",
//                "original_title":"The Revenant",
//                "overview":"In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.",
//                "release_date":"2015-12-25",
//                "poster_path":"/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg",
//                "popularity":34.220287,
//                "title":"The Revenant",
//                "video":false,
//                "vote_average":7.3,
//                "vote_count":1738}


    }

    @Override
    protected void onProgressUpdate(Integer... result) {
        // [... Сообщите о результате через обновление пользовательского
        // интерфейса, диалоговое окно или уведомление ...]
    }
}

