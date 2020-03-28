package com.android.ecommercex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.ecommercex.R;
import com.android.ecommercex.controller.Product;
import com.android.ecommercex.controller.ProductAdapter;
import com.android.ecommercex.utils.Server;
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

public class Home extends Fragment {

    private String URL_PRODUCTS = Server.URL + "Home.php";
    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;
    private ArrayList<Product> mProductList;
    private RequestQueue mRequestQueue;

    public Home(){}
    RelativeLayout view;

    Button addCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.frag_home, container, false);
        getActivity().setTitle("Product List");

            mRecyclerView = view.findViewById(R.id.recylcerView);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mProductList = new ArrayList<>();
            mRequestQueue = Volley.newRequestQueue(getActivity());
            loadProducts();

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
                              //  mProductAdapter.setOnItemClickListener(Home.this);

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
    }