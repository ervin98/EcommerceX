package com.android.ecommercex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import static com.android.ecommercex.ui.Home.EXTRA_GAMBAR;
import static com.android.ecommercex.ui.Home.EXTRA_HARGA;
import static com.android.ecommercex.ui.Home.EXTRA_NAMA;
import static com.android.ecommercex.ui.Home.EXTRA_NILAI;
import static com.android.ecommercex.ui.Home.EXTRA_SHORTDESC;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String productName = intent.getStringExtra(EXTRA_NAMA);
        String detailDesc = intent.getStringExtra(EXTRA_SHORTDESC);
        String imageUrl = intent.getStringExtra(EXTRA_GAMBAR);
        String nilaiStar= intent.getStringExtra(EXTRA_NILAI);
        String hargaPr =intent.getStringExtra(EXTRA_HARGA);

        ImageView imageView = findViewById(R.id.dt_image);
        TextView tName = findViewById(R.id.dt_name);
        TextView tDetail = findViewById(R.id.dt_shortdesc);
        TextView tRate = findViewById(R.id.dt_nilai);
        TextView tHarga = findViewById(R.id.dt_harga);


        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        tName.setText(productName);
        tDetail.setText(detailDesc);
        tRate.setText(nilaiStar);
        double hargaPrice=Double.parseDouble(hargaPr);
        tHarga.setText(formatRupiah.format(hargaPrice));


    }
}