package com.stylight.codingtask.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.stylight.codingtask.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by muhammadabubakar on 12/3/16.
 */
public class WebRequest<T> extends Request<T> {

    private ResponseListener mResponseListener;
    private Class<T> mModelClass;
    private Parser<T> mCustomParser;
    private Map<String, String> mHeaders;
    private String mPayLoad, mContentType;
    private NetworkResponse mNetworkResponse;

    public interface ResponseListener<T> {
        public void onResponse(boolean isSuccess, NetworkResponse networkResponse, T parsedResponse, VolleyError error);
    }
    public interface Parser<T> {
        public T parse(String input);
    }

    public WebRequest(String url){
        this(Method.GET, url);
    }

    public WebRequest(int method, String url){
        super(method, url, null);
        // Turn off cache by default
        setShouldCache(false);
    }

    public WebRequest setModelClass(Class<T> modelClass){
        mModelClass = modelClass;
        return this;
    }

    public WebRequest setCustomParser(Parser<T> customParser){
        mCustomParser = customParser;
        return this;
    }

    public WebRequest addHeader(String key, String value){
        if(mHeaders == null)
            mHeaders = new HashMap<String, String>();

        mHeaders.put(key, value);
        return this;
    }

    public WebRequest setPayLoad(String payLoad){
        mPayLoad = payLoad;
        return this;
    }
    public WebRequest setContentType(String contentType){
        mContentType = contentType;
        return this;
    }

    public void start(ResponseListener responseListener){
        mResponseListener = responseListener;
        VolleyManager.getInstance().addToRequestQueue(this);
    }

    public WebRequest setTag(Object tag){
        super.setTag(tag);
        return this;
    }

    @Override
    public void deliverError(VolleyError error) {
        if(mResponseListener != null)
            mResponseListener.onResponse(false, mNetworkResponse, null, error);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if(Utils.isNull(mHeaders))
            return super.getHeaders();
        else{
            if(!Utils.isNull(super.getHeaders()))
                mHeaders.putAll(super.getHeaders());
            return mHeaders;
        }
    }

    @Override
    public byte[] getBody()
    {
        if(!Utils.isNull(mPayLoad)){
            try {
                return mPayLoad.getBytes("utf-8");
            }catch (Exception e){
            }
        }
        return null;
    }

    @Override
    public String getBodyContentType() {
        if(!Utils.isNull(mContentType))
            return mContentType;
        return super.getBodyContentType();
    }

    @Override
    protected void deliverResponse(T response) {
        if(mResponseListener != null)
            mResponseListener.onResponse(true, mNetworkResponse, response, null);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        T parseResult = null;
        ParseError parseError = null;
        mNetworkResponse = response;

        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            if(mCustomParser != null){
                parseResult = mCustomParser.parse(json);
            }else{
                parseResult = new Gson().fromJson(json, mModelClass);
            }
        } catch (UnsupportedEncodingException e) {
            parseError = new ParseError(e);
        } catch (JsonSyntaxException e) {
            parseError = new ParseError(e);
        }

        if(parseResult != null)
            return Response.success(parseResult, HttpHeaderParser.parseCacheHeaders(response));
        else
            return Response.error(parseError);
    }

}
