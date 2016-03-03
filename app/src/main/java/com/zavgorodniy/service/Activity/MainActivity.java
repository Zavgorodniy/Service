package com.zavgorodniy.service.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.zavgorodniy.service.Adapter.ItemListAdapter;
import com.zavgorodniy.service.Adapter.RequestListAdapter;
import com.zavgorodniy.service.R;
import com.zavgorodniy.service.Service.Controller;
import com.zavgorodniy.service.Service.Item;
import com.zavgorodniy.service.Service.RequestItem;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mViewGenres = (ListView) findViewById(R.id.lv_request_item);
        ListView mViewFilms = (ListView) findViewById(R.id.lv_film);
        Spinner mViewYears = (Spinner) findViewById(R.id.sp_year);

        //genres adapter init
        ArrayList<RequestItem> mRequestItems = initRequestList();
        RequestListAdapter mAdapterGenres = new RequestListAdapter(this, R.layout.request_item, mRequestItems);
        mViewGenres.setAdapter(mAdapterGenres);
        mViewGenres.setOnItemClickListener(new OnRequestItemClick());

        //years adapter init
        ArrayList<String> mListYears = new ArrayList<>();
        mListYears.addAll(Arrays.asList(getResources().getStringArray(R.array.st_year)));
        ArrayAdapter<String> mAdapterYears = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListYears);
        mAdapterYears.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mViewYears.setAdapter(mAdapterYears);
        mViewYears.setOnItemSelectedListener(new OnSpinnerClick());

        // items adapter init
        ArrayList<Item> mListFilms = new ArrayList<>();
        mListFilms.add(new Item("name", "request_item", "rate"));
        ArrayList<Item> mListItems = new ArrayList<>();
        ItemListAdapter mAdapterFilms = new ItemListAdapter(this, R.layout.item, mListItems);
        mViewFilms.setAdapter(mAdapterFilms);
        mViewFilms.setOnItemClickListener(new OnItemClick());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class OnRequestItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //TODO make a requests from the request objects, not from string values
            String[] requests = getResources().getStringArray(R.array.st_request_id);
            int request = (Integer.parseInt(requests[position]));
            if (request == 0)
                Toast.makeText(MainActivity.this, "Доступно в платном обновлении!", Toast.LENGTH_SHORT).show();
            else {
                //TODO connect to controller
//                Controller.makeRequest(request);
            }
        }
    }

    class OnSpinnerClick implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    class OnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent itemInfo = new Intent(getApplicationContext(), ItemInfo.class);
            startActivity(itemInfo);
        }
    }

    private ArrayList<RequestItem> initRequestList() {
        ArrayList<RequestItem> requestItems = new ArrayList<>();

        String[] names = getResources().getStringArray(R.array.st_request_name);
        String[] images = getResources().getStringArray(R.array.st_request_image);
        String[] ids = getResources().getStringArray(R.array.st_request_id);

        for (int i = 0; i < names.length; i++) {
            requestItems.add(new RequestItem(names[i], images[i], ids[i]));
        }
        return requestItems;
    }
}
