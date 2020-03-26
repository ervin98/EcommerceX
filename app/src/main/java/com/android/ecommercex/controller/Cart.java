package com.android.ecommercex.controller;

public class Cart {
    private int id;
    private String nama;
    private String detail;
    private double nilai;
    private double harga;
    private String gbr;
    private String jml;

    public Cart(int id, String nama, String detail, double nilai, double harga, String gbr) {
        this.id = id;
        this.nama = nama;
        this.detail = detail;
        this.nilai = nilai;
        this.harga = harga;
        this.gbr = gbr;
    }

    public int getId() {
        return id;
    }
    public String getQuantity(){
        return jml;
    }
    public String getNama() {
        return nama;
    }

    public String getDetail() {
        return detail;
    }

    public double getNilai() {
        return nilai;
    }

    public double getHarga() {
        return harga;
    }

    public String getGbr() {
        return gbr;
    }
}