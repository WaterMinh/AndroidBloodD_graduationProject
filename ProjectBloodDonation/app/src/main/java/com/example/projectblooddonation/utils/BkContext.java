package com.example.projectblooddonation.utils;

import android.app.Application;

import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.entity.Users;
import com.example.projectblooddonation.model.HistoryResponse;

import java.util.ArrayList;
import java.util.List;

public class BkContext extends Application {
    private Users mUser;
    private List<HistoryResponse> mHis = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Users getmUser() {
        return mUser;
    }

    public void setmUser(Users mUser) {
        this.mUser = mUser;
    }

    public List<HistoryResponse> getmHis() {
        return mHis;
    }

    public void setmHis(List<HistoryResponse> mHis) {
        this.mHis = mHis;
    }


    public void addHistory(HistoryResponse historyResponse){
        mHis.add(historyResponse);
    }
}
