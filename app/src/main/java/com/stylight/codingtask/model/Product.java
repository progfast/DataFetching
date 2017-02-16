package com.stylight.codingtask.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by muhammadabubakar on 2/15/17.
 */

public class Product extends Object implements Serializable{
    private String name;
    private float price;

//    @SerializedName("")
    private Currency currency;
    private List<ProductImage> images;

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getCurrencySymbol() {
        return currency.getSymbol();
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public String getImage(){
        if(images != null){
            return images.get(0).getUrl();
        }
        else{
            return "";
        }
    }

    private class ProductImage{

        private String url;
        private boolean primary;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }
    }
    private class Currency{
        private String id;
        private String name;
        private String symbol;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }
}

