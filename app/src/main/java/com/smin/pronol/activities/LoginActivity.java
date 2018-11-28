package com.smin.pronol.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.smin.pronol.R;

public class LoginActivity extends AppCompatActivity {

    private Button connect;
    private EditText mail;
    private EditText password;
    private CheckBox remember;
    private TextView createAccount;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        // Récupération des éléments de l'IHM
        connect = findViewById(R.id.btn_connect);
        mail = findViewById(R.id.edit_mail);
        password = findViewById(R.id.edit_password);
        remember = findViewById(R.id.check_remember);
        createAccount = findViewById(R.id.textView_createAccount);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(mail.getText().toString().trim(),password.getText().toString().trim())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,"Registered",Toast.LENGTH_SHORT).show();
                                    // Lancement du menu
                                }
                                else
                                {
                                    password.setText("");
                                    Toast.makeText(LoginActivity.this,"Not Registered",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                // Création d'un compte
               /* firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString().trim(),password.getText().toString().trim())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,"Registered",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,"Not Registered",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/
            }
        });

    }
}
