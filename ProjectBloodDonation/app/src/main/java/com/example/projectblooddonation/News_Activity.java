package com.example.projectblooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projectblooddonation.adapter.NewsAdapter;
import com.example.projectblooddonation.entity.News;
import com.example.projectblooddonation.utils.CallApiServer;
import com.example.projectblooddonation.utils.News_CallApiServer;

import java.util.ArrayList;
import java.util.List;

public class News_Activity extends AppCompatActivity {
    List<News> mLst = new ArrayList<>();
    NewsAdapter mAdapter;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        loadNews();
    }

    private void loadNews() {
        lstView = findViewById(R.id.listViewNews);
        mAdapter = new NewsAdapter(this, R.layout.item_news, mLst);
        lstView.setAdapter(mAdapter);
        News_CallApiServer.requestDataNews(this, mLst, mAdapter);

        lstView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                News_CallApiServer.requestDataNews(News_Activity.this, mLst, mAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Context context = this;
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = mLst.get(position);
                Intent newsIntent = new Intent(context , DetailNewsActivity.class);
                newsIntent.putExtra("chitiet" , news);
                ((Activity)context).startActivity(newsIntent);
            }
        });
    }
}