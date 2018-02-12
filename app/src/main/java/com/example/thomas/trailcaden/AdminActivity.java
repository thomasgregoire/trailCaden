package com.example.thomas.trailcaden;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 11/02/2018.
 */

public class AdminActivity extends AppCompatActivity {

    FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ToggleButton toggleInscrit = findViewById(R.id.toggleButton);
        displayInscrits();

        toggleInscrit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                {
                    hideFragments();
                    displayInscrits();
                }
                else
                {
                    hideFragments();
                    displayPreInscrits();
                }
            }
        });

    }


    public void displayInscrits(){

        Fragment fragInscrit = new ListInscritFragment();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.adminll, fragInscrit);
        fragmentTransaction.commit();
    }

    public void hideFragments(){
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (fm.findFragmentById(R.id.adminll) != null) {
            fragmentTransaction.remove(fm.findFragmentById(R.id.adminll));
        }
        fragmentTransaction.commit();
    }

    public void displayPreInscrits(){

        Fragment fragInscrit = new ListPreInscritFragment();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.adminll, fragInscrit);
        fragmentTransaction.commit();
    }

}
