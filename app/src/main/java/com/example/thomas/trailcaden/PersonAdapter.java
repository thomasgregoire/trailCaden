package com.example.thomas.trailcaden;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thomas.trailcaden.admin.fragments.DialogCertificatFragment;

import java.util.List;

/**
 * Created by Thomas on 11/02/2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private List<Person> inscritsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, firsname, date;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            firsname = view.findViewById(R.id.fisrtname);
            date = view.findViewById(R.id.date);
        }
    }

    public PersonAdapter(List<Person> inscritsList) {
        this.inscritsList = inscritsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inscrit_list_view, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCertificatFragment dcf = new DialogCertificatFragment();
                FragmentManager manager = ((Activity) parent.getContext()).getFragmentManager();
                dcf.show(manager, "test");
            }
        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person person = inscritsList.get(position);
        holder.name.setText(person.getName());
        holder.firsname.setText(person.getFirstname());
        holder.date.setText(person.getDate());
    }

    @Override
    public int getItemCount() {
        return inscritsList.size();
    }

}
