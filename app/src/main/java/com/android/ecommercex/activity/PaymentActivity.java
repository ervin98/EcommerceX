package com.android.ecommercex.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.ecommercex.R;
import com.android.ecommercex.utils.Server;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    private String URL = Server.addTran;
    private Locale localeID = new Locale("in", "ID");
    private NumberFormat fRupiah = NumberFormat.getCurrencyInstance(localeID);

    Double sPrice;
    String id_user,stTotal;
    Button sPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        TextView tPrice = findViewById(R.id.sPay);
        TextView tName = findViewById(R.id.sName);
        Bundle bundle=getIntent().getExtras();
        sPrice=bundle.getDouble("totalPrice");
        id_user=bundle.getString("id_user");
        String sUser=bundle.getString("nameToken");
        tName.setText(sUser);
        stTotal=Double.toString(sPrice);
        tPrice.setText(fRupiah.format(sPrice));
        sPayment=findViewById(R.id.sBuy);
        sPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment();
        }
    });
    }
    private void payment(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"successfully Add Into Payment",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("token", id_user);  //Its a Reference Key Of User succesfull fetch
                params.put("totalhrg", stTotal);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
