package com.android.ecommercex.controller;

public class Cart {
    private int cart_id;
    private String nama;
    private String detail;
    private double harga;
    private String token;
    private String gbr;
    private int jml;

    public Cart(int id, String nama, String detail ,double harga, String gbr,String token,int jml) {
        this.cart_id = id;
        this.nama = nama;
        this.detail = detail;
        this.harga = harga;
        this.gbr = gbr;
        this.token=token;
        this.jml=jml;
    }

    public int getId() {
        return cart_id;
    }
    public String getToken(){
        return token;
    }

    public String getNama() {
        return nama;
    }

    public String getDetail() {
        return detail;
    }

    public double getHarga() {
        return harga;
    }
    public int getJml(){
        return jml;
    }
    public String getGbr() {
        return gbr;
    }
}