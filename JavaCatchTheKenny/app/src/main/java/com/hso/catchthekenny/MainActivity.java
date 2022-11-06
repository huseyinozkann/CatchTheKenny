package com.hso.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
     TextView scoreText;
     TextView timeText;
     ImageView imageView;
     ImageView imageView2;
     ImageView imageView3;
     ImageView imageView4;
     ImageView imageView5;
     ImageView imageView6;
     ImageView imageView7;
     ImageView imageView8;
     ImageView imageView9;
     ImageView [] imageArray;
     int score;
     Handler handler;
     Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.textViewScore);
        timeText = findViewById(R.id.textViewTime);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        score = 0;

        hideImages();

        new CountDownTimer(20000,650){

            @Override
            public void onTick(long l) {
                timeText.setText("TIME:" + l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("TIME OFF !");
                Toast.makeText(MainActivity.this,"SCORE:" + score,Toast.LENGTH_LONG).show();
                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("RESTART");
                alert.setMessage("ARE YOU SURE ?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"GAME OVER",Toast.LENGTH_LONG).show();

                    }
                });

                alert.show();
            }
        }.start();
    }

    public void increaseScore(View view){
          score++;
          scoreText.setText("SCORE:" + score);
    }

    public void hideImages(){
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,650);
            }
        };

        handler.post(runnable);

    }
}