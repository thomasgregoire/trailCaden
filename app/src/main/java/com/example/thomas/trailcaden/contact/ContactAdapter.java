package com.example.thomas.trailcaden.contact;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thomas.trailcaden.R;
import com.example.thomas.trailcaden.model.Contact;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Safiah on 14/02/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<Contact> contactList;
    private Context context;



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_view,parent,false);

        View buttonView = ((ViewGroup) itemView).getChildAt(4);
        Button button = (Button)buttonView;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callContact(v);
            }
        });

        return new MyViewHolder(itemView);
    }

    private void callContact(View v) {
        View view = (View)v.getParent();
        View tel = ((ViewGroup)view).getChildAt(3);
        String number = ((TextView)tel).getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.CALL_PHONE},1);
        }else{
            context.startActivity(intent);
        }
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

    public ContactAdapter(List<Contact> contactList, Context context){
        this.contactList = contactList;
        this.context = context;
    }
}
