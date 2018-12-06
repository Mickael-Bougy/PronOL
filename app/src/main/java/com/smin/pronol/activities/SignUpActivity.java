package com.smin.pronol.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.smin.pronol.R;
import com.smin.pronol.Utils;

public class SignUpActivity extends AppCompatActivity {
    private EditText mail;
    private EditText password;
    private Button signUp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Récupération des éléments de l'IHM
        mail = findViewById(R.id.edit_mail);
        password = findViewById(R.id.edit_password);
        signUp = findViewById(R.id.btn_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        if(!Utils.isNetworkAvailable(getApplicationContext())){
            signUp.setEnabled(false);
            Toast.makeText(getApplicationContext(),"Please define an internet connection",Toast.LENGTH_LONG);
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Création d'un compte
                if(!mail.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
                {
                    firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString().trim(),password.getText().toString().trim())
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        // Lancement du menu
                                        startActivity(new Intent(SignUpActivity.this, MainTabActivity.class));
                                    }
                                    else {
                                        Utils.showSnackBar(SignUpActivity.this, findViewById(R.id.bottomLayout), getString(R.string.snack_invalideField));

                                    }
                                }
                            });
                }
                else
                {
                    Utils.showSnackBar(SignUpActivity.this, findViewById(R.id.bottomLayout), getString(R.string.snack_emptyField));
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
