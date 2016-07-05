package com.example.admin.blueui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class ActivityControl extends ActionBarActivity implements ActionBar.TabListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        final List<Fragment> fragments = new Vector<>();

        try
        {
            fragments.add(Fragment.instantiate(this,FragmentControlDimmers.class.getName()));
            fragments.add(Fragment.instantiate(this,FragmentControlSwitches.class.getName()));
            fragments.add(Fragment.instantiate(this,FragmentControlTime.class.getName()));

            PagerAdapterControl pagerAdapterControl = new PagerAdapterControl(getSupportFragmentManager(),fragments);
            final ViewPager viewPager = (ViewPager) findViewById(R.id.vwPagerControl);
            viewPager.setAdapter(pagerAdapterControl);


            android.support.v7.app.ActionBar.TabListener tabListener = new android.support.v7.app.ActionBar.TabListener(){

            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
            };
            final android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            //ab.setDisplayHomeAsUpEnabled(true);
            //ab.setHomeButtonEnabled(true);
            ab.setTitle("tabs");
            ab.addTab(getSupportActionBar().newTab().setText("tab1").setTabListener(tabListener));
            ab.addTab(getSupportActionBar().newTab().setText("tab2").setTabListener(tabListener));
            ab.addTab(getSupportActionBar().newTab().setText("tab3").setTabListener(tabListener));

            viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                public void onPageSelected(int position)
                {
                    ab.setSelectedNavigationItem(position);
                }
            });
        }
        catch(Exception e)
        {
            String a = e.toString();
            ((TextView)findViewById(R.id.txtVwException)).setText(a);
            Toast.makeText(this, a, Toast.LENGTH_LONG);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_control, menu);
        //return true;
        MenuInflater mif = getMenuInflater();
        mif.inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
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

    @Override
    public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {

    }
}
