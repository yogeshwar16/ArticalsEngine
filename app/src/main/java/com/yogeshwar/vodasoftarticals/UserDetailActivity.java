package com.yogeshwar.vodasoftarticals;



import android.app.ActionBar;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class UserDetailActivity extends AppCompatActivity {

    TextView userName,userDesig,userCity,userAbout;
    ImageView userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        userName = findViewById(R.id.textname);
        userDesig = findViewById(R.id.textdesig);
        userCity = findViewById(R.id.textcity);
        userAbout = findViewById(R.id.textabout);
        userImage = findViewById(R.id.imguser);


        Intent intent = getIntent();
        String intImage= intent.getStringExtra("userimage");
        String intName = intent.getStringExtra("name");
        String intDesig = intent.getStringExtra("designation");
        String intCity = intent.getStringExtra("city");
        String intAbout = intent.getStringExtra("about");


        Glide.with(getApplicationContext())
                .load(intImage)
                .into(userImage);

        userName.setText(intName);
        userDesig.setText(intDesig);
        userCity.setText(intCity);
        userAbout.setText(intAbout);

        getSupportActionBar().setTitle("Users Profile");


    }
}