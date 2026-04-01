package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityChoice extends AppCompatActivity implements View.OnClickListener {
Button image, audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        audio= findViewById(R.id.buttonAudio);
        image=  findViewById(R.id.buttonImage);
        audio.setOnClickListener(this);
        image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonAudio:
                Intent intent = new Intent(this, DeveloperAudio.class);
                startActivity(intent);
                break;
            case R.id.buttonImage:
                Intent intent2 = new Intent(this, MainActivityDevelopers.class);
                startActivity(intent2);
                break;
        }
    }
}