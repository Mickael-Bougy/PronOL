package com.smin.pronol.liste;
/** CustomList : Classe permettant d'afficher la liste des matchs
 *  dans le premier fragments
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smin.pronol.Match;
import com.smin.pronol.R;
import com.smin.pronol.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomList extends ArrayAdapter<Match> {

    private Activity context;
    private List<Match> matchList;
    private TextView dialog_titleMatch;
    private TextView dialog_tvDom;
    private TextView dialog_tvExt;
    private EditText dialog_scoreDom;
    private EditText dialog_scoreExt;
    private Button dialog_valid;

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
                View dialogView = context.getLayoutInflater().inflate(R.layout.dialog_prono,null);

                //<editor-fold desc=" Récupération des composants du layout">
                dialog_titleMatch = dialogView.findViewById(R.id.dialog_tvMatchName);
                dialog_scoreDom = dialogView.findViewById(R.id.dialog_scoreDom);
                dialog_scoreExt = dialogView.findViewById(R.id.dialog_scoreExt);
                dialog_tvDom = dialogView.findViewById(R.id.dialog_tvDom);
                dialog_tvExt = dialogView.findViewById(R.id.dialog_tvExt);
                dialog_valid = dialogView.findViewById(R.id.dialog_btn_valid);
                //</editor-fold>

                dialog_titleMatch.setText(match.getDomicile() +"/"+ match.getExterieur());
                dialog_tvDom.setText(match.getDomicile());
                dialog_tvExt.setText(match.getExterieur());

                dialog_valid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Date currentTime = Calendar.getInstance().getTime();
                        String date = Utils.convertDateFormat(currentTime);
                        Match m = new Match(date,match.getDomicile(),match.getExterieur(),Integer.parseInt(String.valueOf(dialog_scoreDom.getText())),Integer.parseInt(String.valueOf(dialog_scoreExt.getText())));
                        m.addNewProno();
                        Toast.makeText(context,"Pronostique enregistré", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return convertView;
    }
}