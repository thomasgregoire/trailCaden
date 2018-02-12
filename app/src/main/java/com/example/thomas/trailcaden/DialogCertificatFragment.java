package com.example.thomas.trailcaden;

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

import com.squareup.picasso.Picasso;

/**
 * Created by Thomas on 12/02/2018.
 */

public class DialogCertificatFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        
        ImageView ivBasicImage = new ImageView(getActivity());
        Picasso.with(getActivity()).load(R.mipmap.jojo).resize(500, 800).into(ivBasicImage);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.title_dialog)
                .setPositiveButton(R.string.valider_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(R.string.annuler_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        builder.setView(ivBasicImage);
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
