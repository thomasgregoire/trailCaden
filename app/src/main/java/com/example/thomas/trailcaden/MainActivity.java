package com.example.thomas.trailcaden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testAdmin(View view){
        Intent intent = new Intent(MainActivity.this, AdminActivity.class);

        startActivity(intent);
    }
}
