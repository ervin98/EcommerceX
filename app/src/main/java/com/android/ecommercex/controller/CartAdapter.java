package com.android.ecommercex.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.ecommercex.R;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context mPtx;
    private List<Cart> mCartList;
    private OnItemClickListener mListener;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    Button cart;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CartAdapter(Context mPtx, List<Cart> cartList) {

        this.mPtx = mPtx;
        this.mCartList = cartList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mPtx).inflate(R.layout.cart_list, null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        final Cart cartx = mCartList.get(position);

        //loading the image
        Glide.with(mPtx)
                .load(cartx.getGbr())
                .into(holder.imageView);

        holder.textViewTitle.setText(cartx.getNama());
        holder.textViewShortDesc.setText(cartx.getDetail());
        holder.textViewPrice.setText(formatRupiah.format((cartx.getHarga())));
        holder.textViewJml.setText(String.valueOf(cartx.getQuantity()));
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewJml, textViewPrice;
        ImageView imageView;
        Button cart;

        public CartViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.cart_item_name);
            textViewJml = itemView.findViewById(R.id.cart_item_jml);
            textViewPrice = itemView.findViewById(R.id.cart_item_price);

        }
    }
}
