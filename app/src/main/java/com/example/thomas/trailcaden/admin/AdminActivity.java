package com.example.thomas.trailcaden.admin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.thomas.trailcaden.BaseActivity;
import com.example.thomas.trailcaden.admin.fragments.ListInscritFragment;
import com.example.thomas.trailcaden.admin.fragments.ListPreInscritFragment;
import com.example.thomas.trailcaden.R;
import com.example.thomas.trailcaden.auth.LogInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Thomas on 11/02/2018.
 */

public class AdminActivity extends BaseActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser != null) {
            isAuth = true;
        } else {
            isAuth = false;
        }

        ToggleButton toggleInscrit = findViewById(R.id.toggleButton);
        displayInscrits();

        toggleInscrit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    hideFragments();
                    displayInscrits();
                } else {
                    hideFragments();
                    displayPreInscrits();
                }
            }
        });
    }

    public void displayInscrits() {
        Fragment fragInscrit = new ListInscritFragment();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.adminll, fragInscrit);
        fragmentTransaction.commit();
    }

    public void hideFragments() {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (fm.findFragmentById(R.id.adminll) != null) {
            fragmentTransaction.remove(fm.findFragmentById(R.id.adminll));
        }
        fragmentTransaction.commit();
    }

    public void displayPreInscrits() {
        Fragment fragInscrit = new ListPreInscritFragment();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.adminll, fragInscrit);
        fragmentTransaction.commit();
    }
}
