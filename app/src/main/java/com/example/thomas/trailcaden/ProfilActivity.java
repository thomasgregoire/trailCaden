package com.example.thomas.trailcaden;

import android.*;
import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.thomas.trailcaden.model.Person;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Thomas on 13/02/2018.
 */

public class ProfilActivity extends BaseActivity {
    public static final int GET_FROM_GALLERY = 3;
    public static int PICK_IMAGE_REQUEST = 1;

    private ImageView imageView;
    private EditText nom;
    private EditText prenom;
    private EditText dateNaiss;
    private RadioGroup genre;
    private EditText adresse;
    private EditText ville;
    private EditText cp;
    private EditText club;
    private EditText licence;
    private EditText mail;
    private Uri contentURI = null;
    private String urlImage;
    private String urlDown ;
    private Person person;
    private StorageReference gsReference;

    // Create a storage reference from our app
    static FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    static StorageReference storageRef = firebaseStorage.getReference();

    static StorageReference  userRef;


    private Person p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        urlImage = mFirebaseAuth.getUid()+"/profilPicture.jpg";

        System.out.println(urlDown);

        setContentView(R.layout.activity_profil);

        nom = (EditText) findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        dateNaiss = findViewById(R.id.dateNaiss);
        mail = findViewById(R.id.email);
        adresse = findViewById(R.id.adresse);
        ville = findViewById(R.id.ville);
        cp = findViewById(R.id.cp);
        club = findViewById(R.id.club);
        licence = findViewById(R.id.licence);
        imageView = (ImageView)findViewById(R.id.imageView);

        storageRef.child(urlImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.with(ProfilActivity.this)
                        .load(uri).into(imageView);
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }});

        mDatabase.child("users").orderByChild("uid").equalTo(mFirebaseAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                p = dataSnapshot.getValue(Person.class);

                nom.setText(p.getName());
                prenom.setText(p.getFirstname());
                dateNaiss.setText(p.getDate());
                /*mail.setText(p.getMail());
                adresse.setText(p.getAdresse());
                ville.setText(p.getVille());
                cp.setText(p.getCp());
                club.setText(p.getClub());
                licence.setText(p.getLicence());*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap thumbnail;
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if(urlImage.equals(mFirebaseAuth.getUid()+"/cerfificat.jpg")){
            if (requestCode == 1) {
                if (data != null) {
                    contentURI = data.getData();
                    try {
                        thumbnail= MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        saveImage(thumbnail);
                        saveToFirebase(thumbnail,urlImage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else if (requestCode == 2) {
                thumbnail = (Bitmap) data.getExtras().get("data");

                contentURI = Uri.parse(saveImage(thumbnail).toString());
                saveImage(thumbnail);
                saveToFirebase(thumbnail,urlImage);
            }  else if (requestCode == 3){
                thumbnail = (Bitmap) data.getExtras().get("data");
                saveToFirebase(thumbnail,urlImage);
            }
        }else{
            if (requestCode == 1) {
                if (data != null) {
                    contentURI = data.getData();
                    try {
                        thumbnail= MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        saveImage(thumbnail);
                        cropImage();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else if (requestCode == 2) {
                thumbnail = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(thumbnail);

                contentURI = Uri.parse(saveImage(thumbnail).toString());
                cropImage();
                saveImage(thumbnail);
                saveToFirebase(thumbnail,urlImage);
            }  else if (requestCode == 3){
                thumbnail = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(thumbnail);
                saveToFirebase(thumbnail,urlImage);
            }
        }
    }

    public void pickImage(View view) {
        showPictureDialog();
    }

    public void pickCertif(View view) {
        urlImage = mFirebaseAuth.getUid()+"/cerfificat.jpg";
        showPictureDialog();
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Choix photo");
        String[] pictureDialogItems = {
                "Provenant de la gallerie",
                "Prendre une photo" };
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        choosePhotoFromGallary();
                        break;
                    case 1:
                        takePhotoFromCamera();
                        break;
                }
            }
        });

        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, 1);
        }
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA},1);
        }else{
            startActivityForResult(intent,2);
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + "/trailCaden/");
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return "";
    }


    private void cropImage() {

        try{
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(contentURI,"image/*");

            System.out.println("jhsfj<gwfjkfklqhkl             ====  "+contentURI);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("aspectX", 2);
            intent.putExtra("aspectY", 2);
            //intent.putExtra("scale", true);
            intent.putExtra("scaleUpIfNeeded",true);
            intent.putExtra("return-data", true);


            startActivityForResult(intent,3);
        }
        catch (ActivityNotFoundException ex)
        {

        }

    }


    public void saveToFirebase(Bitmap bitmap, String path){

        System.out.println("SAVETOFIREBASEE");
        userRef = storageRef.child(path);

        // Get the data from an ImageView as bytes

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = userRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast t = Toast.makeText(ProfilActivity.this,"Image sauvegardée ...",Toast.LENGTH_LONG);
                t.show();
            }
        });
    }


    private void getUser() {
        mDatabase.child("users").orderByChild("uid").equalTo(mFirebaseUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                person = dataSnapshot.getValue(Person.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }






}