package com.stylight.codingtask.model;

/**
 * Created by hafiz on 2/15/17.
 */
public class MainListItem{

    protected int itemType;
    protected String title;
    protected String subTitle;
    protected String query;
    protected int noOfItemsInSection;

    public MainListItem(int itemType, String title, String subTitle, String query, int noOfItemsInSection) {
        this.itemType = itemType;
        this.title = title;
        this.subTitle = subTitle;
        this.query = query;
        this.noOfItemsInSection = noOfItemsInSection;
    }


    public int getItemType() {
        return itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getNoOfItemsInSection() {
        return noOfItemsInSection;
    }

    public void setNoOfItemsInSection(int noOfItemsInSection) {
        this.noOfItemsInSection = noOfItemsInSection;
    }


}
