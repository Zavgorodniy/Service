package com.zavgorodniy.service.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nick on 02.03.16.
 */
public class Controller {

    private List<Item> items = new ArrayList<>();

    public void start(){
        JsonReq jsonReq  = new JsonReq();
        jsonReq.execute();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(Item items) {
        this.items.add(items);
    }
}
