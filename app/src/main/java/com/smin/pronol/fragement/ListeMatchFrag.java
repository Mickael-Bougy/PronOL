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
import com.smin.pronol.activities.MainTabActivity;
import com.smin.pronol.liste.CustomList;
import com.smin.pronol.Match;
import com.smin.pronol.R;
import com.smin.pronol.Utils;

import java.util.ArrayList;
import java.util.List;

public class ListeMatchFrag extends Fragment {

    private int posListMatch;
    private ListView listViewMatch;
    private List<Match> matchList;
    private DatabaseReference dbRefListMatch;
    private FirebaseAuth session;
    public static boolean matchListSet;
    private static final String TAG = "ListeMatchFrag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.liste_match_fragment, container, false);

        listViewMatch = view.findViewById(R.id.list);
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
        posListMatch = 0;
        dbRefListMatch.child("liste_match").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                matchList.clear();
                for(DataSnapshot matchSnapshot : dataSnapshot.getChildren())
                {
                    Match match = (Match) matchSnapshot.getValue(Match.class);
                    // Remplissage de la liste
                    matchList.add(match);
                    // Recherche de l'item contenant le prochain match à venir
                    if(Utils.isAlreadyPlayed(match.getDate())){
                        posListMatch++;
                    }
                }

                MainTabActivity.matchListBis = matchList;
                matchListSet = true;



                CustomList adapter = new CustomList(getActivity(), matchList);
                listViewMatch.setAdapter(adapter);

                // Affichage de la liste sur l'item contenant le prochain match à venir
                Log.d(TAG, "onDataChange: pos "+ posListMatch);
                listViewMatch.setSelectionFromTop(posListMatch,0);
                //
                activity.findViewById(R.id.list).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}
