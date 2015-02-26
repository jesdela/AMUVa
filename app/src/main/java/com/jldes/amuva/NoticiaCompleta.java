package com.jldes.amuva;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;


public class NoticiaCompleta extends ActionBarActivity {
    Noticia noticia;
    private TextView sub_titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_completa);
        noticia = new Noticia();
        noticia = (Noticia)getIntent().getSerializableExtra("Noticia");
        TextView titulo = (TextView)findViewById(R.id.LblTitulo);
        sub_titulo = (TextView)findViewById(R.id.LblSubTitulo);
        titulo.setText(noticia.getTitulo().toString());
        CargaImage tarea = new CargaImage();
        tarea.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_noticia_completa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    private class CargaImage extends AsyncTask<String, Integer, Boolean>{

        private Spanned spanned;

        @Override
        protected Boolean doInBackground(String... params) {
            spanned = Html.fromHtml(noticia.getContenido(), getImageHTML(), null);
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Log.d("Prueba", spanned.toString());
            sub_titulo.setText(spanned.toString());
            super.onPostExecute(aBoolean);
        }

        public Html.ImageGetter getImageHTML() {
            Html.ImageGetter ig = new Html.ImageGetter() {
                public Drawable getDrawable(String source) {
                    try {
                        Drawable d = Drawable.createFromStream(new URL(source).openStream(), "src name");
                        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                        return null;
                    } catch (IOException e) {
                        Log.v("IOException", e.getMessage());
                        return null;
                    }
                }
            };
            return ig;
        }
    }
}
