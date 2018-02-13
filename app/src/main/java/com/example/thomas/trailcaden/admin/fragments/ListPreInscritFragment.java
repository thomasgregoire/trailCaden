package com.example.thomas.trailcaden.admin.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thomas.trailcaden.model.Person;
import com.example.thomas.trailcaden.PersonAdapter;
import com.example.thomas.trailcaden.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 12/02/2018.
 */

public class ListPreInscritFragment extends Fragment {

    private List<Person> preInscritsList = new ArrayList<>();
    private RecyclerView recyclerViewPreInscrits;
    private PersonAdapter preInscritAdapter;

    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_inscrit, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewPreInscrits = view.findViewById(R.id.listePreInscritsRV);
        preInscritAdapter = new PersonAdapter(preInscritsList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPreInscrits.setLayoutManager(llm);
        recyclerViewPreInscrits.setAdapter(preInscritAdapter);

        displayPreInscrit();
    }

    private void displayPreInscrit() {
        mDatabase.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Person p = dataSnapshot.getValue(Person.class);

                if(!p.isInscrit()) {
                    preInscritsList.add(p);
                }

                preInscritAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}