package com.example.projectblooddonation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.projectblooddonation.adapter.BookedAdapter;
import com.example.projectblooddonation.model.HistoryResponse;
import com.example.projectblooddonation.utils.BkContext;

import java.util.ArrayList;
import java.util.List;

public class BookPlace_Activity extends AppCompatActivity {
    ListView lstView;
    BookedAdapter bookedAdapter;
    List<HistoryResponse> lstHis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_place);
        initForm();
        registerForContextMenu(lstView);

    }

    private void initForm(){
        BkContext global = (BkContext) getApplicationContext();
        lstHis = global.getmHis();
        lstView = findViewById(R.id.listView);
        bookedAdapter = new BookedAdapter(this , R.layout.item_booked, lstHis);
        lstView.setAdapter(bookedAdapter);
//        CallApiServer.requestDataBooked(this , lstHis , bookedAdapter);
    }


    public void MoveBook(View view) {
        Intent moveRegister = new Intent(this, RegisterDonate_Activity.class);
        startActivity(moveRegister);
    }

    @Override
    protected void onResume() {
        initForm();
        super.onResume();
    }
}