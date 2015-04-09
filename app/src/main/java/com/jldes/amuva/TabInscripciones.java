package com.jldes.amuva;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TabInscripciones extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_inscripciones,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"Comfortaa-Regular.ttf");
        TextView titulo = (TextView)v.findViewById(R.id.titulo_inscr);
        titulo.setTypeface(typeface);
        Button inscribir = (Button)v.findViewById(R.id.inscribir);
        inscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://robolid.amuva.es/inscripcion.php")));
            }
        });
        return v;
    }

}
