package com.example.rpl2016_11.movie.users;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.rpl2016_11.movie.MainActivity;
import com.example.rpl2016_11.movie.R;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences("users", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (sp != null && !sp.contains("activity_signup")){
            editor.putBoolean("activity_signup", false);
            editor.apply();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    sleep(2000);
                    if (login()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private boolean login(){
        return sp.getBoolean("activity_signup", true);
    }
}
