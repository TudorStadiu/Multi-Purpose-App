package com.example.valentinvasilescu.ceva;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Screen2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        TextView bunvenit = (TextView) findViewById(R.id.mesaj);


        Bundle bundle = getIntent().getExtras();
        String nume = bundle.getString("nume");

        bunvenit.setText(nume);

        Button log = (Button) findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("logged", false);
                editor.commit();
                Intent logac = new Intent(Screen2.this, LoginActivity.class);
                startActivity(logac);
            }
        });

        ImageView g1 = (ImageView) findViewById(R.id.imageView11);
        Picasso.with(this)
                .load(R.drawable.guitar6)
               .into(g1);
        ImageView g2 = (ImageView) findViewById(R.id.imageView12);
        Picasso.with(this)
                .load(R.drawable.guitar6)
                .into(g2);

        ImageView g3 = (ImageView) findViewById(R.id.imageView13);
        Picasso.with(this)
                .load(R.drawable.guitar6)
                .into(g3);
        ImageView g4 = (ImageView) findViewById(R.id.imageView21);
        Picasso.with(this)
                .load(R.drawable.guitar6)
                .into(g4);
        ImageView g5 = (ImageView) findViewById(R.id.imageView22);
        Picasso.with(this)
                .load(R.drawable.guitar6)
                .into(g5);
        ImageView g6 = (ImageView) findViewById(R.id.imageView23);
        Picasso.with(this)
                .load(R.drawable.guitar6)
                .into(g6);
        ImageView g7 = (ImageView) findViewById(R.id.imageView31);
        Picasso.with(this)
                .load(R.drawable.guitar6)
                .into(g7);
        ImageView g8 = (ImageView) findViewById(R.id.imageView32);
        Picasso.with(this)
                .load(R.drawable.guitar6)
                .into(g8);
        ImageView g9 = (ImageView) findViewById(R.id.imageView33);
        Picasso.with(this)
                .load(R.drawable.guitar6)
                .into(g9);

        Button location = (Button) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alocation = new Intent(Screen2.this, LocationActivity.class);
                startActivity(alocation);
            }
        });

        Button imgs = (Button) findViewById(R.id.imageslider);
        imgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen2.this, SliderActivity.class);
                startActivity(intent);
            }
        });
        Button cmr = (Button) findViewById(R.id.camera);
        cmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen2.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        Button wth = (Button) findViewById(R.id.weather);
        wth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen2.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
        Button tb = (Button) findViewById(R.id.tabbed);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen2.this, TActivity.class);
                startActivity(intent);
            }
        });
        Button quizac = (Button) findViewById(R.id.quiz);
        quizac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen2.this, QuizActivity.class);
                startActivity(intent);
            }
        });

//        void loadImage(){
//            Picasso.with(this)
//                    .load(R.drawable.guitar6)
//                    .into(g6);
//        }
//
//        void loadPage(String pageName){
//
//
//        }
//            tb.setOnClickListener(new View.OnClickListener() {
//                                      @Override
//                                      public void onClick(View view) {
//                                          Intent intent = new Intent(Screen2.this, TActivity.class);
//                                          startActivity(intent);
//        }

//        View.OnClickListener abc = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.logout:
//                        break;
//                    case
//                }
//            }
//        };

//        log.setOnClickListener(abc);

    }

        /*
    @Override
    public void onClick(View view) {
        SharedPreferences settings = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("logged", false);
        editor.commit();
    }

    */
    }


