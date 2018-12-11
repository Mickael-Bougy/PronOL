package com.smin.pronol.fragement;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.smin.pronol.liste.CustomList;
import com.smin.pronol.liste.HistoriqueList;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueFrag extends Fragment {

    private static final String TAG = "HistoriqueFrag";
    private ListView listViewMatch;
    private List<Match> matchList;
    private DatabaseReference dbRefListMatch;
    private FirebaseAuth session;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.historique_fragment, container, false);

        listViewMatch = view.findViewById(R.id.list_prono);
        matchList = new ArrayList<Match>();
        dbRefListMatch = FirebaseDatabase.getInstance().getReference();
        session = FirebaseAuth.getInstance();
        affichageListe(getActivity());

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
                    Match match = (Match) matchSnapshot.getValue(Match.class);
                    // Remplissage de la liste
                    matchList.add(match);

                }
                HistoriqueList adapter = new HistoriqueList(getActivity(), matchList);
                listViewMatch.setAdapter(adapter);
                Log.d(TAG, "onDataChange: affichage");
                activity.findViewById(R.id.list_prono).setVisibility(View.VISIBLE);
                Log.d(TAG, "onDataChange: visible");
                activity.findViewById(R.id.progressBar_prono).setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}
