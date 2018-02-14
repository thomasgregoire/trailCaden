package com.example.thomas.trailcaden;

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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Thomas on 13/02/2018.
 */

public class ProfilActivityYvann extends AppCompatActivity {
    public static final int GET_FROM_GALLERY = 3;
    public ImageView imageView = null;
    public ImageView imageView2 = null;
    Uri contentURI = null;

    // Create a storage reference from our app
    static FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    static StorageReference storageRef = firebaseStorage.getReference();

    // Create a reference to "mountains.jpg"
    static StorageReference  userRef = storageRef.child("test.jpg");

    // Create a reference to 'images/mountains.jpg'
    static StorageReference mountainImagesRef = storageRef.child("images/test.jpg");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView);

    }
    public void pickImage(View view) {
        showPictureDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap thumbnail = null;
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                contentURI = data.getData();
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    imageView.setImageBitmap(thumbnail);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(contentURI.toString());
                cropImage();
            }

        } else if (requestCode == 2) {

            thumbnail = (Bitmap) data.getExtras().get("data");

            saveImage(thumbnail);
            contentURI = data.getData();

            System.out.println("URI             " + contentURI);
            contentURI = Uri.fromFile(new File(data.getExtras().get("data").toString()));
            cropImage();

        } else if (requestCode == 3){
            thumbnail = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(thumbnail);
            saveImage(thumbnail);
        }

        avecToFirebase(thumbnail);

    }


    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Choix photo");
        String[] pictureDialogItems = {
                "Provenant de la gallerie",
                "Prendre une photo" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
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
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            startActivityForResult(galleryIntent, 1);

        }


    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},1);
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},1);
            startActivityForResult(intent,2);

        }


    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + "/TrailCaden/");
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
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


    public static void avecToFirebase(Bitmap bitmap){
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
            }
        });
    }



}
