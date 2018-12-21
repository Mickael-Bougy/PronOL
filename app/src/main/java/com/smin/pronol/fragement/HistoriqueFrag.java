package com.smin.pronol.fragement;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smin.pronol.Match;
import com.smin.pronol.R;
import com.smin.pronol.activities.MainTabActivity;
import com.smin.pronol.liste.CustomList;
import com.smin.pronol.liste.HistoriqueList;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueFrag extends Fragment {

    private static final String TAG = "HistoriqueFrag";
    private ListView listViewMatch;
    private List<Match> matchList;
    private List<Integer> indexList;
    private DatabaseReference dbRefListMatch;
    private FirebaseAuth session;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.historique_fragment, container, false);

        listViewMatch = view.findViewById(R.id.list_prono);
        matchList = new ArrayList<Match>();
        indexList = new ArrayList<Integer>();
        dbRefListMatch = FirebaseDatabase.getInstance().getReference();
        session = FirebaseAuth.getInstance();

        return view;
    }

    /**
     * Permet de récupérer la liste des matchs dans la base de données et de l'afficher
     * @param activity : activité où la liste sera affiché
     */
    public void affichageListe (final Activity activity){
        dbRefListMatch.child(session.getUid()).child("pronostique").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                matchList.clear();
                for(DataSnapshot matchSnapshot : dataSnapshot.getChildren())
                {
                    indexList.add(Integer.parseInt(matchSnapshot.getKey()));
                    Match match = (Match) matchSnapshot.getValue(Match.class);
                    // Remplissage de la liste
                    matchList.add(match);
                }
                // Création de l'adapter seulement si la première liste des matchs est récupérer
                if(!(MainTabActivity.matchListBis == null)){
                    HistoriqueList adapter = new HistoriqueList(getActivity(), matchList, indexList);
                    listViewMatch.setAdapter(adapter);
                }

                activity.findViewById(R.id.list_prono).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.progressBar_prono).setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    /**
     *  Fonction permettant d'afficher la liste des pronostic.
     *  Puisque l'affichage se fais uniquement lorsque l'utilisateur arrive sur le fragment,
     *  les points sont automatiquement calculé
     * @param isVisibleToUser : booléen pour savoir si le fragment est actuellement à afficher
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            affichageListe(getActivity());
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
