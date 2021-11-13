package com.example.projectblooddonation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.projectblooddonation.BookPlace_Activity;
import com.example.projectblooddonation.History_Activity;
import com.example.projectblooddonation.RegisterDonate_Activity;
import com.example.projectblooddonation.ScreenLoginActivity;
import com.example.projectblooddonation.adapter.BookedAdapter;
import com.example.projectblooddonation.adapter.HistoryAdapter;
import com.example.projectblooddonation.adapter.NoticeAdapter;
import com.example.projectblooddonation.entity.BkResponse;
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.entity.Users;
import com.example.projectblooddonation.model.HistoryResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CallApiServer {
    private static final String HOSTING = "http://192.168.1.18:8080/BloodDonation";
    private static final String API = "/api/bloodDonate1/";

    /**
     * Hàm chuẩn bị API cho việc gọi
     *
     * @param pathFunction path chức năng ở web service
     * @return
     */
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
                TypeToken<List<History>> typeToken = new TypeToken<List<History>>() {
                };
                List<History> lst = gson.fromJson(json, typeToken.getType());
                for (History history : lst) {
                    Log.e("-", history.getTimes() + " | " + history.getDayDonation() + " | " + history.getAmountOfBlood() + " | " + history.getWeight() + " | " + history.getPlace());
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
    public static void requestDataHistory(Context mCtx, List<HistoryResponse> mLst, HistoryAdapter adapter) {
        BkContext global = (BkContext) mCtx.getApplicationContext();
        String api = prepareAPI("list-history/" + global.getmUser().getUserId());
        Response.Listener listener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                String json = null;
                try {
                    json = new String(response.toString().getBytes("ISO-8859-1"), "UTF-8");
                    Log.e("JSON: ", json);
                    Gson gson = new Gson();
                    TypeToken<List<HistoryResponse>> typeToken = new TypeToken<List<HistoryResponse>>() {
                    };
                    List<HistoryResponse> lst = gson.fromJson(json, typeToken.getType());
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

    public static void insertDatLich(Context ctx, History history) {
        String api = prepareAPI("insert-history");
        try {
            // Đối tượng JSON
            JSONObject jsonObject = new JSONObject(new Gson().toJson(history));

            // Đối tượng Listenner
            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    BkResponse bkResponse = new Gson().fromJson(response.toString(), BkResponse.class);

                    Toast.makeText(ctx, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();

                    BkContext bkContext = (BkContext) ((Activity) ctx).getApplicationContext();
                    if (bkContext.getmHis() == null) {
                        Intent main = new Intent(ctx, BookPlace_Activity.class);
                        ((Activity) ctx).startActivity(main);
                    }
                    ((Activity) ctx).finish(); // Đóng màn hình đăng nhập

                }
            };
            // Đối tượng Error
            Response.ErrorListener error = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ctx, error.getMessage() + "\n" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(ctx);
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, api, jsonObject, listener, error);
            // Gửi request
            requestQueue.add(objectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBook(Context ctx, int hisId) {
        String api = prepareAPI("delete-history");
        try {
            // Đối tượng JSON
            JSONObject jsonObject = new JSONObject(new Gson().toJson(hisId));
            // Đối tượng Listenner
            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    BkResponse bkResponse = new Gson().fromJson(response.toString(), BkResponse.class);
                    if (bkResponse.getId() == 100) {
                        Toast.makeText(ctx, "Huỷ lịch hiến máu thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ctx, "Huỷ lịch hiến máu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            // Đối tượng Error
            Response.ErrorListener error = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ctx, error.getMessage() + "\n" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(ctx);
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, api, jsonObject, listener, error);
            // Gửi request
            requestQueue.add(objectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
