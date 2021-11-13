package com.example.projectblooddonation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectblooddonation.MainActivity;
import com.example.projectblooddonation.ScreenLoginActivity;
import com.example.projectblooddonation.entity.BkResponse;
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.entity.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User_CallApiServer {
    private static final String HOSTING = "http://192.168.1.18:8080/BloodDonation";
    private static final String API = "/api/bloodDonate3/";

    public static String prepareImageLink(String imageName) {
        return HOSTING + imageName;
    }

    public static String prepareAPI(String pathFunction) {
        return HOSTING + API + pathFunction;
    }

    public static void register(Context ctx, Users users) {
        String api = prepareAPI("insert-user");
        try {
            // Đối tượng JSON
            JSONObject jsonObject = new JSONObject(new Gson().toJson(users));

            // Đối tượng Listenner
            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    BkResponse bkResponse = new Gson().fromJson(response.toString(), BkResponse.class);
                    if (bkResponse.getId() == 100) {
                        Toast.makeText(ctx, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                        BkContext bkContext = (BkContext) ((Activity) ctx).getApplicationContext();
                        if (bkContext.getmUser() == null) {
                            Intent main = new Intent(ctx, ScreenLoginActivity.class);
                            ((Activity) ctx).startActivity(main);
                        }
                        ((Activity) ctx).finish(); // Đóng màn hình đăng nhập
                    } else {
                        Toast.makeText(ctx, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
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

    public static void login(Context ctx,ProgressBar progressBar, Users users) {
        String api = prepareAPI("user-login");
        try {
            // Đối tượng JSON
            JSONObject jsonObject = new JSONObject(new Gson().toJson(users));

            // Đối tượng Listenner
            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Users bkResponse = new Gson().fromJson(response.toString(), Users.class);
//                    Toast.makeText(ctx, "Đăng nhập thành công: " + bkResponse.getFullName(), Toast.LENGTH_SHORT).show();

                    BkContext bkContext = (BkContext) ((Activity) ctx).getApplicationContext();

                    if (bkContext.getmUser() == null) {
                        Intent main = new Intent(ctx, MainActivity.class);
                        ((Activity) ctx).startActivity(main);
                    }
                    bkContext.setmUser(bkResponse);
                    ((Activity) ctx).finish(); // Đóng màn hình đăng nhập
                }
            };
            // Đối tượng Error
            Response.ErrorListener error = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ctx, "Sai tài khoản hoặc mật khẩu. Xin mời nhập lại!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
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

    public static void updateUser(Context ctx, Users users) {
        String api = prepareAPI("update-user");
        try {
            // Đối tượng JSON
            JSONObject jsonObject = new JSONObject(new Gson().toJson(users));
            // Đối tượng Listenner
            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    BkResponse bkResponse = new Gson().fromJson(response.toString(), BkResponse.class);
                    if (bkResponse.getId() == 100) {
                        Toast.makeText(ctx, "Cập nhập thông tin cá nhân thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ctx, "Cập nhập thông tin cá nhân thất bại", Toast.LENGTH_SHORT).show();
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

    /**
     * Hàm lấy về đối tượng ImageLoader của thư viện VOLLEY (cache ảnh - tăng tốc độ load)
     *
     * @param ctx Context Activity
     * @return Đối tượng ImageLoader (Volley)
     */
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

//    Uploadfile lên serverApi

    public static void uploadBitmap(Context mCtx, final Bitmap bitmap, Users user) {

        String api = prepareAPI("upload");

        Response.Listener<NetworkResponse> listener = new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    JSONObject obj = new JSONObject(new String(response.data));
                    Toast.makeText(mCtx, obj.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mCtx, error.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("GotError", "" + error.getMessage());
            }
        };

        String userId = String.valueOf(user.getUserId());
        String identityCard = user.getIdentityCard();

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, api, listener, errorListener) {
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("avatar", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("identityCard", identityCard);
                params.put("userId", userId);
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(mCtx).add(volleyMultipartRequest);
    }

    private static byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    // =============================================================================================



}
