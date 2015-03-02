package com.jldes.amuva;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import java.util.Locale;


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
    private SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
    public TabRobolid tabRobolid;
    public TabCompeticiones tabCompeticiones;
    public TabPatrocinadores tabPatrocinadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
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
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://amuva.es/feed/");

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

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
        actionBar.setTitle(R.string.app_name);
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
            for (Noticia noticia : noticias.subList(0,6)) {
                Spanned spanned = Html.fromHtml(noticia.getDescripcion(), getImageHTML(), null);
                datos.add(new Titular(noticia.getTitulo(), "" + spanned));
            }
            adaptador = new AdaptadorTitulares(datos);

            recView.setAdapter(adaptador);
            adaptador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,NoticiaCompleta.class);
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
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    tabRobolid = new TabRobolid();
                    return tabRobolid;
                case 1:
                    tabCompeticiones = new TabCompeticiones();
                    return tabCompeticiones;
                default:
                    tabPatrocinadores = new TabPatrocinadores();
                    return tabPatrocinadores;
            }
        }

        // @Override
        // public Fragment getItem(int position) {
        // // getItem is called to instantiate the fragment for the given page.
        // // Return a DummySectionFragment (defined as a static inner class
        // // below) with the page number as its lone argument.
        // Fragment fragment = new DummySectionFragment();
        // Bundle args = new Bundle();
        // args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
        // fragment.setArguments(args);
        // return fragment;
        // }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 1:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 0:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }

    }
}
