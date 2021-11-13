package com.example.projectblooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.projectblooddonation.entity.Users;
import com.example.projectblooddonation.utils.User_CallApiServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void DangkiTK(View view) {
        String fullName = ((EditText) findViewById(R.id.frmHoTen)).getText().toString();
        String password = ((EditText) findViewById(R.id.frmMK)).getText().toString();
        String birthday = ((EditText) findViewById(R.id.frmNgaySinh)).getText().toString();
        Boolean gender = ((RadioButton) findViewById(R.id.frmGtNam)).isChecked();
        String telephone = ((EditText) findViewById(R.id.frmSdt)).getText().toString();
        String address = ((EditText) findViewById(R.id.frmDiachi)).getText().toString();
        String email = ((EditText) findViewById(R.id.frmEmail)).getText().toString();
        String identityCard = ((EditText) findViewById(R.id.frmCmt)).getText().toString();
        String job = ((EditText) findViewById(R.id.frmJob)).getText().toString();

        if(fullName.length()==0)
        {
//            ((EditText) findViewById(R.id.frmHoTen)).requestFocus();
            ((EditText) findViewById(R.id.frmHoTen)).setError("Họ tên không được để trống. Mời bạn nhập!");
        }
        else if(!fullName.matches("[a-zA-Z ]+"))
        {
            ((EditText) findViewById(R.id.frmHoTen)).setError("Chỉ nhập các kí tự từ a-z!");
        }
        else if(password.length()==0)
        {
            ((EditText) findViewById(R.id.frmMK)).setError("Mật khẩu không được để trống!");
        }
        else if(birthday.length()==0){
            ((EditText) findViewById(R.id.frmNgaySinh)).setError("Ngày sinh không được để trống!");
        }
        else if(telephone.length()==0){
            ((EditText) findViewById(R.id.frmSdt)).setError("Số di động không được để trống!");
        }
        else if(email.length()==0){
            ((EditText) findViewById(R.id.frmEmail)).setError("Email không được để trống!");
        }
        else if(identityCard.length()==0){
            ((EditText) findViewById(R.id.frmCmt)).setError("Số CCCD không được để trống!");
        }
        else if(address.length()==0){
            ((EditText) findViewById(R.id.frmDiachi)).setError("Địa chỉ không được để trống!");
        }
        else if (!isValidEmailAddress(email)){
            ((EditText) findViewById(R.id.frmEmail)).setError("Email không hợp lệ!");
        } else {
            try {
                java.sql.Date createDate = new java.sql.Date(new Date().getTime());
                Date dBirhday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                java.sql.Date xbirthday = new java.sql.Date(dBirhday.getTime());
                Users user = new Users(fullName, password, xbirthday, gender, telephone, address, email, identityCard, job, createDate);
                User_CallApiServer.register(this, user);
            } catch (ParseException e) {
                Log.e(RegisterActivity.class.getName(), e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void openDatePicker(View view) {
        EditText ngaySinh = findViewById(R.id.frmNgaySinh);

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
                ngaySinh.setText(format.format(calen.getTime()));

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, year, month, day);
        datePickerDialog.show();
    }
}