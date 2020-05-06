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
import com.android.ecommercex.utils.Server;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context mPtx;
    private List<Cart> mCartList;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    private int quan;
    private String URL = Server.addQuant;

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
        String URL_Gbr = Server.Gambar;

        quan=cartlist.getJml();

        holder.textViewTitle.setText(cartlist.getNama());
        holder.textViewPrice.setText(formatRupiah.format((cartlist.getHarga())));
        Glide.with(mPtx)
                .load(URL_Gbr+cartlist.getGbr())
                .into(holder.imageView);
        holder.textViewJml.setText(String.valueOf(cartlist.getJml()));

        holder.cPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quan=quan+1;

                holder.textViewJml.setText(String.valueOf(quan));
            }
        });
        holder.cMin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                quan=quan-1;
                holder.textViewJml.setText(String.valueOf(quan));
            }
        });
    }

    @Override
    public int getItemCount() {
       return mCartList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewJml, textViewPrice;
        ImageView imageView;
        Button cPlus,cMin;

        public CartViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cl_gambar);
            textViewTitle = itemView.findViewById(R.id.cl_nama);
            textViewPrice = itemView.findViewById(R.id.cl_harga);
            textViewJml=itemView.findViewById(R.id.cl_jml);
            cPlus=itemView.findViewById(R.id.cl_plus);
            cMin=itemView.findViewById(R.id.cl_min);

        }
    }
/*
    private void addQuantity(){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){

                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params = new HashMap<>();
                    if(quan==1){
                        params.put("quan",quan);
                    }
                    params.put("token", id_user);  //Its a Reference Key Of User succesfull fetch

                    params.put("product_id", );
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);

        }
*/
}
