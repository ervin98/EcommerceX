package com.android.ecommercex.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.ecommercex.DetailActivity;
import com.android.ecommercex.R;
import com.android.ecommercex.controller.CartAdapter;
import com.android.ecommercex.controller.Product;
import com.android.ecommercex.controller.ProductAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Fragment {

    public Cart() {
    }

    RelativeLayout view;

    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;
    private ArrayList<Product> mProductList;
    private RequestQueue mRequestQueue;
    private static final String URL_PRODUCTS = "http://192.168.1.115/Appkasir/Api.php";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.frag_scart, container, false);
        getActivity().setTitle("Shopping Cart");

        //Set back button to activity


        return view;
    }

    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject productObj = array.getJSONObject(i);

                                //adding the product to product list

                                mProductList.add(new Product(
                                        productObj.getInt("id"),
                                        productObj.getString("nama"),
                                        productObj.getString("detail"),
                                        productObj.getDouble("nilai"),
                                        productObj.getDouble("harga"),
                                        productObj.getString("gbr")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            mProductAdapter = new ProductAdapter(getActivity(),mProductList);
                            mRecyclerView.setAdapter(mProductAdapter);
                            mProductAdapter.setOnItemClickListener(Home.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        Product clickedItem = mProductList.get(position);

        detailIntent.putExtra(EXTRA_NAMA, clickedItem.getNama());
        double harga = clickedItem.getHarga();
        String harga2 = Double.toString(harga);
        detailIntent.putExtra(EXTRA_HARGA, harga2);
        detailIntent.putExtra(EXTRA_GAMBAR, clickedItem.getGbr());
        double nilai = clickedItem.getNilai();
        String nilai2 = Double.toString(nilai);
        detailIntent.putExtra(EXTRA_NILAI, nilai2);
        detailIntent.putExtra(EXTRA_SHORTDESC, clickedItem.getDetail());
        startActivity(detailIntent);
    }
}