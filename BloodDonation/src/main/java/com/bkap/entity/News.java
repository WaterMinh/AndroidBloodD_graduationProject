package com.bkap.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="News")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idNews")
	private Integer idNews;
	@Column(name = "image")
	private String image;
	@Column(name = "publicDate")
	private Date publicDate;
	@Column(name = "title")
	private String title;
	@Column(name = "info")
	private String info;
	
	public News() {
		super();
	}

	public News(Integer idNews, String image, Date publicDate, String title, String info) {
		super();
		this.idNews = idNews;
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
