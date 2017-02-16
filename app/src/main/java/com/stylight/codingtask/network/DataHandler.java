package com.stylight.codingtask.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.stylight.codingtask.model.PostResponse;
import com.stylight.codingtask.model.ProductResponse;
import com.stylight.codingtask.utils.Constants;
import com.stylight.codingtask.utils.Utils;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by muhammadabubakar on 6/25/16.
 */

public class DataHandler {

    private static DataHandler INSTANCE;


    public static DataHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataHandler();
        }
        return INSTANCE;
    }

    public DataHandler() {


    }

    public Observable<PostResponse> getPostsBySlug(final String slug, int pageSize) {
        String url = Constants.POSTS_ENDPOINT.replace(Constants.PAGE_SIZE_TAG, pageSize+"");

        if (!Utils.isNull(slug)) {
            url = url + Constants.AND + Constants.CATEGORY + slug;
        }
        return fetchPosts(url);
    }

    public Observable<ProductResponse> getProductsByCategory(String category, int pageSize) {
        String url = Constants.PRODUCTS_ENDPOINT.replace(Constants.PAGE_SIZE_TAG, pageSize+"");

        if (!Utils.isNull(category)) {
            url = url + Constants.AND + Constants.CATEGORY + category;
        }

        return fetchProducts(url);
    }

    private Observable<ProductResponse> fetchProducts(final String url) {
        return Observable.create(new Observable.OnSubscribe<ProductResponse>() {
            @Override
            public void call(final Subscriber<? super ProductResponse> subscriber) {

                new WebRequest(Request.Method.GET, url)
                        .setTag(this)
                        .addHeader(Constants.LOCALE_HEADER_TAG, Constants.LOCALE)
                        .addHeader(Constants.KEY_HEADER_TAG, Constants.API_KEY)
                        .setModelClass(ProductResponse.class)
                        .start(new WebRequest.ResponseListener<ProductResponse>() {
                            @Override
                            public void onResponse(boolean isSuccess, NetworkResponse networkResponse,
                                                   ProductResponse parsedResponse, VolleyError error) {
                                if (isSuccess) {
                                    subscriber.onNext(parsedResponse);
                                } else {
                                    subscriber.onError(new Throwable(error.getMessage()));
                                }
                            }
                        });
            }
        });
    }

    private Observable<PostResponse> fetchPosts(final String url) {
        return Observable.create(new Observable.OnSubscribe<PostResponse>() {
            @Override
            public void call(final Subscriber<? super PostResponse> subscriber) {

                new WebRequest(Request.Method.GET, url)
                        .setTag(this)
                        .addHeader(Constants.LOCALE_HEADER_TAG, Constants.LOCALE)
                        .addHeader(Constants.KEY_HEADER_TAG, Constants.API_KEY)
                        .setModelClass(PostResponse.class)
                        .start(new WebRequest.ResponseListener<PostResponse>() {
                            @Override
                            public void onResponse(boolean isSuccess, NetworkResponse networkResponse,
                                                   PostResponse parsedResponse, VolleyError error) {
                                if (isSuccess) {
                                    subscriber.onNext(parsedResponse);
                                } else {
                                    subscriber.onError(new Throwable(error.getMessage()));
                                }
                            }
                        });
            }
        });
    }

}
