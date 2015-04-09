package com.jldes.amuva;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class Contacto extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Mail enviarMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        enviarMail = new Mail(this);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        Button enviar = (Button)findViewById(R.id.enviar);
        LinearLayout facebook = (LinearLayout)findViewById(R.id.facebook);
        LinearLayout twitter = (LinearLayout)findViewById(R.id.twitter);
        final String[] datos =
                new String[]{"General","Robolid","Talleres Internos","Talleres Externos"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        R.layout.list_item, datos);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adaptador);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/AMUVa")));
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/AMUVa_Robolid")));
            }
        });
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMail.enviarMail();
            }
        });

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
                intent = new Intent(Contacto.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case 1:
                intent = new Intent(Contacto.this, Robolid.class);
                startActivity(intent);
                finish();
                break;
            case 2:
                intent= new Intent(Contacto.this, Que_es_AMUVa.class);
                startActivity(intent);
                finish();
                break;
            case 3:
                intent= new Intent(Contacto.this, Como_LLegar.class);
                startActivity(intent);
                finish();
                break;
            case 4:
                break;
            case 5:
                Social.share(Contacto.this,"AMUVa en Google Play", "Ten toda la información sobre AMUVa en tu móvil: https://play.google.com/store/apps/details?id=com.jldes.amuva");
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.title_activity_contacto);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        android.app.FragmentManager manager = getFragmentManager();
        new DialogoConfirmacion().show(manager,"alerta");
//        finish();
//        super.onBackPressed();
    }

}
