package com.android.ecommercex.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.ecommercex.MainActivity;
import com.android.ecommercex.R;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView logo;
    String id, username;
    SharedPreferences sharedpreferences;
    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    Boolean session = false;
    ConnectivityManager conMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        logo = findViewById(R.id.logo);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);


        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (session) {
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_USERNAME, username);
                        finish();
                        startActivity(intent);
                    }
                    else{
                        startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                    }
                    finish();
                }
            }
        };
        timer.start();

    }
}
