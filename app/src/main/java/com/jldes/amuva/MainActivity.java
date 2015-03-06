package com.jldes.amuva;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private RecyclerView recView;

    private ArrayList<Titular> datos;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private List<Noticia> noticias;
    private AdaptadorTitulares adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = new ArrayList<Titular>();
        recView = (RecyclerView) findViewById(R.id.RecView);
        recView.setHasFixedSize(true);
        adaptador = new AdaptadorTitulares(datos);
        recView.setAdapter(adaptador);


        recView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        //recView.setLayoutManager(new GridLayoutManager(this,3));

//        recView.addItemDecoration(
//                new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        recView.setItemAnimator(new DefaultItemAnimator());
        if (estaConectado()) {
            CargarXmlTask tarea = new CargarXmlTask();
            tarea.execute("http://amuva.es/feed/");
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    private boolean estaConectado() {
        if (conectadoWifi()) {
            return true;
        } else {
            if (conectadoRedMovil()) {
                return true;
            } else {
                showAlertDialog(MainActivity.this, "Conexion a Internet",
                        "Tu Dispositivo no tiene Conexion a Internet.", false);
                return false;
            }
        }
    }

    protected Boolean conectadoWifi() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Boolean conectadoRedMovil() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);

        alertDialog.setMessage(message);


        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Intent intent;
        // update the main content by replacing fragments
        switch (position) {
            case 0:

                break;
            case 1:
                intent = new Intent(MainActivity.this, Robolid.class);
                startActivity(intent);
                finish();
                break;
            case 2:
                intent = new Intent(MainActivity.this, Que_es_AMUVa.class);
                startActivity(intent);
                finish();
                break;
            case 3:
                intent = new Intent(MainActivity.this, Como_LLegar.class);
                startActivity(intent);
                finish();
                break;
            case 4:
                intent = new Intent(MainActivity.this, Contacto.class);
                startActivity(intent);
                finish();
                break;
            case 5:
                Social.share(MainActivity.this,"AMUVa en Google Play", "Ten toda la información sobre AMUVa en tu móvil: https://play.google.com/store/apps/details?id=com.jldes.amuva");
                break;
        }
    }

    public void onSectionAttached(int number) {

        switch (number) {
            case 1:
                Toast.makeText(MainActivity.this, "" + number, Toast.LENGTH_SHORT).show();
                break;
            case 2:

                Toast.makeText(MainActivity.this, "" + number, Toast.LENGTH_SHORT).show();
                break;
            case 3:

                Toast.makeText(MainActivity.this, "" + number, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.title_section1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    private class CargarXmlTask extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... params) {

            RssParserSax2 saxparser =
                    new RssParserSax2(params[0]);

            noticias = saxparser.parse();

            return true;
        }

        protected void onPostExecute(Boolean result) {

            //Tratamos la lista de noticias
            //Por ejemplo: escribimos los títulos en pantalla
            for (Noticia noticia : noticias.subList(0, 6)) {
                Spanned spanned = Html.fromHtml(noticia.getDescripcion(), getImageHTML(), null);
                datos.add(new Titular(noticia.getTitulo(), "" + spanned));
            }
            adaptador = new AdaptadorTitulares(datos);

            recView.setAdapter(adaptador);
            adaptador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, NoticiaCompleta.class);
                    intent.putExtra("Noticia", noticias.get(recView.getChildPosition(v)));
                    startActivity(intent);
                }
            });

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

    @Override
    public void onBackPressed() {
        FragmentManager manager = getFragmentManager();
        new DialogoConfirmacion().show(manager, "alerta");
//        finish();
//        super.onBackPressed();
    }

}
