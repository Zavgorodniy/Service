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


public class JsonReq extends AsyncTask<Void, Void, String> {

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = "";
    public static String LOG_TAG = "my_log";


    @Override
    protected String doInBackground(Void... params) {
        try {
//            URL url = new URL("http://androiddocs.ru/api/friends.json");
            URL url = new URL("http://api.themoviedb.org/3/movie/top_rated?api_key=ae55d48a6fab6cd75142c455dda352ab");

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

//        try {
//            dataJsonObj = new JSONObject(strJson);
//            JSONArray friends = dataJsonObj.getJSONArray("movie");

            // 1. достаем инфо о втором друге - индекс 1
//            JSONObject secondFriend = friends.getJSONObject(1);
//            secondName = secondFriend.getString("title_russian");
            Log.d(LOG_TAG, "Второе имя: " + strJson);

            // 2. перебираем и выводим контакты каждого друга
//            for (int i = 0; i < friends.length(); i++) {
//                JSONObject friend = friends.getJSONObject(i);
//
//                JSONObject contacts = friend.getJSONObject("contacts");
//
//                String phone = contacts.getString("mobile");
//                String email = contacts.getString("email");
//                String skype = contacts.getString("skype");
//
//                Log.d(LOG_TAG, "phone: " + phone);
//                Log.d(LOG_TAG, "email: " + email);
//                Log.d(LOG_TAG, "skype: " + skype);
//            }

//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }
}

