package com.example.thomas.trailcaden;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 12/02/2018.
 */

public class ListInscritFragment extends Fragment {

    private List<Inscrit> inscritsList = new ArrayList<>();
    private RecyclerView recyclerViewInscrits;
    private InscritAdapter inscritAdapter;

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
        inscritAdapter = new InscritAdapter(inscritsList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewInscrits.setLayoutManager(llm);
        recyclerViewInscrits.setAdapter(inscritAdapter);

        fakeInscrits();
    }

    private void fakeInscrits(){
        Inscrit i = new Inscrit("Fonck", "Joris", "22/05/1994");
        inscritsList.add(i);

        i = new Inscrit("Grégoire", "Thomas", "25/06/1995");
        inscritsList.add(i);

        i = new Inscrit("Josso", "Yvann", "13/09/1995");
        inscritsList.add(i);

        i = new Inscrit("Seedat", "Safiah", "02/05/1992");
        inscritsList.add(i);

        i = new Inscrit("Sahraoui", "Hedi-Théo", "31/05/1995");
        inscritsList.add(i);

        i = new Inscrit("Siguret", "Alexandre", "01/08/1992");
        inscritsList.add(i);

        i = new Inscrit("Doe", "Jhon", "01/01/1992");
        inscritsList.add(i);

        i = new Inscrit("test", "test", "01/01/1900");
        inscritsList.add(i);

        i = new Inscrit("a", "a", "01/01/1900");
        inscritsList.add(i);

        i = new Inscrit("b", "b", "01/01/1900");
        inscritsList.add(i);

        i = new Inscrit("c", "c", "01/01/1900");
        inscritsList.add(i);

        i = new Inscrit("d", "d", "01/01/1900");
        inscritsList.add(i);

        i = new Inscrit("e", "e", "01/01/1900");
        inscritsList.add(i);

        i = new Inscrit("f", "f", "01/01/1900");
        inscritsList.add(i);


        inscritAdapter.notifyDataSetChanged();
    }

}
