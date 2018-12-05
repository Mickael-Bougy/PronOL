package com.smin.pronol.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smin.pronol.R;

import java.util.Timer;
import java.util.TimerTask;

public class LaunchScreenActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        System.out.println("USER :" + user);

        Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if( user == null){
                    startActivity(new Intent(LaunchScreenActivity.this, LoginActivity.class));
                }
                else{
                    startActivity(new Intent(LaunchScreenActivity.this, TabbedActivity.class));
                }
                finish();
            }
        },2000);



    }
}
