package com.smin.pronol;
/** CustomList : Classe permettant d'afficher la liste des matchs
 *  dans le premier fragments
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class CustomList extends ArrayAdapter<Match> {

    private Activity context;
    private List<Match> matchList;

    public CustomList(Activity context, List<Match> matchList)
    {
        super(context, R.layout.row_adapter, matchList);
        this.context = context;
        this.matchList = matchList;
    }

    /**
     *  Fonction d'affichage des données récupérées via la base de donnée
     *
     * @param position
     * @param View
     * @param parent
     * @return
     */
    @Override
    public View getView(int position,  View View,  ViewGroup parent)
    {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row_adapter, null, true);

        //<editor-fold desc="Récupération des éléments IHM">
        TextView txtDom = (TextView) rowView.findViewById(R.id.tvDom);
        TextView txtExt = (TextView) rowView.findViewById(R.id.tvExt);
        TextView txtDate = (TextView) rowView.findViewById(R.id.tvDate);
        TextView scoreDom =  rowView.findViewById(R.id.tvScoreDom);
        TextView scoreExt =  rowView.findViewById(R.id.tvScoreExt);
        //</editor-fold>

        // Récupération des informations d'un match contenu dans la base de données
        Match match = matchList.get(position);

        //<editor-fold desc="Affichage des données">
        txtDom.setText(match.getDomicile());
        txtExt.setText(match.getExterieur());
        txtDate.setText(match.getDate());
        scoreDom.setText(String.valueOf(match.getScore_domicile()));
        scoreExt.setText(String.valueOf(match.getScore_exterieur()));
        //</editor-fold>

        return rowView;
    }
}