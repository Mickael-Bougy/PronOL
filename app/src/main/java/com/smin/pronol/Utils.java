package com.smin.pronol;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    private Utils() {}

    /**
     *  Fais apparaitre une snackbar d'erreur
     * @param c : context de l'application
     * @param v : vue où sera affiché la snackbar
     * @param text : texte à écrire
     */
    public static void  showSnackBar(Context c, View v , String text){
        Snackbar mySnackbar = Snackbar.make(v,
                text, Snackbar.LENGTH_SHORT);
        View sbv = mySnackbar.getView();
        sbv.setBackgroundColor(ContextCompat.getColor(c, colorLyonRouge));
        mySnackbar.show();
    }

    /**
     * Recherche si le match à déjà été joué ou non
     * @param dateToTest
     * @return : true si le match est passé, false si non
     */
    public static boolean isAlreadyPlayed(String dateToTest){

        Date matchDate = new Date(dateToTest);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //Récupération et formatage de la date système
        String current = dateFormat.format(new Date());
        Date now = new Date(current);

        /*i++;
        Log.d(TAG, "isAlreadyPlayed: now "+ now);
        Log.d(TAG, "isAlreadyPlayed: match "+ matchDate);
        Log.d(TAG, "isAlreadyPlayed: " + now.after(matchDate) + " for match "+i);*/

        if(now.after(matchDate))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
