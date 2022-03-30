package com.example.reaction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dflt = findViewById(R.id.dflt);
        Button hard = findViewById(R.id.hard);

        dflt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeactivity1();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeactivity2();
            }
        });

    }

    public void changeactivity1(){
        Intent intent = new Intent(this, slowreaction.class);
        startActivity(intent);
    }

    public void changeactivity2(){
        Intent intent = new Intent(this, strongactivity.class);
        startActivity(intent);
    }
}