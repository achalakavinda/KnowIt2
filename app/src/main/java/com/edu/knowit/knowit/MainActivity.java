package com.edu.knowit.knowit;
import com.edu.knowit.knowit.PageAdapters.UserLoginPageAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;



      @Override
    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if(mAuthStateListener != null)
        {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpFirebaseAuth();
        setupViewPager();

    }

    private void setupViewPager(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final UserLoginPageAdapter adapter = new UserLoginPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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

    private void setUpFirebaseAuth()
    {

        Log.d(TAG,"setUpFirebaseAuth: setting up fireabase auth");

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                checkCurrentUser(user);
                if(user != null){
                    Log.d(TAG,"onAuthStateChanged: signed_In"+user.getUid());
                    Intent intent = new Intent(getApplication().getApplicationContext(),BaseActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Log.d(TAG,"onAuthStateChanged: signed_Out");
                }
            }
        };
    }

    private void checkCurrentUser(FirebaseUser user){
        Log.d(TAG,"checkCurrentUser");
        if(user != null){
            Log.d(TAG,"checkCurrentUser: signed_In");
        }else
        {
            Log.d(TAG,"checkCurrentUser: not signed_Out");
        }
    }

}
