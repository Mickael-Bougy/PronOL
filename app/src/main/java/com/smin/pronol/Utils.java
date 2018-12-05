package com.smin.pronol;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.smin.pronol.R.color.colorLyonRouge;

public class Utils {
    private static final String TAG = "Utils";
    private static final Utils ourInstance = new Utils();
    private static  int i =0;
    static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {


    }
    public static void  showSnackBar(Context c, View v , String text){
        Snackbar mySnackbar = Snackbar.make(v,
                text, Snackbar.LENGTH_SHORT);
        View sbv = mySnackbar.getView();
        sbv.setBackgroundColor(ContextCompat.getColor(c, colorLyonRouge));
        mySnackbar.show();
    }

    public static boolean isAlreadyPlayed(String dateToTest){

        // Récupération de la date du jour
        Date now = new Date();
        Date matchDate = new Date(dateToTest);

        // TRONQUER LES HEURES
        i++;
        Log.d(TAG, "isAlreadyPlayed: now "+ now);
        Log.d(TAG, "isAlreadyPlayed: match "+ matchDate);
        Log.d(TAG, "isAlreadyPlayed: " + now.after(matchDate) + " for match "+i);

        if(now.after(matchDate))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
