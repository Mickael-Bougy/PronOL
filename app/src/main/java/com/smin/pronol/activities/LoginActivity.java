package com.smin.pronol.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.smin.pronol.R;
import com.smin.pronol.Utils;

public class LoginActivity extends AppCompatActivity {

    private Button connect;
    private EditText mail;
    private EditText password;
    private TextView createAccount;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        //<editor-fold desc=" Récupération des éléments de l'IHM">
        connect = findViewById(R.id.btn_connect);
        mail = findViewById(R.id.edit_mail);
        password = findViewById(R.id.edit_password);
        createAccount = findViewById(R.id.textView_createAccount);
        //</editor-fold>

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent ( LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check de la connexion
                if(!Utils.isNetworkAvailable(getApplicationContext())){
                    Utils.showSnackBar(getApplicationContext(),findViewById(R.id.bottomLayout),"No connection");
                }
                else if( !mail.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty())
                {
                    firebaseAuth.signInWithEmailAndPassword(mail.getText().toString().trim(),password.getText().toString().trim())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        // Lancement du menu
                                        startActivity(new Intent(LoginActivity.this, TabbedActivity.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Utils.showSnackBar(LoginActivity.this, findViewById(R.id.bottomLayout), getString(R.string.snack_invalideField));
                                    }
                                }
                            });
                }
                else
                {
                    Utils.showSnackBar(LoginActivity.this, findViewById(R.id.bottomLayout), getString(R.string.snack_emptyField));
                }
            }
        });
    }

    /**
     *  Gestion du bouton retour de l'appareil, quitter l'application
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Are you sure you want to leave Pron'OL ?")
                    .setTitle("Leave Pron'OL")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
