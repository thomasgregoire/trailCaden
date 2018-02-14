package com.example.thomas.trailcaden.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thomas.trailcaden.BaseActivity;
import com.example.thomas.trailcaden.R;
import com.example.thomas.trailcaden.model.Contact;
import com.example.thomas.trailcaden.contact.ContactAdapter;
import com.example.thomas.trailcaden.model.Person;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Safiah on 14/02/2018.
 */

public class ContactActivity extends BaseActivity {

    private List<Contact> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        recyclerView = (RecyclerView) findViewById(R.id.contact_recyclerView);
        cAdapter = new ContactAdapter(contactList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);

       /* final Button button = findViewById(R.id.button_call);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               callContact(v);
            }
        });
*/
        displayContact();
    }

    private void callContact(View v) {
        View numberView = ((ViewGroup)v).getChildAt(4);
        TextView tv = (TextView)numberView;
        String number = tv.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},1);
        }else{
            startActivity(intent);
        }

    }

    private void displayContact() {
        mDatabase.child("contacts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Contact c = dataSnapshot.getValue(Contact.class);
                contactList.add(c);
                cAdapter.notifyDataSetChanged();
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
    }




}
