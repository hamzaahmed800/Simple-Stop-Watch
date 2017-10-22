package com.example.hami.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    int second = 0;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){

            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
        }

        runTimer();
    }

    public void onSaveInstanceState(Bundle b){
        b.putInt("second",second);
        b.putBoolean("running",running);

    }

    public void onStart(View v){

        running = true;
    }

    public void onStop(View v){
        running = false;

    }

    public void onReset(View v){

        second =0;
        running = false;
    }

   public void runTimer(){

       final TextView text = (TextView) findViewById(R.id.mytext);
       final Handler handler = new Handler();
       handler.post(new Runnable() {
           @Override
           public void run() {

               int h = second/3600;
               int min = (second%3600)/60;
               int sec = second%60;

               String time = String.format("%d:%02d:%02d",h,min,sec);
               text.setText(time);

               if(running){
                   second++;
               }

               handler.postDelayed(this,1000);

           }
       });
   }
}
