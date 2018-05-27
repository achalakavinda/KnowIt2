package com.edu.knowit.knowit;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.edu.knowit.knowit.PageAdapters.BasePageAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity implements  View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "Base Activity";
    private NavigationView navigationView=null;
    private ActionBarDrawerToggle menuToggle;
    private DrawerLayout menuDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
//Obtain the toolbar support
        menuDrawerLayout = (DrawerLayout) findViewById(R.id.homeMenu);
        //Set Listener
        menuToggle = new ActionBarDrawerToggle(this,menuDrawerLayout,R.string.open,R.string.close);
        Toolbar toolbar = (Toolbar) findViewById(R.id.menuBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        menuDrawerLayout.addDrawerListener(menuToggle);
        menuToggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_search_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_add_box_black_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final BasePageAdapter adapter = new BasePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(1);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });



    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuToggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.profile_item:
                startActivity(new Intent(this,ProfleViewActivity.class));
                break;
            case R.id.log_out_item:
                Log.d(TAG,"Sign Out Click");
                signOut();
                break;
            default:
                Log.d(TAG,"Nav Click");
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.homeMenu);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
