package com.example.thomas.trailcaden.contact;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.thomas.trailcaden.BaseActivity;
import com.example.thomas.trailcaden.R;
import com.example.thomas.trailcaden.model.Contact;
import com.example.thomas.trailcaden.contact.ContactAdapter;

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
        cAdapter = new ContactAdapter(contactList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);

        prepareData();
    }

    private void prepareData() {
        Contact contact = new Contact("yvannJosso", "Josso", "Yvann", "123", "aa@aa.aa","Roi des beaufs");
        contactList.add(contact);
        contact = new Contact("jorisFonck","Fonck","Joris","456","aa@aa.aa","Monsieur Fonké");
        contactList.add(contact);

    }


}
