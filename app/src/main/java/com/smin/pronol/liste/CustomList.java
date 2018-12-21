package com.smin.pronol.liste;
/** CustomList : Classe permettant d'afficher la liste des matchs
 *  dans le premier fragments
 */

import android.app.Activity;
import android.app.AlertDialog;
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
    private Button dialog_cancel;

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

        // Création d'un view holder pour recycler les vues et rendre la liste plus fluide
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
        final int matchNumber = position;

        //<editor-fold desc="Affichage des données">
        holder.tvDom.setText(match.getDomicile());
        holder.tvExt.setText(match.getExterieur());
        holder.tvDate.setText(match.getDate());
        holder.tvScoreDom.setText(String.valueOf(match.getScore_domicile()));
        holder.tvScoreExt.setText(String.valueOf(match.getScore_exterieur()));
        //</editor-fold>

        // Affichage d'un Dialog pour renseigner sont pronostic
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Utils.isAlreadyPlayed(match.getDate())){

                    //<editor-fold desc="Création d'un AlertDialog pour saisir le pronostic du match">

                    View dialogView = context.getLayoutInflater().inflate(R.layout.dialog_prono,null);

                    //<editor-fold desc=" Récupération des composants du layout">
                    dialog_titleMatch = dialogView.findViewById(R.id.dialog_tvMatchName);
                    dialog_scoreDom = dialogView.findViewById(R.id.dialog_scoreDom);
                    dialog_scoreExt = dialogView.findViewById(R.id.dialog_scoreExt);
                    dialog_tvDom = dialogView.findViewById(R.id.dialog_tvDom);
                    dialog_tvExt = dialogView.findViewById(R.id.dialog_tvExt);
                    dialog_valid = dialogView.findViewById(R.id.dialog_btn_valid);
                    dialog_cancel = dialogView.findViewById(R.id.dialog_btn_cancel);
                    //</editor-fold>

                    dialog_titleMatch.setText(match.getDomicile() +"/"+ match.getExterieur());
                    dialog_tvDom.setText(match.getDomicile());
                    dialog_tvExt.setText(match.getExterieur());

                    // Création de l'AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(dialogView);
                    final AlertDialog dialog = builder.create();
                    dialog.show();

                    // Insertion des données dans la base de données
                    dialog_valid.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!dialog_scoreDom.getText().toString().isEmpty() && !dialog_scoreExt.getText().toString().isEmpty()) {
                                Date currentTime = Calendar.getInstance().getTime();
                                String date = Utils.convertDateFormat(currentTime);
                                Match m = new Match(date,match.getDomicile(),match.getExterieur(),Integer.parseInt(String.valueOf(dialog_scoreDom.getText())),Integer.parseInt(String.valueOf(dialog_scoreExt.getText())));
                                m.addNewProno(matchNumber);
                                Toast.makeText(context,R.string.prono_register, Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                            else {
                                Toast.makeText(getContext(),R.string.snack_emptyField,Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                    dialog_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    //</editor-fold>
                }
                else {
                    Toast.makeText(context,context.getString(R.string.alreadyPlayed_msg), Toast.LENGTH_LONG).show();
                }
            }
        });

        return convertView;
    }

}