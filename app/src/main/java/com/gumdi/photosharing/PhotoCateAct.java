package com.gumdi.photosharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhotoCateAct extends AppCompatActivity {

    RecyclerView myCateList;
    CatesAdapter catesAdapter;
    List<Cates> catesList;
    Button btnsavecate;
    Animation buttom_tp_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_cate);

        btnsavecate=findViewById(R.id.btnsaveusername);
        buttom_tp_top = AnimationUtils.loadAnimation(this,R.anim.bottom_tp_top);

        catesList = new ArrayList<>();
        catesList.add(
                new Cates("juicy", R.drawable.icjuicy)
        );

        catesList.add(
                new Cates("Snorkling", R.drawable.icsnork)
        );

        catesList.add(
                new Cates("Shoping", R.drawable.icshopper)
        );

        catesList.add(
                new Cates("Prize", R.drawable.icprize)
        );

        btnsavecate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhotoCateAct.this, ChoseUsernameAct.class);
                startActivity(intent);

            }
        });

        myCateList= findViewById(R.id.myCateList);
        final LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);

        myCateList.setLayoutManager(linearLayoutManager);
        myCateList.setHasFixedSize(true);

        catesAdapter = new CatesAdapter(this, catesList);
        myCateList.setAdapter(catesAdapter);

        //Snaping From Google

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(myCateList);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 1ms = 100ms
                RecyclerView.ViewHolder viewHolderDefault = myCateList.
                        findViewHolderForAdapterPosition(0);

                ImageView imageViewDefault = viewHolderDefault.itemView.findViewById(R.id.iconimg);
                imageViewDefault.animate().alpha(1).scaleX(1).scaleY(1).setDuration(100).start();

                TextView icontitleDefault = viewHolderDefault.itemView.findViewById(R.id.icontitle);
                icontitleDefault.animate().alpha(1).setDuration(100).start();

                btnsavecate.animate().alpha(1).setDuration(100).start();

            }
        }, 100);

        myCateList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == RecyclerView.SCROLL_STATE_IDLE){

                    View view = snapHelper.findSnapView(linearLayoutManager);
                    int pos = linearLayoutManager.getPosition(view);

                    RecyclerView.ViewHolder viewHolder = myCateList.findViewHolderForAdapterPosition(pos);

                    ImageView imageView = viewHolder.itemView.findViewById(R.id.iconimg);
                    imageView.animate().alpha(1).scaleX(1).scaleY(1).setDuration(100).start();

                    final TextView icontitle = viewHolder.itemView.findViewById(R.id.icontitle);
                    icontitle.animate().alpha(1).setDuration(100).start();

                    btnsavecate.setAlpha(1);
                    btnsavecate.startAnimation(buttom_tp_top);


                } else {

                    View view = snapHelper.findSnapView(linearLayoutManager);
                    int pos = linearLayoutManager.getPosition(view);

                    RecyclerView.ViewHolder viewHolder = myCateList.findViewHolderForAdapterPosition(pos);
                    ImageView imageView = viewHolder.itemView.findViewById(R.id.iconimg);
                    imageView.animate().alpha(0.5f).scaleX(0.5f).scaleY(0.5f).setDuration(100).start();

                    TextView icontitle = viewHolder.itemView.findViewById(R.id.icontitle);
                    icontitle.animate().alpha(0).setDuration(100).start();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                btnsavecate.setAlpha(0);
            }
        });


    }
}
