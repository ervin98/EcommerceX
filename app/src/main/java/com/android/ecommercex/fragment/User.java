package com.android.ecommercex.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.ecommercex.MainActivity;
import com.android.ecommercex.R;

public class User extends Fragment {
    public User(){}
    LinearLayout view;
    Button log_out;
    String id, userName;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    TextView tNama,tId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (LinearLayout) inflater.inflate(R.layout.frag_user, container, false);
        sharedpreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getActivity().getIntent().getStringExtra(TAG_ID);
        userName = getActivity().getIntent().getStringExtra(TAG_USERNAME);


        tNama=view.findViewById(R.id.txt_name);
        tId=view.findViewById(R.id.txt_id);

        tId.setText(id);
        tNama.setText(userName);

        log_out=view.findViewById(R.id.btn_logout);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        return view;
    }
}
