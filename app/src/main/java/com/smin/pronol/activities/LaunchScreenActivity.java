package com.smin.pronol.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smin.pronol.R;
import com.smin.pronol.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class LaunchScreenActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private Timer delayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        delayer = new Timer();

        // Pour rester au moins 2s sur le launchscreen
        delayer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Check de la connexion internet
                if (Utils.isNetworkAvailable(getApplicationContext()))
                {
                    firebaseAuth = FirebaseAuth.getInstance();
                    user = firebaseAuth.getCurrentUser();

                    if( user == null ){
                        startActivity(new Intent(LaunchScreenActivity.this, LoginActivity.class));
                    }
                    else{
                        startActivity(new Intent(LaunchScreenActivity.this, TabbedActivity.class));
                    }
                }
                else{
                    startActivity(new Intent(LaunchScreenActivity.this, LoginActivity.class));
                }
                finish();
            }
        },2000);
    }
}
