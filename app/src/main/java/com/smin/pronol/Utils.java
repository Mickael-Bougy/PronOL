package com.smin.pronol;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.smin.pronol.R.color.colorLyonRouge;

public class Utils {
    private static final String TAG = "Utils";
    private static final Utils ourInstance = new Utils();
    static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {}

    /**
     *  Fais apparaitre une snackbar d'erreur
     * @param c : context de l'application
     * @param v : vue où sera affichée la snackbar
     * @param text : texte à écrire
     */
    public static void  showSnackBar(Context c, View v , String text){
        Snackbar mySnackbar = Snackbar.make(v, text, Snackbar.LENGTH_SHORT);
        View sbv = mySnackbar.getView();
        sbv.setBackgroundColor(ContextCompat.getColor(c, colorLyonRouge));
        mySnackbar.show();
    }

    /**
     * Recherche si le match à déjà été joué ou non
     * @param dateToTest : date à vérifier
     * @return : vrai si le match est passé sinon faux
     */
    public static boolean isAlreadyPlayed(String dateToTest){
        String current = convertDateFormat(new Date());
        Date now = new Date(current);
        Date matchDate = new Date(dateToTest);

        if(now.after(matchDate))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Vérifie la connectivité internet
     * @param context : context pour récupérer les informations
     * @return : vrai si une connexion internet est diponible sinon faux
     */

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    /**
     *  Permet de convertir un objet date en une String formatté
     * @param date : objet à convertir
     * @return : string convertit
     */

    public static String convertDateFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateFormatted = dateFormat.format(date);

        return dateFormatted;
    }

    /**
     * Calcul le nombre de point gagné selon le pronostic
     * @param match : match joué
     * @param prono : match pronostiqué
     * @return : 0 si perdu, 1 si bon vainqueur, 3 si score exact
     */

    public static int calculPointMatch(Match match, Match prono){
        int nbPoint = 0;
        if(isGoodWinner(match,prono)){
            nbPoint = 1;
        }
        if(isExactScore(match,prono)){
            nbPoint = 3;
        }

        return  nbPoint;
    }

    /**
     *  Vérifie si le pronostic est parfaitement juste
     * @param match : match issu de la liste des matchs
     * @param prono : pronostic utilisateur
     * @return Vrai si le pronostic est bon sinon faux
     */
    private static boolean isExactScore(Match match, Match prono){
        if(match.getScore_domicile() ==  prono.getScore_domicile() && match.getScore_exterieur() == prono.getScore_exterieur()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *  Vérifie si l'utilisateur a pronostiqué le bon vainqueur
     * @param match : match issu de la liste des matchs
     * @param prono : pronostic utilisateur
     * @return : Vrai si le pronostic est bon sinon faux
     */
    private static boolean isGoodWinner(Match match, Match prono){
        boolean domWin = false;
        if( prono.getScore_domicile() > match.getScore_exterieur()){
            domWin = true;
        }
        else {
            domWin = false;
        }
        if(domWin){
            if(match.getScore_domicile() > match.getScore_exterieur()){
                return  true;
            }
            else {
                return false;
            }
        }
        else {
            if(match.getScore_domicile() < match.getScore_exterieur()){
                return  true;
            }
            else {
                return false;
            }
        }
    }
}
