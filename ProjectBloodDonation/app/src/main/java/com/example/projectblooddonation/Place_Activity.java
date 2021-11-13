package com.example.projectblooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.example.projectblooddonation.adapter.PlaceAdapter;
import com.example.projectblooddonation.entity.DonationPlaces;
import com.example.projectblooddonation.utils.Place_CallApiServer;

import java.util.ArrayList;
import java.util.List;

public class Place_Activity extends AppCompatActivity {
    ListView lstViewPlace;
    PlaceAdapter placeAdapter;
    List<DonationPlaces> lstPlace = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        initForm();

    }

    private void initForm(){
        lstViewPlace = findViewById(R.id.placeListView);
        placeAdapter = new PlaceAdapter(this , R.layout.item_place, lstPlace);
        lstViewPlace.setAdapter(placeAdapter);
        Place_CallApiServer.requestDataPlaces(this , lstPlace , placeAdapter);
    }


}