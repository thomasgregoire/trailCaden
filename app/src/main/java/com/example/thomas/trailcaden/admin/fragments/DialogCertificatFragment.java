package com.example.thomas.trailcaden.admin.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomas.trailcaden.ProfilActivity;
import com.example.thomas.trailcaden.R;
import com.example.thomas.trailcaden.admin.AdminActivity;
import com.example.thomas.trailcaden.model.Person;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Map;

/**
 * Created by Thomas on 12/02/2018.
 */

public class DialogCertificatFragment extends DialogFragment {

    private String mail;

    private DatabaseReference mDatabase;

    static FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    static StorageReference storageRef = firebaseStorage.getReference();

    private String urlImage = "";
    private String message = "";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final ImageView ivBasicImage = new ImageView(getActivity());

        final Query test = mDatabase.child("users").orderByChild("mail").equalTo(getMail());

        test.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, Object> user = (Map<String, Object>) child.getValue();
                    urlImage = user.get("uid") + "/cerfificat.jpg";
                }
                if (!urlImage.equals(null) && !urlImage.equals("")){
                    storageRef.child(urlImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.with(getActivity()).load(uri).resize(700, 1000).
                                    centerCrop().into(ivBasicImage);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getActivity(), "Pas de certificat renseigné", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        builder.setTitle(R.string.title_dialog)
                .setPositiveButton(R.string.valider_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        test.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {

                                    child.getRef().child("inscrit").setValue(true);

                                    FragmentManager fm = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    if (fm.findFragmentById(R.id.adminll) != null) {
                                        fragmentTransaction.remove(fm.findFragmentById(R.id.adminll));
                                    }
                                    Fragment fragInscrit = new ListPreInscritFragment();
                                    fragmentTransaction.add(R.id.adminll, fragInscrit);
                                    fragmentTransaction.commit();
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
