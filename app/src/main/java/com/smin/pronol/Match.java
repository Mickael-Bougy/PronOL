package com.smin.pronol;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class Match {

    private String Date;
    private String Domicile;
    private String Exterieur;
    private int Score_domicile;
    private int Score_exterieur;

    public Match(){

    }

    public Match(String Date, String Domicile, String Exterieur, int Score_domicile, int Score_exterieur) {
        this.Date = Date;
        this.Domicile = Domicile;
        this.Exterieur = Exterieur;
        this.Score_domicile = Score_domicile;
        this.Score_exterieur = Score_exterieur;
    }

    public String getDate() {
        return Date;
    }

    public String getDomicile() {
        return Domicile;
    }

    public String getExterieur() {
        return Exterieur;
    }

    public int getScore_domicile() {
        return Score_domicile;
    }

    public int getScore_exterieur() {
        return Score_exterieur;
    }

    public void addNewProno(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        if (user != null) {
            databaseReference.child(user.getUid()).child("pronostique").setValue(this);
        }
    }
}