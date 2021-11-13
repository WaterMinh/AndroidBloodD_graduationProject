package com.example.projectblooddonation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.projectblooddonation.R;
import com.example.projectblooddonation.entity.Photo;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {
    private Context mContext;
    private List<Photo> mLstPhoto;

    public PhotoAdapter(Context mContext, List<Photo> mLstPhoto) {
        this.mContext = mContext;
        this.mLstPhoto = mLstPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull  ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView imgPhoto = view.findViewById(R.id.img_photo);

        Photo photo = mLstPhoto.get(position);
        if(photo != null){
            //Sử dụng thư viện Glide load ảnh lên imageView
            Glide.with(mContext).load(photo.getResourceId()).into(imgPhoto);
        }

        //Add view to ViewGroup
        container.addView(view);


        return view;
    }

    @Override
    public int getCount() {
        if (mLstPhoto !=null){
            return mLstPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Remove view
        container.removeView((View) object);
    }
}
