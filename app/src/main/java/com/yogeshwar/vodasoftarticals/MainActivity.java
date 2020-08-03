package com.yogeshwar.vodasoftarticals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yogeshwar.vodasoftarticals.articalsPack.ArticalsActivity;
import com.yogeshwar.vodasoftarticals.usersPack.UsersActivity;

public class MainActivity extends AppCompatActivity {

    Button btArticals,btUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btArticals = findViewById(R.id.butartical);
        btUsers = findViewById(R.id.butuser);

        btArticals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent calArtical = new Intent(MainActivity.this, ArticalsActivity.class);
                startActivity(calArtical);
            }
        });


        btUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calArtical = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(calArtical);
            }
        });
    }
}