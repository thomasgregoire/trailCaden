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

public class ListInscritFragment extends Fragment {

    private List<Person> inscritsList = new ArrayList<>();
    private RecyclerView recyclerViewInscrits;
    private PersonAdapter personAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inscrit, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewInscrits = view.findViewById(R.id.listeInscritsRV);
        personAdapter = new PersonAdapter(inscritsList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewInscrits.setLayoutManager(llm);
        recyclerViewInscrits.setItemAnimator(new DefaultItemAnimator());
        recyclerViewInscrits.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerViewInscrits.setAdapter(personAdapter);

        fakeInscrits();
    }

    private void fakeInscrits(){
        Person i = new Person("Fonck", "Joris", "22/05/1994");
        inscritsList.add(i);

        i = new Person("Grégoire", "Thomas", "25/06/1995");
        inscritsList.add(i);

        i = new Person("Josso", "Yvann", "13/09/1995");
        inscritsList.add(i);

        i = new Person("Seedat", "Safiah", "02/05/1992");
        inscritsList.add(i);

        i = new Person("Sahraoui", "Hedi-Théo", "31/05/1995");
        inscritsList.add(i);

        i = new Person("Siguret", "Alexandre", "01/08/1992");
        inscritsList.add(i);

        i = new Person("Doe", "Jhon", "01/01/1992");
        inscritsList.add(i);

        i = new Person("test", "test", "01/01/1900");
        inscritsList.add(i);

        i = new Person("a", "a", "01/01/1900");
        inscritsList.add(i);

        i = new Person("b", "b", "01/01/1900");
        inscritsList.add(i);

        i = new Person("c", "c", "01/01/1900");
        inscritsList.add(i);

        i = new Person("d", "d", "01/01/1900");
        inscritsList.add(i);

        i = new Person("e", "e", "01/01/1900");
        inscritsList.add(i);

        i = new Person("f", "f", "01/01/1900");
        inscritsList.add(i);


        personAdapter.notifyDataSetChanged();
    }

}
