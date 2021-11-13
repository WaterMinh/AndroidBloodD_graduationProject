package com.example.projectblooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.projectblooddonation.adapter.DateUtility;
import com.example.projectblooddonation.entity.News;
import com.example.projectblooddonation.utils.News_CallApiServer;

public class DetailNewsActivity extends AppCompatActivity {
    private int idNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        News news = (News) getIntent().getSerializableExtra("chitiet");
        idNews = news.getIdNews();

        ImageLoader il = News_CallApiServer.getImageLoader(this);
        ((NetworkImageView) findViewById(R.id.detailImage)).setImageUrl(news.getImage(), il);

        ((TextView) findViewById(R.id.detailTieuDe)).setText(news.getTitle());
        ((TextView) findViewById(R.id.detailNgay)).setText(DateUtility.dateToString(news.getPublicDate()));
        ((TextView) findViewById(R.id.detailThongTin)).setText(news.getInfo());
    }


}