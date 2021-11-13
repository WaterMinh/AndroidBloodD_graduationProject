package com.bkap.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")

public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "userId")
	private Integer userId;
	@Column (name = "fullName")
	private String fullName;
	@Column (name = "password")
	private String password;
	@Column (name = "avatar")
	private String avatar;
	@Column (name = "birthday")
	private Date birthday;
	@Column (name = "gender")
	private boolean gender;
	@Column (name = "telephone")
	private String telephone;
	@Column (name = "address")
	private String address;
	@Column (name = "email")
	private String email;
	@Column (name = "identityCard")
	private String identityCard; 
	@Column (name = "job")
	private String job;
	@Column (name = "bloodType")
	private String bloodType;
	@Column (name = "permission")
	private int permission;
	@Column (name = "createDate")
	private Date createDate;
	
	public Users() {
		super();
	}

	public Users(Integer userId, String fullName, String password, String avatar, Date birthday, boolean gender,
			String telephone, String address, String email, String identityCard, String job, String bloodType,
			int permission, Date createDate) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.password = password;
		this.avatar = avatar;
		this.birthday = birthday;
		this.gender = gender;
		this.telephone = telephone;
		this.address = address;
		this.email = email;
		this.identityCard = identityCard;
		this.job = job;
		this.bloodType = bloodType;
		this.permission = permission;
		this.createDate = createDate;
	}

	public Users(String fullName, String password, String avatar, Date birthday, boolean gender, String telephone,
			String address, String email, String identityCard, String job, String bloodType, int permission, Date createDate) {
		super();
		this.fullName = fullName;
		this.password = password;
		this.avatar = avatar;
		this.birthday = birthday;
		this.gender = gender;
		this.telephone = telephone;
		this.address = address;
		this.email = email;
		this.identityCard = identityCard;
		this.job = job;
		this.bloodType = bloodType;
		this.permission = permission;
		this.createDate = createDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", fullName=" + fullName + ", password=" + password + ", telephone="
				+ telephone + ", email=" + email + "]";
	}
	
}
