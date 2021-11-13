package com.example.projectblooddonation.entity;

import java.sql.Date;

public class History {
    private int idHis;
    private int times;
    private Date dayDonation;
    private int userId;
    private int weight;
    private int amountOfBlood;
    private int place;
    private boolean tinhTrang;
    private boolean macBenh;
    private boolean sutCan;
    private boolean phauThuat;
    private boolean xam ;
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

    public History(int idHis, int times, Date dayDonation, int userId, int weight, int amountOfBlood, int place, boolean tinhTrang, boolean macBenh, boolean sutCan, boolean phauThuat, boolean xam, boolean truyenMau, boolean maTuy, boolean quanHe, boolean quanHeCungGioi, boolean vaccine, boolean vungDich, boolean benh, boolean thuocKS, boolean diKham, boolean tanTat, boolean mangThai, boolean status) {
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

    public History(int idHis, int times, Date dayDonation, int userId, int weight, int amountOfBlood, int place, boolean status) {
        this.idHis = idHis;
        this.times = times;
        this.dayDonation = dayDonation;
        this.userId = userId;
        this.weight = weight;
        this.amountOfBlood = amountOfBlood;
        this.place = place;
        this.status = status;
    }

    public History(Date dayDonation, int userId, int weight, int amountOfBlood, int place, boolean tinhTrang, boolean macBenh, boolean sutCan, boolean phauThuat, boolean xam, boolean truyenMau, boolean maTuy, boolean quanHe, boolean quanHeCungGioi, boolean vaccine, boolean vungDich, boolean benh, boolean thuocKS, boolean diKham, boolean tanTat, boolean mangThai) {
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
    }

    public History(Date dayDonation, int place) {
        this.dayDonation = dayDonation;
        this.place = place;
    }

    public int getIdHis() {
        return idHis;
    }

    public void setIdHis(int idHis) {
        this.idHis = idHis;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Date getDayDonation() {
        return dayDonation;
    }

    public void setDayDonation(Date dayDonation) {
        this.dayDonation = dayDonation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAmountOfBlood() {
        return amountOfBlood;
    }

    public void setAmountOfBlood(int amountOfBlood) {
        this.amountOfBlood = amountOfBlood;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
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
        return "History{" +
                "idHis=" + idHis +
                ", times=" + times +
                ", dayDonation=" + dayDonation +
                ", userId=" + userId +
                ", weight=" + weight +
                ", amountOfBlood=" + amountOfBlood +
                ", place=" + place +
                ", status=" + status +
                '}';
    }
}
