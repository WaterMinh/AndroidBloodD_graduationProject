package com.example.projectblooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.NetworkImageView;
import com.example.projectblooddonation.adapter.PhotoAdapter;
import com.example.projectblooddonation.adapter.PlaceAdapter;
import com.example.projectblooddonation.entity.DonationPlaces;
import com.example.projectblooddonation.entity.History;
import com.example.projectblooddonation.entity.News;
import com.example.projectblooddonation.entity.Photo;
import com.example.projectblooddonation.entity.Users;
import com.example.projectblooddonation.utils.BkContext;
import com.example.projectblooddonation.utils.CallApiServer;
import com.example.projectblooddonation.utils.News_CallApiServer;
import com.example.projectblooddonation.utils.Place_CallApiServer;
import com.example.projectblooddonation.utils.User_CallApiServer;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // DrawerLayout
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    //Image with viewpager, Indicator, Glide
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;

    private Users mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.draw_layout);
        navView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        //-----Toolbar
        setSupportActionBar(toolbar);
        //-----Navigation Drawer Menu
        //Hiệu ứng của click item
        navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Khi click chọn item
        navView.setNavigationItemSelectedListener(this);

        navView.setCheckedItem(R.id.Trangchu);

        subPhoto();

        // Hiển thị tên người dùng
        BkContext bkContext = (BkContext) getApplicationContext();
        mUser = bkContext.getmUser();

        ((TextView) findViewById(R.id.loadTenTk)).setText(mUser.getFullName());
        if (mUser.getAvatar() != null && !mUser.getAvatar().isEmpty()) {
            String linkAnh = User_CallApiServer.prepareImageLink(mUser.getAvatar());
            ((NetworkImageView) findViewById(R.id.formAvatar)).setImageUrl(linkAnh, User_CallApiServer.getImageLoader(this));
        }
        ((TextView) findViewById(R.id.nhomMau)).setText(mUser.getBloodType());

    }

    private void subPhoto() {
        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circleIndicator);

        photoAdapter = new PhotoAdapter(this, getListPhoto());
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.image1));
        list.add(new Photo(R.drawable.image2));
        list.add(new Photo(R.drawable.image3));
        list.add(new Photo(R.drawable.image4));

        return list;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Trangchu:
                break;
            case R.id.Lichsu:
                Intent intent = new Intent(MainActivity.this, History_Activity.class);
                startActivity(intent);
                break;
            case R.id.Thongbao:
                Intent intent1 = new Intent(MainActivity.this, Notice_Activity.class);
                startActivity(intent1);
                break;
            case R.id.Caidat:
                Intent intent2 = new Intent(MainActivity.this, UserInfo_Activity.class);
                startActivity(intent2);
                break;

            case R.id.Chiase:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Logout:
                Toast.makeText(this,"Đăng xuất thành công",Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(MainActivity.this, ScreenLoginActivity.class);
                startActivity(logout);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true ;
    }



    public void findPlace(View view) {
        Intent intent = new Intent(MainActivity.this,Place_Activity.class);
        startActivity(intent);
    }

    public void News(View view) {
        Intent intent = new Intent(MainActivity.this,News_Activity.class);
        startActivity(intent);
    }

    public void Contact(View view) {
        Intent intent = new Intent(MainActivity.this,Contact_Activity.class);
        startActivity(intent);
    }

    public void Gift(View view) {
        Intent intent = new Intent(MainActivity.this,GiftInfo_Activity.class);
        startActivity(intent);
    }

    public void QandA(View view) {
        Intent intent = new Intent(MainActivity.this,QuestionAnswer_Activity.class);
        startActivity(intent);

    }

    public void DatLich(View view) {
        Intent intent = new Intent(MainActivity.this,BookPlace_Activity.class);
        startActivity(intent);
    }

}