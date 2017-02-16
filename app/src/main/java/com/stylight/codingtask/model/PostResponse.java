package com.stylight.codingtask.model;

import java.util.List;

/**
 * Created by muhammadabubakar on 2/15/17.
 */

public class PostResponse {
    private int start;
    private int count;
    private int totalCount;

    List<Post> posts;

    public int getStart() {
        return start;
    }

    public int getCount() {
        return count;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Post> getPosts() {
        return posts;
    }
}