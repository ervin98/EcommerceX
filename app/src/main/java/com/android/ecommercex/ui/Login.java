package com.android.ecommercex.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.android.ecommercex.R;

public class Login extends Fragment {

    public Login(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.frag_login, container, false);

        getActivity().setTitle("Login");

        return view;
    }
}
