package com.example.thomas.trailcaden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.thomas.trailcaden.admin.AdminActivity;
import com.example.thomas.trailcaden.auth.LogInActivity;
import com.example.thomas.trailcaden.contact.ContactActivity;
import com.example.thomas.trailcaden.map.MapActivity;
import com.example.thomas.trailcaden.model.Person;
import com.example.thomas.trailcaden.weather.Weather;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Joris on 13/02/2018.
 */

public class BaseActivity extends AppCompatActivity {
    protected FirebaseAuth mFirebaseAuth;
    protected FirebaseUser mFirebaseUser;
    protected DatabaseReference mDatabase;

    protected boolean isAuth;
    protected boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.panneau);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").orderByChild("uid").equalTo(mFirebaseAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Person p = dataSnapshot.getValue(Person.class);

                isAdmin = p.isAdmin();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if (mFirebaseUser != null) {
            isAuth = true;
        } else {
            isAuth = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();

        if (isAuth) {
            if (isAdmin) {
                inflater.inflate(R.menu.menu_auth_admin, menu);
            } else {
                inflater.inflate(R.menu.menu_auth, menu);
            }
        } else {
            inflater.inflate(R.menu.menu_visitor, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch(item.getItemId()) {
            case R.id.connect:
                loadLogInView();
                break;
            case R.id.action_logout:
                mFirebaseAuth.signOut();
                loadMainActivity();
                break;
            case R.id.weather:
                weather();
                break;
            case R.id.contact:
                contact();
                break;
            case R.id.parcours:
                mapActivity();
                break;
            case R.id.admin:
                espaceAdmin();
                break;
            case R.id.profil:
                profil();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void espaceAdmin(){
        Intent intent = new Intent(this, AdminActivity.class);

        startActivity(intent);
    }

    public void mapActivity(){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void weather(){
        Intent intent = new Intent(this, Weather.class);

        startActivity(intent);
    }

    public void profil(){
        Intent intent = new Intent(this, ProfilActivity.class);

        startActivity(intent);
    }

    public void contact(){
        Intent intent = new Intent(this, ContactActivity.class);

        startActivity(intent);
    }
}
