package com.smin.pronol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tab1Fragment extends Fragment {
    ListView list;
     String[] domicile =
            {
                    "Lille",
                    "Lyon",
                    "Lyon"
            };




    private static final String TAG = "Tab1Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);

        CustomList listAdapter = new CustomList(getActivity(),  domicile);
        System.out.println("Affichage de la liste");
        list = view.findViewById(R.id.list);
        list.setAdapter(listAdapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), "You clicked at " + domicile[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        return inflater.inflate(R.layout.tab1_fragment, container, false);
    }
}
