package com.jldes.amuva;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;


public class NoticiaCompleta extends ActionBarActivity {
    Noticia noticia;
    private TextView sub_titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_completa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        noticia = new Noticia();
        noticia = (Noticia)getIntent().getSerializableExtra("Noticia");
        actionBar.setTitle(noticia.getTitulo());
//        TextView titulo = (TextView)findViewById(R.id.LblTitulo);
//        sub_titulo = (TextView)findViewById(R.id.LblSubTitulo);
//        titulo.setText(noticia.getTitulo().toString());
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><html><body>";
        WebView webView = (WebView)findViewById(R.id.webView);
        webView.loadData(header+"<h2>"+noticia.getTitulo()+"</h2>"+noticia.getContenido()+"</body></html>","text/html; charset=UTF-8",null);
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
        if (id == R.id.compartir) {
            Spanned spanned = Html.fromHtml(noticia.getDescripcion(), getImageHTML(), null);
            Social.share(NoticiaCompleta.this,noticia.getTitulo(), spanned.toString().substring(0,90)+" Vía App de @AMUVa_Robolid "+noticia.getLink());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public Html.ImageGetter getImageHTML() {
        Html.ImageGetter ig = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                try {
                    Drawable d = Drawable.createFromStream(new URL(source).openStream(), "src name");
                    d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                    return d;
                } catch (IOException e) {
                    Log.v("IOException", e.getMessage());
                    return null;
                }
            }
        };
        return ig;
    }
}
