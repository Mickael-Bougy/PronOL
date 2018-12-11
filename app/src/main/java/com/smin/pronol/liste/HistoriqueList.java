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

public class HistoriqueList extends ArrayAdapter<Match> {

    private Activity context;
    private List<Match> matchList;
    private TextView dialog_titleMatch;
    private TextView dialog_tvDom;
    private TextView dialog_tvExt;
    private EditText dialog_scoreDom;
    private EditText dialog_scoreExt;
    private Button dialog_valid;
    private Button dialog_cancel;

    public HistoriqueList(Activity context, List<Match> matchList)
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
        TextView tvPoint;
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
            convertView = inflater.inflate(R.layout.prono_adapter, null, true);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        if(holder == null){
            holder = new ViewHolder();
            holder.tvScoreDom = convertView.findViewById(R.id.tvScoreDom);
            holder.tvScoreExt = convertView.findViewById(R.id.tvScoreExt);
            holder.tvDate = convertView.findViewById(R.id.tvDate);
            holder.tvDom = convertView.findViewById(R.id.tvDom);
            holder.tvExt = convertView.findViewById(R.id.tvExt);
            holder.tvPoint = convertView.findViewById(R.id.tvPoint);

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
        holder.tvPoint.setText("+3");
        //</editor-fold>


        return convertView;
    }
}