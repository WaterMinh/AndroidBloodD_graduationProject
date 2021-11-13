package com.bkap.entity;

import java.sql.Date;

public class History {
	private Integer idHis;
	private Integer times;
	private Date dayDonation;
	private Integer userId;
	private Integer weight;
	private Integer amountOfBlood;
	private Integer place;
	private String fullName;
	private String placeName;
	private boolean tinhTrang;
	private boolean macBenh;
	private boolean sutCan;
	private boolean phauThuat;
	private boolean xam;
	private boolean truyenMau;
	private boolean maTuy;
	private boolean quanHe;
	private boolean quanHeCungGioi;
	private boolean vaccine;
	private boolean vungDich;
	private boolean benh;
	private boolean thuocKS;
	private boolean diKham;
	private boolean tanTat;
	private boolean mangThai;
	private boolean status;
	
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public History(Integer idHis, Integer times, Date dayDonation, Integer userId, Integer weight,
			Integer amountOfBlood, Integer place, boolean tinhTrang, boolean macBenh,
			boolean sutCan, boolean phauThuat, boolean xam, boolean truyenMau, boolean maTuy, boolean quanHe,
			boolean quanHeCungGioi, boolean vaccine, boolean vungDich, boolean benh, boolean thuocKS, boolean diKham,
			boolean tanTat, boolean mangThai, boolean status) {
		super();
		this.idHis = idHis;
		this.times = times;
		this.dayDonation = dayDonation;
		this.userId = userId;
		this.weight = weight;
		this.amountOfBlood = amountOfBlood;
		this.place = place;
		this.tinhTrang = tinhTrang;
		this.macBenh = macBenh;
		this.sutCan = sutCan;
		this.phauThuat = phauThuat;
		this.xam = xam;
		this.truyenMau = truyenMau;
		this.maTuy = maTuy;
		this.quanHe = quanHe;
		this.quanHeCungGioi = quanHeCungGioi;
		this.vaccine = vaccine;
		this.vungDich = vungDich;
		this.benh = benh;
		this.thuocKS = thuocKS;
		this.diKham = diKham;
		this.tanTat = tanTat;
		this.mangThai = mangThai;
		this.status = status;
	}

	public History(Integer times, Date dayDonation, Integer userId, Integer weight, Integer amountOfBlood,
			Integer place, boolean tinhTrang, boolean macBenh, boolean sutCan,
			boolean phauThuat, boolean xam, boolean truyenMau, boolean maTuy, boolean quanHe, boolean quanHeCungGioi,
			boolean vaccine, boolean vungDich, boolean benh, boolean thuocKS, boolean diKham, boolean tanTat,
			boolean mangThai, boolean status) {
		super();
		this.times = times;
		this.dayDonation = dayDonation;
		this.userId = userId;
		this.weight = weight;
		this.amountOfBlood = amountOfBlood;
		this.place = place;
		this.tinhTrang = tinhTrang;
		this.macBenh = macBenh;
		this.sutCan = sutCan;
		this.phauThuat = phauThuat;
		this.xam = xam;
		this.truyenMau = truyenMau;
		this.maTuy = maTuy;
		this.quanHe = quanHe;
		this.quanHeCungGioi = quanHeCungGioi;
		this.vaccine = vaccine;
		this.vungDich = vungDich;
		this.benh = benh;
		this.thuocKS = thuocKS;
		this.diKham = diKham;
		this.tanTat = tanTat;
		this.mangThai = mangThai;
		this.status = status;
	}

	public Integer getIdHis() {
		return idHis;
	}

	public void setIdHis(Integer idHis) {
		this.idHis = idHis;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Date getDayDonation() {
		return dayDonation;
	}

	public void setDayDonation(Date dayDonation) {
		this.dayDonation = dayDonation;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getAmountOfBlood() {
		return amountOfBlood;
	}

	public void setAmountOfBlood(Integer amountOfBlood) {
		this.amountOfBlood = amountOfBlood;
	}

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public boolean isMacBenh() {
		return macBenh;
	}

	public void setMacBenh(boolean macBenh) {
		this.macBenh = macBenh;
	}

	public boolean isSutCan() {
		return sutCan;
	}

	public void setSutCan(boolean sutCan) {
		this.sutCan = sutCan;
	}

	public boolean isPhauThuat() {
		return phauThuat;
	}

	public void setPhauThuat(boolean phauThuat) {
		this.phauThuat = phauThuat;
	}

	public boolean isXam() {
		return xam;
	}

	public void setXam(boolean xam) {
		this.xam = xam;
	}

	public boolean isTruyenMau() {
		return truyenMau;
	}

	public void setTruyenMau(boolean truyenMau) {
		this.truyenMau = truyenMau;
	}

	public boolean isMaTuy() {
		return maTuy;
	}

	public void setMaTuy(boolean maTuy) {
		this.maTuy = maTuy;
	}

	public boolean isQuanHe() {
		return quanHe;
	}

	public void setQuanHe(boolean quanHe) {
		this.quanHe = quanHe;
	}

	public boolean isQuanHeCungGioi() {
		return quanHeCungGioi;
	}

	public void setQuanHeCungGioi(boolean quanHeCungGioi) {
		this.quanHeCungGioi = quanHeCungGioi;
	}

	public boolean isVaccine() {
		return vaccine;
	}

	public void setVaccine(boolean vaccine) {
		this.vaccine = vaccine;
	}

	public boolean isVungDich() {
		return vungDich;
	}

	public void setVungDich(boolean vungDich) {
		this.vungDich = vungDich;
	}

	public boolean isBenh() {
		return benh;
	}

	public void setBenh(boolean benh) {
		this.benh = benh;
	}

	public boolean isThuocKS() {
		return thuocKS;
	}

	public void setThuocKS(boolean thuocKS) {
		this.thuocKS = thuocKS;
	}

	public boolean isDiKham() {
		return diKham;
	}

	public void setDiKham(boolean diKham) {
		this.diKham = diKham;
	}

	public boolean isTanTat() {
		return tanTat;
	}

	public void setTanTat(boolean tanTat) {
		this.tanTat = tanTat;
	}

	public boolean isMangThai() {
		return mangThai;
	}

	public void setMangThai(boolean mangThai) {
		this.mangThai = mangThai;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
}
