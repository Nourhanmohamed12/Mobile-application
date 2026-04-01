package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer myPlayer;

    public MyService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override  public void onCreate() {
        myPlayer= MediaPlayer.create(this, R.raw.sun);
        myPlayer.setLooping(false); // Set looping
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (myPlayer.isPlaying()){
            myPlayer.pause();}
        else{
            myPlayer.start();
        }
        return START_STICKY;
    }

    @Override  public void onDestroy( ) {
        myPlayer.stop( );
    }
}