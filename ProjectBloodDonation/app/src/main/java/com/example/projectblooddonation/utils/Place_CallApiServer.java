package com.example.projectblooddonation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectblooddonation.RegisterDonate_Activity;
import com.example.projectblooddonation.ScreenLoginActivity;
import com.example.projectblooddonation.adapter.PlaceAdapter;
import com.example.projectblooddonation.entity.BkResponse;
import com.example.projectblooddonation.entity.DonationPlaces;
import com.example.projectblooddonation.entity.Users;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Place_CallApiServer {
    private static final String HOSTING = "http://192.168.1.18:8080/BloodDonation";
    private static final String API = "/api/bloodDonate/";

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
                TypeToken<List<DonationPlaces>> typeToken = new TypeToken<List<DonationPlaces>>() {
                };
                List<DonationPlaces> lst = gson.fromJson(json, typeToken.getType());
                for (DonationPlaces donationPlaces : lst) {
                    Log.e("-", donationPlaces.getNamePlace() + " | " + donationPlaces.getDayStart() + " | " + donationPlaces.getTimeOpen());
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
     *  @param mCtx    Context Activity
     * @param mLst    Đối tượng Collection chứa dữ liệu
     * @param adapter Adapter đổ dữ liệu
     */
    public static void requestDataPlaces(Context mCtx, List<DonationPlaces> mLst, PlaceAdapter adapter) {
        String api = prepareAPI("list-places");
        Response.Listener listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                String json = null;
                try {
                    json = new String(response.toString().getBytes("UTF-8"), "UTF-8");
                    Log.e("JSON: ", json);
                    Gson gson = new Gson();
                    TypeToken<List<DonationPlaces>> typeToken = new TypeToken<List<DonationPlaces>>() {
                    };
                    List<DonationPlaces> lst = gson.fromJson(json, typeToken.getType());
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

    public static void requestDataPlacesToSpinner(Context context, ArrayAdapter<DonationPlaces> adapter, List<DonationPlaces> lstPlace) {
        String api = prepareAPI("list-places");
        Response.Listener listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                String json = null;
                try {
                    json = new String(response.toString().getBytes("ISO-8859-1"), "UTF-8");
                    Log.e("JSON: ", json);
                    Gson gson = new Gson();
                    TypeToken<List<DonationPlaces>> typeToken = new TypeToken<List<DonationPlaces>>() {
                    };
                    List<DonationPlaces> lst = gson.fromJson(json, typeToken.getType());
                    lstPlace.removeAll(lstPlace);
                    lstPlace.addAll(lst);
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

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(strRequest);
    }
}
