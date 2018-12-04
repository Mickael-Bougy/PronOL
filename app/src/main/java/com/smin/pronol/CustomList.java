package com.smin.pronol;
/*
* Cette classe est l'adapter de la list
* fait le lien avec le fichier row_adapter.xml
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
     *  Fonction d'affichage des données recuperees via la base de donnée
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

        TextView txtDom = (TextView) rowView.findViewById(R.id.tvDom);
        TextView txtExt = (TextView) rowView.findViewById(R.id.tvExt);
        TextView txtDate = (TextView) rowView.findViewById(R.id.tvDate);
        EditText scoreDom = (EditText) rowView.findViewById(R.id.editScoreDom);
        EditText scoreExt = (EditText) rowView.findViewById(R.id.editScoreExt);

        Match match = matchList.get(position);

        txtDom.setText(match.getDomicile());
        txtExt.setText(match.getExterieur());
        txtDate.setText(match.getDate());
        scoreDom.setText(String.valueOf(match.getScore_domicile()));
        scoreExt.setText(String.valueOf(match.getScore_exterieur()));


        return rowView;
    }
}
