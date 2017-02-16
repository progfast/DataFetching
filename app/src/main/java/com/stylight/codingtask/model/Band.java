package com.stylight.codingtask.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hafiz on 2/15/17.
 */
public class Band extends MainListItem{
    private List<Product> itemsList;

    public Band(int itemType, String title, String subTitle, String query, int noOfItemsInSection) {
        super(itemType, title, subTitle, query, noOfItemsInSection);
    }

    public List<Product> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Product> itemsList) {
        this.itemsList = itemsList;
    }
}
