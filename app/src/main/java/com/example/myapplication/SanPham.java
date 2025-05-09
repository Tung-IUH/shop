package com.example.myapplication;

import java.io.Serializable;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int id;
    private String ten;
    private double gia;
    private String hinhanh; // Tên hình ảnh
    private String mota;

    // Constructor
    public SanPham(int id, String ten, double gia, String hinhanh, String mota) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinhanh = hinhanh;
        this.mota = mota;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
