package com.example.projectblooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projectblooddonation.entity.Users;
import com.example.projectblooddonation.utils.User_CallApiServer;

public class ScreenLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);
    }

    public void login(View view) {
        String email = ((EditText) findViewById(R.id.tenDangNhap)).getText().toString();
        String password = ((EditText) findViewById(R.id.matKhau)).getText().toString();

        if (email.length()==0){
            ((EditText) findViewById(R.id.tenDangNhap)).setError("Email đăng nhập không được để trống!");
        } else if (password.length()==0) {
            ((EditText) findViewById(R.id.matKhau)).setError("Nhập mật khẩu để đăng nhập!");
        } else if (!isValidEmailAddress(email)){
            ((EditText) findViewById(R.id.tenDangNhap)).setError("Email không hợp lệ!");
        } else {
            ProgressBar prg = findViewById(R.id.formPrg);
            prg.setVisibility(View.VISIBLE);

            Users user = new Users(email, password);
            User_CallApiServer.login(this, prg, user );
        }

    }

    public void moveRegister(View view) {
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
}