package com.android.ecommercex.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.ecommercex.R;
import com.android.ecommercex.utils.Server;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailProduct extends Fragment {
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    private ImageView imageView;
    private TextView detail_pname,detail_price,detail_desc,detail_pnilai;
    SharedPreferences sharedPreferences;
    private String URL = Server.URL + "activity/addCart.php";
    Button addCart;

    String id_user, userName;
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    public DetailProduct() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail, container, false);

        //detail_id=view.findViewById(R.id.dt_id);
        detail_pname = view.findViewById(R.id.dt_nama);
        detail_price = view.findViewById(R.id.dt_harga);
        detail_desc = view.findViewById(R.id.dt_shortdesc);
        imageView = view.findViewById(R.id.dt_gambar);
        addCart=view.findViewById(R.id.addCart);
        detail_pnilai=view.findViewById(R.id.dt_nilai);

        sharedPreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id_user = getActivity().getIntent().getStringExtra(TAG_ID);
        userName = getActivity().getIntent().getStringExtra(TAG_USERNAME);

        binddata();
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addto_cart();
            }
        });
        return view;
    }
    private void binddata() {
        Bundle bundle = getArguments();

        String product_id =  bundle.getString("product_id");
        String name =  bundle.getString("product_name");
        String price = bundle.getString("price");
        String description = bundle.getString("product_desc");
        String nilai= bundle.getString("product_nilai");
        String imageurl = bundle.getString("product_image");

      //  detail_id.setText(product_id);
        detail_pname.setText(name);
        detail_pnilai.setText("Rating :  "+nilai);
        detail_desc.setText(description);
        Glide.with(this)
                .load(imageurl)
                .into(imageView);
        detail_price.setText(formatRupiah.format((Double.parseDouble(price))));
    }
    private void addto_cart(){
        if(id_user != null ){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getActivity(),"successfully Add Into Cart",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }){

                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params = new HashMap<>();
                    params.put("token", id_user);  //Its a Reference Key Of User succesfull fetch
                    Bundle bundle = getArguments();
                    String product_id = bundle.getString("product_id");
                    params.put("product_id", product_id);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);

        }
        else {
            Toast.makeText(getActivity(),"Silahkan Login Terlebih Dahulu",Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.frame_container, new Login());
            ft.commit();
        }
    }
}


