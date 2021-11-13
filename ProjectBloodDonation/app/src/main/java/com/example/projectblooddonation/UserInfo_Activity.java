package com.example.projectblooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.projectblooddonation.entity.Users;
import com.example.projectblooddonation.utils.BkContext;
import com.example.projectblooddonation.utils.CallApiServer;
import com.example.projectblooddonation.utils.User_CallApiServer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserInfo_Activity extends AppCompatActivity {
    private Users mUser;
    private static final int REQUEST_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        unit();
        if (mUser.getAvatar() != null && !mUser.getAvatar().isEmpty()) {
            String linkAnh = User_CallApiServer.prepareImageLink(mUser.getAvatar());
            Log.e("Link anh dai dien:", linkAnh);
            ((NetworkImageView) findViewById(R.id.formAvatar)).setImageUrl(linkAnh, User_CallApiServer.getImageLoader(this));
        }
    }

    private void unit() {
        BkContext bkContext = (BkContext) getApplicationContext();
        mUser = bkContext.getmUser();

        ((EditText) findViewById(R.id.formTen)).setText(mUser.getFullName());
        ((EditText) findViewById(R.id.formNgaySinh)).setText(new SimpleDateFormat("yyyy-MM-dd").format(mUser.getBirthday()));
        ((EditText) findViewById(R.id.formMK)).setText(mUser.getPassword());
        if (mUser.getGender()) {
            ((RadioGroup) findViewById(R.id.formGioiTinh)).check(R.id.formGtNam);
        } else {
            ((RadioGroup) findViewById(R.id.formGioiTinh)).check(R.id.formGtNu);
        }
        ((EditText) findViewById(R.id.formSdt)).setText(mUser.getTelephone());
        ((EditText) findViewById(R.id.formDiaChi)).setText(mUser.getAddress());
        ((EditText) findViewById(R.id.formEmail)).setText(mUser.getEmail());
        ((EditText) findViewById(R.id.formCmt)).setText(mUser.getIdentityCard());
        ((EditText) findViewById(R.id.formJob)).setText(mUser.getJob());

        // Kiểm tra quyền đọc dữ liệu thẻ nhớ
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
            }
        }

    }
    public void updateInfo(View view) {
        String fullName = ((EditText) findViewById(R.id.formTen)).getText().toString();
        String password = ((EditText) findViewById(R.id.formMK)).getText().toString();
        String birthday = ((EditText) findViewById(R.id.formNgaySinh)).getText().toString();
        boolean gender = ((RadioButton) findViewById(R.id.formGtNam)).isChecked();
        String telephone = ((EditText) findViewById(R.id.formSdt)).getText().toString();
        String address = ((EditText) findViewById(R.id.formDiaChi)).getText().toString();
        String email = ((EditText) findViewById(R.id.formEmail)).getText().toString();
        String identityCard = ((EditText) findViewById(R.id.formCmt)).getText().toString();
        String job = ((EditText) findViewById(R.id.formJob)).getText().toString();


        if(fullName.length()==0)
        {
//            ((EditText) findViewById(R.id.frmHoTen)).requestFocus();
            ((EditText) findViewById(R.id.formTen)).setError("Họ tên không được để trống. Mời bạn nhập!");
        }
        else if(!fullName.matches("[a-zA-Z ]+"))
        {
            ((EditText) findViewById(R.id.formTen)).setError("Chỉ nhập các kí tự từ a-z!");
        }
        else if(password.length()==0)
        {
            ((EditText) findViewById(R.id.formMK)).setError("Mật khẩu không được để trống!");
        }
        else if(birthday.length()==0){
            ((EditText) findViewById(R.id.formNgaySinh)).setError("Ngày sinh không được để trống!");
        }
        else if(telephone.length()==0){
            ((EditText) findViewById(R.id.formSdt)).setError("Số di động không được để trống!");
        }
        else if(email.length()==0){
            ((EditText) findViewById(R.id.formEmail)).setError("Email không được để trống!");
        }
        else if(identityCard.length()==0){
            ((EditText) findViewById(R.id.formCmt)).setError("Số CCCD không được để trống!");
        }
        else if(address.length()==0){
            ((EditText) findViewById(R.id.formDiaChi)).setError("Địa chỉ không được để trống!");
        }
        else
        {
            try {
                Date dBirthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                java.sql.Date xbirthday = new java.sql.Date(dBirthday.getTime());
                Users user = new Users(fullName, password,  xbirthday, gender,telephone, address, email, identityCard, job);

                // Lấy ID User và Cập nhật
                BkContext bkContext = (BkContext) getApplicationContext();
                user.setUserId(bkContext.getmUser().getUserId());

                User_CallApiServer.updateUser(this, user);
            } catch (ParseException e) {
                Log.e(RegisterActivity.class.getName(), e.getMessage());
                e.printStackTrace();
            }

        }




    }

    public void openDatePicker(View view) {
        EditText ngaySinh = findViewById(R.id.formNgaySinh);

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

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    public void changeAvatar(View view) {
        // Mở bộ nhớ lấy ảnh
        // in onCreate or any event where your want the user to
        // select a file
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                Log.e("Link anh: ", ">>>> " + selectedImagePath + " | " + selectedImageUri.getPath());

                try {
                    // Hiển thị ảnh lên ImageView
                    InputStream imageStream = getContentResolver().openInputStream(selectedImageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ImageView avatar = findViewById(R.id.formAvatar);
                    avatar.setImageBitmap(selectedImage);

                    // Upload file lên server
                    User_CallApiServer.uploadBitmap(getApplicationContext(), selectedImage, mUser);
                } catch (FileNotFoundException fnfe) {
                    Log.e("Lỗi: ", fnfe.getMessage());
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}