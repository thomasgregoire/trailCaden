package com.example.thomas.trailcaden.admin.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thomas.trailcaden.R;
import com.example.thomas.trailcaden.model.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by Thomas on 12/02/2018.
 */

public class DialogCertificatFragment extends DialogFragment {

    private String mail;

    private DatabaseReference mDatabase;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ImageView ivBasicImage = new ImageView(getActivity());
        Picasso.with(getActivity()).load(R.mipmap.jojo).resize(500, 800).
                centerCrop().into(ivBasicImage);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.title_dialog)
                .setPositiveButton(R.string.valider_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Query test = mDatabase.child("users").orderByChild("mail").equalTo(getMail());

                        test.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot child: dataSnapshot.getChildren()) {

                                    child.getRef().child("inscrit").setValue(true);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                })
                .setNegativeButton(R.string.annuler_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        builder.setView(ivBasicImage);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
