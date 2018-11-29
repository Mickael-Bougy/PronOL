package com.smin.pronol;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {

    private final Context context;
    private final String[] domicile;


    public CustomList(Context context, String[] domicile)
    {
        super(context, R.layout.row_adapter, domicile);
        this.context = context;
        this.domicile = domicile;
    }

    @Override
    public View getView(int position,  View View,  ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.row_adapter, parent, false);
        TextView txtDom = (TextView) rowView.findViewById(R.id.tvdom);

        txtDom.setText(domicile[position]);



        return rowView;
    }
}
