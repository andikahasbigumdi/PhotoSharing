package com.gumdi.photosharing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import java.security.Permission;

public class AddPhotoAct extends AppCompatActivity {

    ImageView photostory, imageviewphoto;
    TextView Reviewtext , textView2, textView3;
    Button btnsavepicture, btnaddpic;

    Integer photomax = 1;
    Uri picturelocation;


    String USER_NAME_STORY = "usernamestory";
    String userNameStoryLocal = "";
    String userNameStoryNew = "";

    DatabaseReference reference;
    StorageReference storageReferences;


    private static final int PERMISSION_CODE =1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        photostory = findViewById(R.id.photostory);
        imageviewphoto = findViewById(R.id.imageviewphoto);

        LoadDataUsernameLocal();

        Reviewtext = findViewById(R.id.Reviewtext);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        btnaddpic = findViewById(R.id.btnaddpic);
        btnsavepicture = findViewById(R.id.btnsavepicture);

        //menyetel alpha
        btnsavepicture.setAlpha(0);
        //        btnaddpic.setAlpha(0);


        //menambahkan even pada button save picture
        btnsavepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePhotoToFirebase();
            }
        });

        btnaddpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhoto();
            }
        });
    }

    public void findPhoto(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){

                //Permision not granted , request it
                String [] permision = {Manifest.permission.READ_EXTERNAL_STORAGE};

                //show popup for runtime permisionn
                requestPermissions(permision ,PERMISSION_CODE);
            }
            else {
                pickImageFromGalery();
            }
        }
            else {
                pickImageFromGalery();
        }

//        Intent pic = new Intent(Intent.ACTION_PICK);
//        pic.setType("Image/*");
//        pic.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(pic, photomax);
    }

    private void pickImageFromGalery(){
        Intent pic = new Intent(Intent.ACTION_PICK);
        pic.setType("image/*");
        startActivityForResult(pic,photomax);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED) {
                    //permision was wanted
                    pickImageFromGalery();

                }
                else {
                    Toast.makeText(this, "permision Denied ",Toast.LENGTH_SHORT).show();


                }
            }
        }
    }


    String getFileExtension (Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == photomax && resultCode == RESULT_OK && data!= null &&  data.getData() != null){

            imageviewphoto.animate().alpha(0).setDuration(350).start();
            btnaddpic.animate().alpha(0).setDuration(350).start();
            textView2.animate().alpha(0).setDuration(350).start();
            textView3.animate().alpha(0).setDuration(350).start();

            Reviewtext.animate().alpha(1).setDuration(350).start();
            btnsavepicture.animate().alpha(1).setDuration(350);

           picturelocation = data.getData();



        }
    }

    public void LoadDataUsernameLocal(){
        SharedPreferences namesharedpreferences = getSharedPreferences(USER_NAME_STORY , MODE_PRIVATE) ;
        userNameStoryNew = namesharedpreferences.getString(userNameStoryLocal,"");
    }

    public void savePhotoToFirebase(){
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userNameStoryNew);
        storageReferences = FirebaseStorage.getInstance().getReference().child("Users").child(userNameStoryNew);

        if (picturelocation != null) {
            StorageReference storageReference = storageReferences.child(System.currentTimeMillis()+
                    "."+getFileExtension(picturelocation));

//           storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//               @Override
//               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                   String uripicture = StorageReference.getDownloadUrl().toString();
//                   reference.getRef().child("picture").setValue(uripicture);
//               }
//           });

        }
    }

    public void methodenew() {


    }



}
