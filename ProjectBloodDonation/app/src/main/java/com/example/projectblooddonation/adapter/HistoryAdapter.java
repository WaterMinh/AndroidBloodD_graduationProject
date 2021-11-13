package com.example.projectblooddonation.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectblooddonation.R;
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.model.HistoryResponse;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<HistoryResponse> {
    private Context hisCtx;
    private int hisLayout;
    private List<HistoryResponse> hisLst;

    public HistoryAdapter(@NonNull Context context, int resource, @NonNull List<HistoryResponse> objects) {
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

        HistoryResponse lichsu = hisLst.get(position);

        ((TextView) item.findViewById(R.id.itemLanHien)).setText(String.valueOf(lichsu.getHistory().getTimes()));
        ((TextView) item.findViewById(R.id.itemNgayHien)).setText(DateUtility.dateToString(lichsu.getHistory().getDayDonation()));
        ((TextView) item.findViewById(R.id.itemLuongHien)).setText(String.valueOf(lichsu.getHistory().getAmountOfBlood()) + "ml");
        ((TextView) item.findViewById(R.id.itemCanNangHien)).setText(String.valueOf(lichsu.getHistory().getWeight()) + "kg");
        ((TextView) item.findViewById(R.id.itemDiaDiemHien)).setText(String.valueOf(lichsu.getPlace().getNamePlace()));
        if (lichsu.getHistory().isStatus())
            ((TextView) item.findViewById(R.id.itemStatus)).setText("Hoàn thành");
        else
            ((TextView) item.findViewById(R.id.itemStatus)).setText("Chưa hoàn thành");
        return item;
    }
}
