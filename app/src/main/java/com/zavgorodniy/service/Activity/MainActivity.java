package com.zavgorodniy.service.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zavgorodniy.service.Adapter.ListAdapter;
import com.zavgorodniy.service.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mViewGenres = (ListView) findViewById(R.id.lv_genre);
        ListView mViewYears = (ListView) findViewById(R.id.lv_year);
        ListView mViewFilms = (ListView) findViewById(R.id.lv_film);

        List<String> mListGenres = Arrays.asList(getResources().getStringArray(R.array.st_genre));
        List<String> mListYears = Arrays.asList(getResources().getStringArray(R.array.st_year));
        List<String> mListFilms = new ArrayList<>();

        ListAdapter mAdapterGenres = new ListAdapter(this, R.layout.genre, mListGenres);
        mViewGenres.setAdapter(mAdapterGenres);
        mViewGenres.setOnItemClickListener(new OnItemClick());

        ListAdapter mAdapterYears = new ListAdapter(this, R.layout.genre, mListYears);
        mViewYears.setAdapter(mAdapterYears);
        mViewYears.setOnItemClickListener(new OnItemClick());

        ListAdapter mAdapterFilms = new ListAdapter(this, R.layout.genre, mListFilms);
        mViewFilms.setAdapter(mAdapterFilms);
        mViewFilms.setOnItemClickListener(new OnItemClick());

//        mAdapter.notifyDataSetChanged();
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("Key", "Value");
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

    class OnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Toast.makeText(MainActivity.this, "PRESSED ITEM " + String.valueOf(position),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
