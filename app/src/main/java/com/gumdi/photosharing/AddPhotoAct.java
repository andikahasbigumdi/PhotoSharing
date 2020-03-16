package com.gumdi.photosharing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class AddPhotoAct extends AppCompatActivity {

    ImageView photostory, imageviewphoto;
    TextView Reviewtext , textView2, textView3;
    Button btnsavepicture, btnaddpic;

    Integer photomax = 1;
    Uri picturelocation;

    String USER_NAME_STORY = "usernamestory";
    String userNameStoryLocal = "";
    String userNameStoryNew = "";

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        photostory = findViewById(R.id.photostory);
        imageviewphoto = findViewById(R.id.imageviewphoto);

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
        Intent pic = new Intent(Intent.ACTION_PICK);
        pic.setType("Image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photomax);
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
        }
    }
}
