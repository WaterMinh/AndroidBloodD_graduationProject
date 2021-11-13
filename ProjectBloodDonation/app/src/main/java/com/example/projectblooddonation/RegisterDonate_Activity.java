package com.example.projectblooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectblooddonation.adapter.BookedAdapter;
import com.example.projectblooddonation.adapter.DateUtility;
import com.example.projectblooddonation.adapter.PlaceAdapter;
import com.example.projectblooddonation.entity.DonationPlaces;
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.entity.Users;
import com.example.projectblooddonation.model.HistoryResponse;
import com.example.projectblooddonation.utils.BkContext;
import com.example.projectblooddonation.utils.CallApiServer;
import com.example.projectblooddonation.utils.Place_CallApiServer;
import com.example.projectblooddonation.utils.User_CallApiServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegisterDonate_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_donate);
        loadPlace();
    }
    private void loadPlace() {
        List<DonationPlaces> lstPlace = new ArrayList<>();
        ArrayAdapter<DonationPlaces> adapter = new ArrayAdapter<DonationPlaces>(this, android.R.layout.simple_list_item_1, lstPlace);
        Spinner spPlace = findViewById(R.id.spnPlace);
        spPlace.setAdapter(adapter);
        //((Spinner) findViewById(R.id.spnPlace)).setAdapter(adapter);
        Place_CallApiServer.requestDataPlacesToSpinner(this , adapter , lstPlace);

    }

    public void Book(View view) throws ParseException {
        String strDayDonate = ((EditText) findViewById(R.id.formNgayDatLich)).getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date getDayDonation = dateFormat.parse(strDayDonate);
        java.sql.Date dayDonation = new java.sql.Date(getDayDonation.getTime());

        Spinner spPlace = findViewById(R.id.spnPlace);
        DonationPlaces donationPlaces = (DonationPlaces) spPlace.getSelectedItem();
        int place = donationPlaces.getIdPlace();

        boolean tinhTrang = ((RadioButton) findViewById(R.id.frmCoTung)).createAccessibilityNodeInfo().isChecked();
        boolean macBenh = ((RadioButton) findViewById(R.id.frmTungMac)).createAccessibilityNodeInfo().isChecked();
        boolean sutCan = ((RadioButton) findViewById(R.id.frmCoSut)).createAccessibilityNodeInfo().isChecked();
        boolean phauThuat = ((RadioButton) findViewById(R.id.frmCoPhau)).createAccessibilityNodeInfo().isChecked();
        boolean xam = ((RadioButton) findViewById(R.id.frmCoXam)).createAccessibilityNodeInfo().isChecked();
        boolean truyenMau = ((RadioButton) findViewById(R.id.frmCoTruyen)).createAccessibilityNodeInfo().isChecked();
        boolean maTuy = ((RadioButton) findViewById(R.id.frmCoTiem)).createAccessibilityNodeInfo().isChecked();
        boolean quanHe = ((RadioButton) findViewById(R.id.frmCoQHTD)).createAccessibilityNodeInfo().isChecked();
        boolean quanHeCungGioi = ((RadioButton) findViewById(R.id.frmCoQH)).createAccessibilityNodeInfo().isChecked();
        boolean vaccine = ((RadioButton) findViewById(R.id.frmRoi)).createAccessibilityNodeInfo().isChecked();
        boolean vungDich = ((RadioButton) findViewById(R.id.frmCoO)).createAccessibilityNodeInfo().isChecked();
        boolean benh = ((RadioButton) findViewById(R.id.frmCoBi)).createAccessibilityNodeInfo().isChecked();
        boolean thuocKS = ((RadioButton) findViewById(R.id.frmCoUong)).createAccessibilityNodeInfo().isChecked();
        boolean diKham = ((RadioButton) findViewById(R.id.frmCoKham)).createAccessibilityNodeInfo().isChecked();
        boolean tanTat = ((RadioButton) findViewById(R.id.frmKhong)).createAccessibilityNodeInfo().isChecked();
        boolean mangThai = ((RadioButton) findViewById(R.id.frmCoThai)).createAccessibilityNodeInfo().isChecked();

        BkContext bkContext = (BkContext) getApplicationContext();

        History history = new History(dayDonation, bkContext.getmUser().getUserId(), 0, 0, place, tinhTrang, macBenh, sutCan, phauThuat, xam, truyenMau, maTuy, quanHe, quanHeCungGioi, vaccine, vungDich, benh, thuocKS, diKham, tanTat, mangThai);

        BkContext global = (BkContext) getApplicationContext();
        global.addHistory(new HistoryResponse(history , donationPlaces));
        CallApiServer.insertDatLich(this, history);
    }

    public void openDatePicker(View view) {
        EditText ngayDatLich = findViewById(R.id.formNgayDatLich);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calen = Calendar.getInstance();
                calen.set(year,month, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                ngayDatLich.setText(format.format(calen.getTime()));

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, year, month, day);
        datePickerDialog.show();
    }
}