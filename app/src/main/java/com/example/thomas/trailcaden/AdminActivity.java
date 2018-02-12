package com.example.thomas.trailcaden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 11/02/2018.
 */

public class AdminActivity extends AppCompatActivity {

    private List<Inscrit> inscritsList = new ArrayList<>();
    private RecyclerView recyclerViewInscrits;
    private InscritAdapter inscritAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerViewInscrits = findViewById(R.id.listeInscritsRV);
        inscritAdapter = new InscritAdapter(inscritsList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
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

        inscritAdapter.notifyDataSetChanged();
    }

}
