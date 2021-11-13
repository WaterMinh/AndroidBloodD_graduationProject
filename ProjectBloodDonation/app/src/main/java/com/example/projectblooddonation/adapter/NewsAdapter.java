package com.example.projectblooddonation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectblooddonation.MainActivity;
import com.example.projectblooddonation.R;
import com.example.projectblooddonation.entity.DonationPlaces;
import com.example.projectblooddonation.entity.News;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    private Context newsCtx;
    private int newsLayout;
    private List<News> newsLst;
    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        this.newsCtx = context;
        this.newsLayout = resource;
        this.newsLst = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null){
            item = LayoutInflater.from(newsCtx).inflate(newsLayout, null);
        }

        News news = newsLst.get(position);
        //ảnh của tin
        ((TextView) item.findViewById(R.id.itemNgayTin)).setText(DateUtility.dateToString(news.getPublicDate()));
        ((TextView) item.findViewById(R.id.itemTieuDeTin)).setText(news.getTitle());
        return item;
    }
}
