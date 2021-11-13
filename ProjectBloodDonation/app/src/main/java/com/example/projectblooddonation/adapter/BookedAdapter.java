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
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.model.HistoryResponse;

import java.util.List;

public class BookedAdapter extends ArrayAdapter<HistoryResponse> {
    private Context hisCtx;
    private int hisLayout;
    private List<HistoryResponse> hisLst;

    public BookedAdapter(@NonNull Context context, int resource, @NonNull List<HistoryResponse> objects) {
        super(context, resource, objects);
        this.hisCtx = context;
        this.hisLayout = resource;
        this.hisLst = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null){
            item = LayoutInflater.from(hisCtx).inflate(hisLayout,null);
        }
        HistoryResponse history = hisLst.get(position);
        ((TextView) item.findViewById(R.id.itemNoiDienRa)).setText(String.valueOf(history.getPlace().getNamePlace()));
        ((TextView) item.findViewById(R.id.itemNgayDatHien)).setText(DateUtility.dateToString(history.getHistory().getDayDonation()));
        return item;
    }
}
