package com.example.thomas.trailcaden.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.thomas.trailcaden.MainActivity;

/**
 * Created by Thomas on 12/02/2018.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        startActivity(new Intent(SplashActivity.this, MainActivity.class));

        finish();
    }
}
