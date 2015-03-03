package com.jldes.amuva;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by programador10 on 2/03/15.
 */
public class TabRobolid extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_robolid,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa-Regular.ttf");
        TextView titulo = (TextView)v.findViewById(R.id.titulo);
        titulo.setTypeface(typeface);

        return v;
    }
}
