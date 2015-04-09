package com.jldes.amuva;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JesúsDiego on 03/03/2015.
 */
public class TabClasificacion extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clasificaciones,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa-Regular.ttf");
        TextView titulo = (TextView)v.findViewById(R.id.titulo_clasi);
        titulo.setTypeface(typeface);
        return v;
    }
}