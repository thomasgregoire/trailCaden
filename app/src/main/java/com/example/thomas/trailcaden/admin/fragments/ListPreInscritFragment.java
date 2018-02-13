package com.example.thomas.trailcaden.admin.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thomas.trailcaden.Person;
import com.example.thomas.trailcaden.PersonAdapter;
import com.example.thomas.trailcaden.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 12/02/2018.
 */

public class ListPreInscritFragment extends Fragment {

    private List<Person> preInscritsList = new ArrayList<>();
    private RecyclerView recyclerViewPreInscrits;
    private PersonAdapter preInscritAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
        recyclerViewPreInscrits.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPreInscrits.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerViewPreInscrits.setAdapter(preInscritAdapter);

        fakePreInscrits();
    }

    private void fakePreInscrits(){
        Person i = new Person("pre", "inscrit", "01/01/0001");
        preInscritsList.add(i);

        i = new Person("pre", "inscrit", "01/01/0001");
        preInscritsList.add(i);

        i = new Person("pre", "inscrit", "01/01/0001");
        preInscritsList.add(i);

        i = new Person("pre2", "inscrit2", "01/01/0001");
        preInscritsList.add(i);

        i = new Person("pre2", "inscrit2", "01/01/0001");
        preInscritsList.add(i);

        i = new Person("pre2", "inscrit2", "01/01/0001");
        preInscritsList.add(i);

        i = new Person("pre3", "inscrit3", "01/01/0001");
        preInscritsList.add(i);

        i = new Person("pre3", "inscrit3", "01/01/0001");
        preInscritsList.add(i);

         i = new Person("pre3", "inscrit3", "01/01/0001");
        preInscritsList.add(i);


        preInscritAdapter.notifyDataSetChanged();
    }
}
