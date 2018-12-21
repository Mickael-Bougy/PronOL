package com.smin.pronol.liste;
/** CustomList : Classe permettant d'afficher la liste des matchs
 *  dans le premier fragments
 */

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.smin.pronol.Match;
import com.smin.pronol.R;
import com.smin.pronol.Utils;
import com.smin.pronol.activities.MainTabActivity;
import com.smin.pronol.fragement.ListeMatchFrag;

import java.util.List;

public class HistoriqueList extends ArrayAdapter<Match> {
    private  final static  String TAG ="HistoriqueList";
    private Activity context;
    private List<Match> matchList;
    private List<Integer> indexList;


    public HistoriqueList(Activity context, List<Match> matchList,  List<Integer> indexList)
    {
        super(context, R.layout.row_adapter, matchList);
        this.context = context;
        this.matchList = matchList;
        this.indexList = indexList;
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
        int point;
        if (ListeMatchFrag.matchListSet){
             point = Utils.getPointMatch(MainTabActivity.matchListBis.get(indexList.get(position)),match);
        }
        else{
            //int point = Utils.getPointMatch(matchListBis.get(indexList.get(position)),match);
             point = Utils.getPointMatch(match,match);
        }


        //<editor-fold desc="Affichage des données">
        holder.tvDom.setText(match.getDomicile());
        holder.tvExt.setText(match.getExterieur());
        holder.tvDate.setText(match.getDate());
        holder.tvScoreDom.setText(String.valueOf(match.getScore_domicile()));
        holder.tvScoreExt.setText(String.valueOf(match.getScore_exterieur()));

        if (point != -1){
            holder.tvPoint.setText(String.valueOf(point));
            // Coloration du background selon le nombre de point gagnés
            switch (point) {
                case 0 : convertView.setBackgroundColor(convertView.getResources().getColor(R.color.colorLyonRouge));
                    break;
                case 1 : convertView.setBackgroundColor(convertView.getResources().getColor(R.color.goodWinner));
                    break;
                case 3 : convertView.setBackgroundColor(convertView.getResources().getColor(R.color.perfectScore));
                    break;
            }

        }


        //</editor-fold>

        return convertView;
    }

}