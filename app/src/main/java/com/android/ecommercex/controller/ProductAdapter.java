package com.android.ecommercex.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.ecommercex.R;
import com.android.ecommercex.fragment.DetailProduct;
import com.android.ecommercex.utils.Server;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> mProductList;
    private OnItemClickListener mListener;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    double total = 0;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ProductAdapter(Context mCtx, List<Product> productList) {

        this.mCtx = mCtx;
        this.mProductList = productList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Product product = mProductList.get(position);
        final String URL_Gbr = Server.Gambar;

        //loading the image
        Glide.with(mCtx)
                .load(URL_Gbr + product.getGbr())
                .into(holder.imageView);

        holder.textViewTitle.setText(product.getNama());
        holder.textViewShortDesc.setText(product.getDetail());
        holder.textViewRating.setText(String.valueOf(product.getNilai()));
        holder.textViewPrice.setText(formatRupiah.format((product.getHarga())));

        holder.cardClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new DetailProduct();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .addToBackStack(null)
                        .commit();
                Bundle bundle = new Bundle();
                bundle.putString("product_id", Integer.toString(product.getId()));
                bundle.putString("product_name", product.getNama());
                bundle.putString("product_nilai", Double.toString(product.getNilai()));
                bundle.putString("price", Double.toString(product.getHarga()));
                bundle.putString("product_desc", product.getDetail());
                bundle.putString("product_image", URL_Gbr + product.getGbr());

                fragment.setArguments(bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
        CardView cardClick;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            cardClick = itemView.findViewById(R.id.cardviw);
        }
    }
}