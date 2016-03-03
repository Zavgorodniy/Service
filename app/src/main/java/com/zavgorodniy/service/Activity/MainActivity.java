package com.zavgorodniy.service.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zavgorodniy.service.Adapter.ListAdapter;
import com.zavgorodniy.service.R;
import com.zavgorodniy.service.Service.Controller;
import com.zavgorodniy.service.Service.Item;
import com.zavgorodniy.service.Service.JsonReq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller controller = new Controller();
        controller.start();
        int i = 0;
        List<Item> list = controller.getItems();
        while (i< 10){
            list.get(i).getName();
            list.get(i).getGenre();
            list.get(i).getDate();
            list.get(i).getDescription();
            list.get(i).getRating();
        }
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
