package com.android.ecommercex.controller;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.ecommercex.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context mPtx;
    private List<Cart> mCartList;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    Button cart;


    public CartAdapter(Context mPtx, List<Cart> cartList) {

        this.mPtx = mPtx;
        this.mCartList = cartList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mPtx).inflate(R.layout.cart_list, null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder,final int position) {
        final Cart cartlist = mCartList.get(position);

        holder.textViewTitle.setText(cartlist.getNama());
        holder.textViewPrice.setText(formatRupiah.format((cartlist.getHarga())));
        Glide.with(mPtx)
                .load(cartlist.getGbr())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
       return mCartList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewJml, textViewPrice;
        ImageView imageView;
        Button cart;

        public CartViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cl_gambar);
            textViewTitle = itemView.findViewById(R.id.cl_nama);
            textViewPrice = itemView.findViewById(R.id.cl_harga);

        }
    }
}
