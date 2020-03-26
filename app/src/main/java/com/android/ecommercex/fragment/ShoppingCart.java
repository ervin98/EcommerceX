package com.android.ecommercex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.ecommercex.R;
import com.android.ecommercex.controller.Cart;
import com.android.ecommercex.controller.CartAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCart extends Fragment {

    public ShoppingCart() {
    }

    RelativeLayout view;

    RecyclerView mView;
    private CartAdapter mCartAdapter;
    private ArrayList<Cart> mCartList;
    private Locale localeID = new Locale("in", "ID");
    private NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    private static final String ARGUMENT_BOAT_ID = "ARGUMENT_BOAT_ID";
    private static final String ARGUMENT_OWNER_ID = "ARGUMENT_OWNER_ID";
    private static final String ARGUMENT_NUMBER_ID = "ARGUMENT_NUMBER";

    public static ShoppingCart newInstance(int boatId, String ownerId, int numberId) {
        Bundle args = new Bundle();
        // Save data here
        args.putInt(ARGUMENT_BOAT_ID, boatId);
        args.putString(ARGUMENT_OWNER_ID, ownerId);
        args.putInt(ARGUMENT_NUMBER_ID,numberId);
        ShoppingCart fragment = new ShoppingCart();
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.frag_cart, container, false);
        getActivity().setTitle("Shopping Cart");
        //Set back button to activity
        mView=view.findViewById(R.id.recycler_view_cart);

        mView.setHasFixedSize(true);
        mView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TextView totalBuy=view.findViewById(R.id.buytotal);

        int numberID= getArguments().getInt(ARGUMENT_NUMBER_ID);
        totalBuy.setText(formatRupiah.format(numberID));

        loadcart();
        return view;
    }
    private void loadcart(){
        mCartAdapter = new CartAdapter(getActivity(), mCartList);
        mView.setAdapter(mCartAdapter);
    }


}

