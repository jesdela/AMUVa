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
public class TabCompeticiones extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_categorias,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa-Regular.ttf");
        TextView titulo = (TextView)v.findViewById(R.id.titulo_cat);
        titulo.setTypeface(typeface);
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView5);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://robolid.amuva.es/documentos/Normativa_Rastreadores.pdf")));
            }
        });
        ImageView imageView1 = (ImageView)v.findViewById(R.id.imageView7);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://robolid.amuva.es/documentos/Normativa_Velocistas.pdf")));
            }
        });
        ImageView imageView2 = (ImageView)v.findViewById(R.id.imageView8);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://robolid.amuva.es/documentos/Normativa_Sumo.pdf")));
            }
        });
        ImageView imageView3 = (ImageView)v.findViewById(R.id.imageView9);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://robolid.amuva.es/documentos/Normativa_Minisumo.pdf")));
            }
        });
        return v;
    }
}
