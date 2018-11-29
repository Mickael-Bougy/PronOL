package com.smin.pronol;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import static com.smin.pronol.R.color.colorLyonRouge;

public class Utils {
    private static final Utils ourInstance = new Utils();

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
}
