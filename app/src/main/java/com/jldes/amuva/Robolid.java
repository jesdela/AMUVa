package com.jldes.amuva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class Robolid extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    Toolbar toolbar;
    private CharSequence mTitle;
    private TabRobolid tabRobolid;
    private TabCompeticiones tabCompeticiones;
    private TabPatrocinadores tabPatrocinadores;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    SlidingTabLayout slidingTabLayout;
    CharSequence Titles[]={"Robolid","Categorías","Patrocinadores","Clasificación"};
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robolid);
        actionBar = getSupportActionBar();
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),Titles,Titles.length);
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);
        slidingTabLayout =(SlidingTabLayout)findViewById(R.id.tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer(){
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        slidingTabLayout.setViewPager(viewPager);





        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

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
                intent = new Intent(Robolid.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case 1:

                break;
            case 2:
                intent = new Intent(Robolid.this, Que_es_AMUVa.class);
                startActivity(intent);
                finish();
                break;
            case 3:
                intent= new Intent(Robolid.this, Como_LLegar.class);
                startActivity(intent);
                finish();
                break;
            case 4:
                intent = new Intent(Robolid.this, Contacto.class);
                startActivity(intent);
                finish();
                break;
            case 5:
                Social.share(Robolid.this,"AMUVa en Google Play", "Ten toda la información sobre AMUVa en tu móvil: https://play.google.com/store/apps/details?id=com.jldes.amuva");
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
        actionBar.setTitle(R.string.title_activity_robolid);
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
