package com.gumdi.photosharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView ic_addphoto,ic_photo , ic_addpicture ;
    Button pagemenu , pagechat , dotmenu;
    ImageView ic_chat;
    Animation buttom_tp_top,buttom_tp_two,imgbounes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

        buttom_tp_top = AnimationUtils.loadAnimation(this,R.anim.bottom_tp_top);
        buttom_tp_two = AnimationUtils.loadAnimation(this, R.anim.bottom_tp_top_two);
        imgbounes = AnimationUtils.loadAnimation(this,R.anim.imgbounces);

        ic_addphoto = findViewById(R.id.add_photo);
        ic_addphoto.setPaintFlags(ic_addphoto.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        //give an event to next act
        ic_addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perpindah = new Intent(MainActivity.this, PhotoCateAct.class);
                startActivity(perpindah);
            }
        });

        ic_photo = findViewById(R.id.photo);
        ic_addpicture = findViewById(R.id.Add_picture);

        pagemenu = findViewById(R.id.pagephoto);
        pagechat = findViewById(R.id.pagechat);

        ic_chat = findViewById(R.id.ic_chat);
        dotmenu = findViewById(R.id.dotmenu);


        pagechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ic_chat.setBackgroundResource(R.drawable.icchat);
                ic_photo.setText("No One Chat");
                ic_addpicture.setText("Build A circle That Impact Sociality ");

                ic_addphoto.setText("Find a Friends");
                ic_addphoto.setTextColor(Color.parseColor("#DF5F7D"));

                //animation di text saat diklik button pagechat
                ic_chat.startAnimation(imgbounes);
                ic_photo.startAnimation(buttom_tp_top);
                ic_addpicture.startAnimation(buttom_tp_top);
                ic_addphoto.startAnimation(buttom_tp_two);

                //animation di Photo saat diklik button pagechat
                pagemenu.setBackgroundResource(R.drawable.iccamun);
                pagechat.setBackgroundResource(R.drawable.icmsgan);

                pagemenu.animate().scaleY(0.7f).scaleX(0.7f).setDuration(350).start();
                pagechat.animate().scaleY(1).scaleX(1).setDuration(350).start();
                dotmenu.animate().translationX(450).setDuration(350).setStartDelay(100).start();

            }
        });

        pagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               pagemenu.setBackgroundResource(R.drawable.icphotos);
               ic_photo.setText("No Photo");
               ic_addpicture.setText("Add Picture on your circle ");

               ic_addphoto.setText("Add Photo");
               ic_addphoto.setTextColor(Color.parseColor("#706AC9"));

//                animation di text saat diklik button pagechat
                ic_chat.startAnimation(imgbounes);
                ic_photo.startAnimation(buttom_tp_top);
                ic_addpicture.startAnimation(buttom_tp_top);
                ic_addphoto.startAnimation(buttom_tp_two);

                //animation di Photo saat diklik button pagechat
                pagemenu.setBackgroundResource(R.drawable.iccaman);
                pagechat.setBackgroundResource(R.drawable.icmsgun);

                pagechat.animate().scaleY(0.7f).scaleX(0.7f).setDuration(350).start();
                pagemenu.animate().scaleY(1).scaleX(1).setDuration(350).start();
                dotmenu.animate().translationX(0).setDuration(350).setStartDelay(100).start();


            }
        });

        ic_addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perpindahan = new Intent(MainActivity.this,PhotoCateAct.class);
                startActivity(perpindahan);
            }
        });
    }
}
