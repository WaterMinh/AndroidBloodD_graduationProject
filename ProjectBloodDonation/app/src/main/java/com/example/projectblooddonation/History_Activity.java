package com.example.projectblooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectblooddonation.adapter.HistoryAdapter;
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.model.HistoryResponse;
import com.example.projectblooddonation.utils.CallApiServer;

import java.util.ArrayList;
import java.util.List;

public class History_Activity extends AppCompatActivity {
    List<HistoryResponse> mLst = new ArrayList<>();
    HistoryAdapter mAdapter;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        loadHistory();
        registerForContextMenu(lstView);
    }

    private void loadHistory() {
        lstView = findViewById(R.id.listView);
        mAdapter = new HistoryAdapter(this, R.layout.item_history, mLst);
        lstView.setAdapter(mAdapter);
        CallApiServer.requestDataHistory(this, mLst, mAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        HistoryResponse history = mLst.get(info.position);

        menu.setHeaderTitle(history.getHistory().getIdHis());
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int idMenu = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        HistoryResponse his = mLst.get(info.position);
        switch (idMenu){
            case R.id.menu_delete:
                CallApiServer.deleteBook(this, his.getHistory().getIdHis());
        }
        
        return super.onContextItemSelected(item);
    }
}