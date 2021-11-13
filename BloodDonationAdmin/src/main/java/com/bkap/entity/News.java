package com.bkap.entity;

import java.sql.Date;

public class News {
	private Integer idNews;
	private String image;
	private Date publicDate;
	private String title;
	private String info;
	
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public News(Integer idNews, String image, Date publicDate, String title, String info) {
		super();
		this.idNews = idNews;
		this.image = image;
		this.publicDate = publicDate;
		this.title = title;
		this.info = info;
	}
	public News(String image, Date publicDate, String title, String info) {
		super();
		this.image = image;
		this.publicDate = publicDate;
		this.title = title;
		this.info = info;
	}
	public Integer getIdNews() {
		return idNews;
	}
	public void setIdNews(Integer idNews) {
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
