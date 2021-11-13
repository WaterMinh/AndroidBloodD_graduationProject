package com.example.projectblooddonation;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectblooddonation.adapter.NoticeAdapter;
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.model.HistoryResponse;
import com.example.projectblooddonation.utils.BkContext;
import com.example.projectblooddonation.utils.CallApiServer;

import java.util.ArrayList;
import java.util.List;

public class Notice_Activity extends AppCompatActivity {
    ListView lstView;
    NoticeAdapter notAdapter;
    List<HistoryResponse> lstHis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        subForm();
    }

    private void subForm() {
        BkContext global = (BkContext) getApplicationContext();
        lstHis = global.getmHis();
        lstView = findViewById(R.id.listView);
        notAdapter = new NoticeAdapter(this , R.layout.item_notice, lstHis);
        lstView.setAdapter(notAdapter);
//        CallApiServer.requestDataDayBook(this , lstHis , notAdapter);
    }
}