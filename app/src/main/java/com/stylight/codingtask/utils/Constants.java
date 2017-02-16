package com.stylight.codingtask.utils;


/**
 * Created by muhammadabubakar on 6/8/16.
 */
public class Constants {

    public static String API_KEY = "C6490912AB3211E680F576304DEC7EB7";

    public static final String BASE_URL = "https://api.stylight.com/rest/";
    public static final String PAGE_SIZE_TAG = "{page_size}";

    public static final String POSTS_ENDPOINT = BASE_URL+"posts?pageItems=" + PAGE_SIZE_TAG;
    public static final String PRODUCTS_ENDPOINT = BASE_URL+"products?pageItems=" + PAGE_SIZE_TAG;
    public static final String CATEGORY = "category=";
    public static final String AND = "&";
    public static final String LOCALE = "de_DE";
    public static final String LOCALE_HEADER_TAG = "X-Locale";
    public static final String KEY_HEADER_TAG = "X-apiKey";

    public static final int BAND_ITEMS_SIZE = 10;
    public static final int ITEM_TYPE_BAND = 11;
    public static final int ITEM_TYPE_PRODUCT = 12;
    public static final int ITEM_TYPE_POST = 13;



}
