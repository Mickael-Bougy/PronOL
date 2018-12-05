package com.smin.pronol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class Tab2Fragment extends Fragment {

    private static final String TAG = "Tab2Fragment";
    private Button test;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);
        test = (Button) view.findViewById(R.id.buttonFrag2);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date currentTime = Calendar.getInstance().getTime();
                Match m1 = new Match(String.valueOf(currentTime),"OL","ASSE",5,0);
                System.out.println("USER :" + user.getUid());
               if (user != null) {
                    databaseReference.child(user.getUid()).setValue(m1);
                    Toast.makeText(getActivity(), "Envoie des data", Toast.LENGTH_LONG).show();

                }
                Toast.makeText(getActivity(), "Echec", Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }
}
