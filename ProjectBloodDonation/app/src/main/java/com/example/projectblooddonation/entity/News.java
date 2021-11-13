package com.example.projectblooddonation.entity;

import java.io.Serializable;
import java.sql.Date;

public class News implements Serializable {
    private int idNews;
    private String image;
    private Date publicDate;
    private String title;
    private String info;

    public News(int idNews, String image, Date publicDate, String title, String info) {
        this.idNews = idNews;
        this.image = image;
        this.publicDate = publicDate;
        this.title = title;
        this.info = info;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
