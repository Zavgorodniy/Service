package com.zavgorodniy.service.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Controller controller;
    List<Item> itemsList;
    ItemListAdapter itemsListAdapter;
    List<RequestItem> requestItems;
    RequestListAdapter requestListAdapter;
    List<String> rangeList;
    ArrayAdapter<String> rangeListAdapter;
    Intent itemInfo;
    String genre;

    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = Controller.getInstance();

        ListView mViewGenres = (ListView) findViewById(R.id.lv_request_item);
        ListView mViewFilms = (ListView) findViewById(R.id.lv_item);
        Spinner mViewYears = (Spinner) findViewById(R.id.sp_range);

        //genres adapter init
        requestItems = initRequestList();
        requestListAdapter = new RequestListAdapter(this, R.layout.request_item, requestItems);
        mViewGenres.setAdapter(requestListAdapter);
        mViewGenres.setOnItemClickListener(new OnRequestItemClick());

        //years adapter init
        rangeList = new ArrayList<>();
        rangeList.addAll(Arrays.asList(getResources().getStringArray(R.array.st_year)));
        rangeListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rangeList);
        rangeListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mViewYears.setAdapter(rangeListAdapter);
        mViewYears.setOnItemSelectedListener(new OnSpinnerClick());

        // items adapter init
        itemsList = new ArrayList<>();
        itemsListAdapter = new ItemListAdapter(this, R.layout.item, itemsList);
        mViewFilms.setAdapter(itemsListAdapter);
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
            String[] genres = getResources().getStringArray(R.array.st_request_name);
            int request = (Integer.parseInt(requests[position]));
            genre = genres[position];
            if (request == 0)
                Toast.makeText(MainActivity.this, "Доступно в платном обновлении!", Toast.LENGTH_SHORT).show();
            else
                sendRequest(request);
        }
    }

    class OnSpinnerClick implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            List<Item> list = new ArrayList<>();

            int from = 0;
            int to = 2050;
            switch(position) {
                case 0:
                    list = itemsList;
                    break;
                case 1:
                    from = 1960;
                    to = 1980;
                    break;
                case 2:
                    from = 1980;
                    to = 1990;
                    break;
                case 3:
                    from = 1990;
                    to = 2000;
                    break;
                case 4:
                    from = 2000;
                    to = 2005;
                    break;
                case 5:
                    from = 2005;
                    to = 2010;
                    break;
                case 6:
                    from = 2010;
                    to = 2015;
                    break;
                case 7:
                    from = 2015;
                    to = 2016;
                    break;
            }

            if (position != 0) {
                for (Item item : itemsList) {
                    int year = Integer.parseInt(item.getDate().substring(0, 4));
                    if (from < year && year <= to)
                        list.add(item);
                }
            }
            itemsListAdapter.clear();
            itemsListAdapter.addAll(list);
            itemsListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    class OnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            itemInfo = new Intent(getApplicationContext(), ItemInfo.class);

            item = itemsList.get(position);
            itemInfo.putExtra("name", item.getName());
            itemInfo.putExtra("genre", parseGenres(item.getGenre()));
            itemInfo.putExtra("date", item.getDate());
            itemInfo.putExtra("rating", item.getRating());
            itemInfo.putExtra("description", item.getDescription());
            itemInfo.putExtra("imageId", item.getImageId());

            itemsList.clear();
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

    private void sendRequest(int request) {
        itemsListAdapter.clear();
        controller.start(request);
        itemsList = controller.getItems();
        itemsListAdapter.addAll(itemsList);
        itemsListAdapter.notifyDataSetChanged();
    }

    private String parseGenres(String st) {
        StringBuilder genreSt = new StringBuilder();
        String[] genresId = st.substring(1, st.length() - 1).split(",");
        for (String genre: genresId) {
            for (RequestItem item: requestItems) {
                if (item.request.equals(genre)) {
                    genreSt.append(item.name);
                    genreSt.append(", ");
                }
            }
        }
        genreSt.setLength(genreSt.length() - 2);
        return genreSt.toString();
    }
}
