package com.smin.pronol;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment {

    private int posListMatch;
    ListView listViewMatch;
    List<Match> matchList;
    DatabaseReference dbRefListMatch;
    private static final String TAG = "Tab1Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_fragment, container, false);

        listViewMatch = view.findViewById(R.id.list);
        matchList = new ArrayList<Match>();
        dbRefListMatch = FirebaseDatabase.getInstance().getReference();

        affichageListe();
        return view;
    }

    /**
     *  Récupération des matchs dans la bdd et remplissage de la listview
     */
    public void affichageListe (){
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
                CustomList adapter = new CustomList(getActivity(), matchList);
                listViewMatch.setAdapter(adapter);

                // Affichage de la liste su l'item contenant le prochain match à venir
                Log.d(TAG, "onDataChange: pos "+ posListMatch);
                listViewMatch.setSelectionFromTop(posListMatch,0);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}
