package com.android.ecommercex.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.ecommercex.R;
import com.android.ecommercex.activity.PaymentActivity;
import com.android.ecommercex.controller.Cart;
import com.android.ecommercex.controller.CartAdapter;
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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCart extends Fragment {

    public ShoppingCart() {}

    private RelativeLayout view;
    private RecyclerView mView;
    private String url = Server.viewCart;
    private CartAdapter mCartAdapter;
    private ArrayList<Cart> mCartList;
    private Locale localeID = new Locale("in", "ID");
    private NumberFormat fRupiah = NumberFormat.getCurrencyInstance(localeID);
    private RequestQueue mRequestQueue;


    Double totalMon=0.0;
    TextView tTotal;
    String halo="0";
    Button payButton;
    private String id_user="0", userName,stTotal;
    private static final String TAG = "Pesan Saya";
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.frag_cart, container, false);
        getActivity().setTitle("Shopping Cart");
        //Set back button to activity
        mView=view.findViewById(R.id.recycler_view_cart);
        mView.setHasFixedSize(true);
        mView.setLayoutManager(new LinearLayoutManager(getActivity()));

        payButton=view.findViewById(R.id.btnPay);
        id_user = getActivity().getIntent().getStringExtra(TAG_ID);
        userName = getActivity().getIntent().getStringExtra(TAG_USERNAME);

        tTotal=view.findViewById(R.id.buytotal);
        mCartList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getActivity());
        loadcart();
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), PaymentActivity.class);
                i.putExtra("totalPrice", totalMon);
                i.putExtra("id_user",id_user);
                i.putExtra("nameToken",userName);
                startActivity(i);

            }
        });
        return view;
    }

    private void loadcart() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
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
                                    halo=productObj.getString("token");
                                if(id_user.equals(halo)){
                                    mCartList.add(new Cart(
                                            productObj.getInt("id"),
                                            productObj.getString("nama"),
                                            productObj.getString("detail"),
                                            productObj.getDouble("harga"),
                                            productObj.getString("gbr"),
                                            productObj.getString("token"),
                                            productObj.getInt("quantity")
                                    ));
                                    Log.i(String.valueOf(productObj.getDouble("harga")), String.valueOf(totalMon));

                                    totalMon=totalMon+(productObj.getDouble("harga")*productObj.getInt("quantity"));
                                }
                            }
                                        mCartAdapter = new CartAdapter(getActivity(), mCartList);
                                        mView.setAdapter(mCartAdapter);
                                        tTotal.setText(fRupiah.format((totalMon)));
                                        stTotal=String.valueOf(totalMon);
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

