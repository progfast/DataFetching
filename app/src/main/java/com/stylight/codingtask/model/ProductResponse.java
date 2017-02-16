package com.stylight.codingtask.model;

import java.util.List;

/**
 * Created by muhammadabubakar on 2/15/17.
 */

public class ProductResponse {
    private int start;
    private int count;
    private int totalResults;

    List<Product> products;

    public int getStart() {
        return start;
    }

    public int getCount() {
        return count;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Product> getProducts() {
        return products;
    }
}