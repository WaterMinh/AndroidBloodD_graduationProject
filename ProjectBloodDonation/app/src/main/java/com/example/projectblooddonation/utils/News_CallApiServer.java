package com.example.projectblooddonation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectblooddonation.DetailNewsActivity;
import com.example.projectblooddonation.adapter.HistoryAdapter;
import com.example.projectblooddonation.adapter.NewsAdapter;
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.entity.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class News_CallApiServer {
    private static final String HOSTING = "http://192.168.1.18:8080/BloodDonation";
    private static final String API = "/api/bloodDonate2/";

    public static String prepareAPI(String pathFunction) {
        return HOSTING + API + pathFunction;
    }
    /**
     * Hàm lấy dữ liệu JSON từ API đơn giản bằng thư viện VOLLEY
     *
     * @param mCtx    Context của Activity
     * @param apilink Link API
     */
    public static void requestJSON(Context mCtx, String apilink) {
        Response.Listener listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                String json = response.toString();
                Log.e("JSON: ", json);
                Gson gson = new Gson();
                TypeToken<List<News>> typeToken = new TypeToken<List<News>>() {
                };
                List<News> lst = gson.fromJson(json, typeToken.getType());
                for (News news : lst) {
                    Log.e("-", news.getImage() + " | " + news.getPublicDate() + " | " + news.getTitle());
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR LOAD JSON", error.toString());
            }
        };

        StringRequest strRequest = new StringRequest(apilink, listener, errorListener);

        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        requestQueue.add(strRequest);
    }


    /**
     * Hàm đổ dữ liệu lên ListView
     *
     * @param mCtx    Context Activity
     * @param mLst    Đối tượng Collection chứa dữ liệu
     * @param adapter Adapter đổ dữ liệu
     */
    public static void requestDataNews(Context mCtx, List<News> mLst, NewsAdapter adapter) {
        String api = prepareAPI("list-news");
        Response.Listener listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                String json = null;
                try {
                    json = new String(response.toString().getBytes("ISO-8859-1"), "UTF-8");
                    Log.e("JSON: ", json);
                    Gson gson = new Gson();
                    TypeToken<List<News>> typeToken = new TypeToken<List<News>>() {
                    };
                    List<News> lst = gson.fromJson(json, typeToken.getType());
                    mLst.removeAll(mLst);
                    mLst.addAll(lst);
                    adapter.notifyDataSetChanged();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR LOAD JSON", error.toString());
            }
        };

        StringRequest strRequest = new StringRequest(api, listener, errorListener);

        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        requestQueue.add(strRequest);
    }


    public static ImageLoader getImageLoader(Context ctx) {
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
                    2 * 1024 * 1024);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        };
        ImageLoader il = new ImageLoader(requestQueue, imageCache);

        return il;
    }

}
