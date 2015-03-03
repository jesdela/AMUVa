package com.jldes.amuva;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by programador10 on 2/03/15.
 */
public class TabPatrocinadores extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patrocinadores,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa-Regular.ttf");
        TextView titulo = (TextView)v.findViewById(R.id.titulo_patr);
        titulo.setTypeface(typeface);
        ImageView imageView1 = (ImageView)v.findViewById(R.id.imageView16);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://robolid.amuva.es/documentos/Dossier_Robolid_2015.pdf")));
            }
        });
        return v;
    }
}
