package anigri.jct.ac.il.android5778_7866_app2.controller;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import anigri.jct.ac.il.android5778_7866_app2.R;
import anigri.jct.ac.il.android5778_7866_app2.controller.Fragments.AboutUsFragment;
import anigri.jct.ac.il.android5778_7866_app2.controller.Fragments.BranchesFragment;
import anigri.jct.ac.il.android5778_7866_app2.controller.Fragments.CarsFragment;
import anigri.jct.ac.il.android5778_7866_app2.controller.Fragments.CurrentCarFragment;
import anigri.jct.ac.il.android5778_7866_app2.controller.Fragments.HomeFragment;
import anigri.jct.ac.il.android5778_7866_app2.controller.Fragments.LoginFragment;
import anigri.jct.ac.il.android5778_7866_app2.model.entities.Client;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private long clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientId = getIntent().getLongExtra("clientId", -1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("clientId", clientId);
        fragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragmentMainContainer, fragment);
        //ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public long getClientId(){
        return clientId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }

    private void displaySelectedScreen(int id){
        Fragment fragment = null;

        switch (id){
            case R.id.nav_about_us:
                fragment = new AboutUsFragment();
                break;
            case R.id.nav_branches:
                fragment = new BranchesFragment();
                break;
            case R.id.nav_cars:
                fragment = new CarsFragment();
                break;
            case R.id.nav_my_car:
                fragment = new CurrentCarFragment();
                Bundle bundle = new Bundle();
                bundle.putLong("clientId", clientId);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_logout:
                logout();
                break;
        }

        if(fragment != null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentMainContainer, fragment);
            //ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
        finish();
    }


}
