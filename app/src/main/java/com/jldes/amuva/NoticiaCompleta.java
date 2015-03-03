package com.jldes.amuva;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;


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
//        getMenuInflater().inflate(R.menu.menu_noticia_completa, menu);
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

}
