package com.example.projectblooddonation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectblooddonation.R;
import com.example.projectblooddonation.entity.DonationPlaces;

import java.text.SimpleDateFormat;
import java.util.List;

public class PlaceAdapter extends ArrayAdapter<DonationPlaces> {
    private Context placeCtx;
    private int placeLayout;
    private List<DonationPlaces> placeLst;

    public PlaceAdapter(@NonNull Context context, int resource, @NonNull List<DonationPlaces> objects) {
        super(context, resource,  objects);
        this.placeCtx = context;
        this.placeLayout = resource;
        this.placeLst = objects;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null){
            item = LayoutInflater.from(placeCtx).inflate(placeLayout,null);
        }
        DonationPlaces donatePlace = placeLst.get(position);
        ((TextView) item.findViewById(R.id.itemNoiDienRa)).setText(donatePlace.getNamePlace());
        ((TextView) item.findViewById(R.id.itemNgayDienRa)).setText(DateUtility.dateToString(donatePlace.getDayStart()));
        ((TextView) item.findViewById(R.id.itemGioDienRa)).setText(donatePlace.getTimeOpen());
        ((TextView) item.findViewById(R.id.itemDiaChi)).setText(donatePlace.getAddress());
        return item;
    }
}
