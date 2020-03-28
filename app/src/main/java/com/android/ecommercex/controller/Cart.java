package com.android.ecommercex.controller;

public class Cart {
    private int cart_id;
    private String nama;
    private String detail;
    private double harga;
    private String token;
    private String gbr;
    private String jml;

    public Cart(int id, String nama, String detail ,double harga, String gbr,String token) {
        this.cart_id = id;
        this.nama = nama;
        this.detail = detail;

        this.harga = harga;
        this.gbr = gbr;
        this.token=token;
    }

    public int getId() {
        return cart_id;
    }
    public String getToken(){
        return token;
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

    public double getHarga() {
        return harga;
    }

    public String getGbr() {
        return gbr;
    }
}