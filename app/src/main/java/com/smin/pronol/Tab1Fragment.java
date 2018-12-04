package com.smin.pronol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment {
    private EditText scoreDom;
    ListView listViewMatch;
    List<Match> matchList;

    DatabaseReference databaseEquipe;


    private static final String TAG = "Tab1Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_fragment, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scoreDom.clearFocus();
                return true;
            }
        });

        CustomList listAdapter = new CustomList(getActivity(), matchList);
        System.out.println("Affichage de la liste");

        listViewMatch = view.findViewById(R.id.list);
        matchList = new ArrayList<Match>();
        databaseEquipe = FirebaseDatabase.getInstance().getReference();

        //list.setAdapter(listAdapter);

       /* listViewMatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getContext(), "You clicked at " + domicile[+ position], Toast.LENGTH_SHORT).show();

            }
        });*/
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseEquipe.child("liste_match").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                matchList.clear();
                for(DataSnapshot matchSnapshot : dataSnapshot.getChildren())
                {

                   Match match = (Match) matchSnapshot.getValue(Match.class);
                    matchList.add(match);
                }

                CustomList adapter = new CustomList(getActivity(), matchList);
                listViewMatch.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }




}





