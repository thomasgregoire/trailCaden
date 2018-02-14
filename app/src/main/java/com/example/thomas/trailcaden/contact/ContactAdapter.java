package com.example.thomas.trailcaden.contact;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thomas.trailcaden.R;
import com.example.thomas.trailcaden.model.Contact;

import java.util.List;

/**
 * Created by Safiah on 14/02/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<Contact> contactList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.nom.setText(contact.getPrenom() + " " + contact.getNom());
        holder.poste.setText(contact.getPoste());
        holder.mail.setText(contact.getMail());
        holder.tel.setText(contact.getTel());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView nom, poste,mail,tel;

        public MyViewHolder(View view) {
            super(view);
            nom = (TextView) view.findViewById(R.id.contact_nom);
            poste = (TextView) view.findViewById(R.id.contact_poste);
            mail = (TextView) view.findViewById(R.id.contact_mail);
            tel = (TextView) view.findViewById(R.id.contact_tel);
        }
    }

    public ContactAdapter(List<Contact> contactList){
        this.contactList = contactList;
    }
}
