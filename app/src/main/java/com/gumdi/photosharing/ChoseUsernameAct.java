package com.gumdi.photosharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.chooser.ChooserTargetService;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChoseUsernameAct extends AppCompatActivity {

    EditText userNameStory;
    Button buttonsaveusername;
    DatabaseReference reference;

    String USER_NAME_STORY = "usernamestory";
    String userNameStoryLocal = "";
    String userNameStoryNew = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_username);


        loadDataUsernameLocal();
        checkUsernameLocal();

        userNameStory = findViewById(R.id.userNameStory);
        buttonsaveusername = findViewById(R.id.btnsaveusername);


        buttonsaveusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    reference = FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(userNameStory.getText().toString());

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //Save Username
                            reference.getRef().child("username").setValue(userNameStory.getText().toString());

                            // save username to the local storage to keep as a key whenever we get the data from Firebase
                            SharedPreferences sharedPreferences = getSharedPreferences(USER_NAME_STORY, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(userNameStoryLocal,userNameStory.getText().toString());
                            editor.apply();

                            //Memberikan notice
                            Toast.makeText(getApplicationContext(), "Username Disimpan ",Toast.LENGTH_SHORT).show();


                            //Memberikan Loncat Kedalam Activity Lainya
                            Intent intent = new Intent(ChoseUsernameAct.this,AddPhotoAct.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            }
        });
    }

    public void loadDataUsernameLocal() {
            SharedPreferences sharedPreferences= getSharedPreferences(USER_NAME_STORY,MODE_PRIVATE);
            userNameStoryNew = sharedPreferences.getString(userNameStoryLocal,"");


    }

    private void checkUsernameLocal(){
     if (userNameStoryNew.isEmpty()) {

     }
     else {
         Intent pindah = new Intent(ChoseUsernameAct.this , AddPhotoAct.class);
         startActivity(pindah);

     }
    }

}
