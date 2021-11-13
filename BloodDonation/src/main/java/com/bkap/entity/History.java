package com.bkap.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "History")

public class History {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idHis")
	private Integer idHis;
	@Column(name = "times")
	private Integer times;
	@Column(name = "dayDonation")
	private Date dayDonation;
	@Column(name = "userId")
	private Integer userId;
	@Column(name = "weight")
	private String weight;
	@Column(name = "amountOfBlood")
	private String amountOfBlood;
	@Column(name = "place")
	private Integer place;
	@Column(name = "tinhTrang")
	private boolean tinhTrang;
	@Column(name = "macBenh")
	private boolean macBenh;
	@Column(name = "sutCan")
	private boolean sutCan;
	@Column(name = "phauThuat")
	private boolean phauThuat;
	@Column(name = "xam")
	private boolean xam;
	@Column(name = "truyenMau")
	private boolean truyenMau;
	@Column(name = "maTuy")
	private boolean maTuy;
	@Column(name = "quanHe")
	private boolean quanHe;
	@Column(name = "quanHeCungGioi")
	private boolean quanHeCungGioi;
	@Column(name = "vaccine")
	private boolean vaccine;
	@Column(name = "vungDich")
	private boolean vungDich;
	@Column(name = "benh")
	private boolean benh;
	@Column(name = "thuocKS")
	private boolean thuocKS;
	@Column(name = "diKham")
	private boolean diKham;
	@Column(name = "tanTat")
	private boolean tanTat;
	@Column(name = "mangThai")
	private boolean mangThai;
	@Column(name = "status")
	private boolean status;
	
	
	public History() {
		super();
	}

	public History(Integer idHis, Integer times, Date dayDonation, Integer userId, String weight, String amountOfBlood,
			Integer place, boolean tinhTrang, boolean macBenh, boolean sutCan, boolean phauThuat, boolean xam,
			boolean truyenMau, boolean maTuy, boolean quanHe, boolean quanHeCungGioi, boolean vaccine, boolean vungDich,
			boolean benh, boolean thuocKS, boolean diKham, boolean tanTat, boolean mangThai, boolean status) {
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
	
	
	public History(Integer times, Date dayDonation, Integer userId, String weight, String amountOfBlood, Integer place,
			boolean tinhTrang, boolean macBenh, boolean sutCan, boolean phauThuat, boolean xam, boolean truyenMau,
			boolean maTuy, boolean quanHe, boolean quanHeCungGioi, boolean vaccine, boolean vungDich, boolean benh,
			boolean thuocKS, boolean diKham, boolean tanTat, boolean mangThai, boolean status) {
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

	

	public History(Date dayDonation, Integer place) {
		super();
		this.dayDonation = dayDonation;
		this.place = place;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAmountOfBlood() {
		return amountOfBlood;
	}

	public void setAmountOfBlood(String amountOfBlood) {
		this.amountOfBlood = amountOfBlood;
	}

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
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

	@Override
	public String toString() {
		return "History [idHis=" + idHis + ", times=" + times + ", dayDonation=" + dayDonation + ", userId=" + userId
				+ ", weight=" + weight + ", amountOfBlood=" + amountOfBlood + ", place=" + place + ", tinhTrang="
				+ tinhTrang + ", macBenh=" + macBenh + ", sutCan=" + sutCan + ", phauThuat=" + phauThuat + ", xam="
				+ xam + ", truyenMau=" + truyenMau + ", maTuy=" + maTuy + ", quanHe=" + quanHe + ", quanHeCungGioi="
				+ quanHeCungGioi + ", vaccine=" + vaccine + ", vungDich=" + vungDich + ", benh=" + benh + ", thuocKS="
				+ thuocKS + ", diKham=" + diKham + ", tanTat=" + tanTat + ", mangThai=" + mangThai + ", status=" + status +"]";
	}
	
}
