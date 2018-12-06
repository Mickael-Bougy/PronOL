package com.smin.pronol;
/** CustomList : Classe permettant d'afficher la liste des matchs
 *  dans le premier fragments
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smin.pronol.activities.LoginActivity;
import com.smin.pronol.activities.TabbedActivity;

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

    public static class ViewHolder {
        TextView tvDom;
        TextView tvExt;
        TextView tvDate;
        TextView tvScoreDom;
        TextView tvScoreExt;
    }
    /**
     *  Fonction d'affichage des données récupérées via la base de donnée
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent)
    {

        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.row_adapter, null, true);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        if(holder == null){
            holder = new ViewHolder();
            holder.tvScoreDom = convertView.findViewById(R.id.tvScoreDom);
            holder.tvScoreExt = convertView.findViewById(R.id.tvScoreExt);
            holder.tvDate = convertView.findViewById(R.id.tvDate);
            holder.tvDom = convertView.findViewById(R.id.tvDom);
            holder.tvExt = convertView.findViewById(R.id.tvExt);
            convertView.setTag(holder);
        }

        // Récupération des informations d'un match contenu dans la base de données
        final Match match = matchList.get(position);

        //<editor-fold desc="Affichage des données">
        holder.tvDom.setText(match.getDomicile());
        holder.tvExt.setText(match.getExterieur());
        holder.tvDate.setText(match.getDate());
        holder.tvScoreDom.setText(String.valueOf(match.getScore_domicile()));
        holder.tvScoreExt.setText(String.valueOf(match.getScore_exterieur()));
        //</editor-fold>

        //TODO rowView.OnClickListener : CREATION DU LAYOUT POUR L'AFFICHAGE
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure you want to disconnect ?")
                        .setTitle(match.getDomicile()+"/"+match.getExterieur())
                        .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return convertView;
    }
}