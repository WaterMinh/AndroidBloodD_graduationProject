package com.example.projectblooddonation.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
    public static String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
