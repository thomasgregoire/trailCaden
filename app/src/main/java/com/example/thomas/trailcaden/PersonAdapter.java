package com.example.thomas.trailcaden;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thomas.trailcaden.model.Person;
import com.example.thomas.trailcaden.admin.fragments.DialogCertificatFragment;

import java.util.List;

/**
 * Created by Thomas on 11/02/2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private List<Person> inscritsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, firsname, date, mail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            firsname = view.findViewById(R.id.fisrtname);
            date = view.findViewById(R.id.date);
            mail = view.findViewById(R.id.mail);
        }
    }

    public PersonAdapter(List<Person> inscritsList) {
        this.inscritsList = inscritsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inscrit_list_view, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View mail = ((ViewGroup)view).getChildAt(2);
                DialogCertificatFragment dcf = new DialogCertificatFragment();
                TextView tv = (TextView)mail;
                dcf.setMail(tv.getText().toString());
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
        holder.mail.setText(person.getMail());
    }

    @Override
    public int getItemCount() {
        return inscritsList.size();
    }

    public List<Person> getInscritsList() {
        return inscritsList;
    }
}
