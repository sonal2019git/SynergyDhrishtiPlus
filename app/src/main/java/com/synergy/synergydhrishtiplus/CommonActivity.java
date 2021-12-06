package com.synergy.synergydhrishtiplus;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.synergy.synergydhrishtiplus.fragments.StatusAndStockFragment;

public class CommonActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout container;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ImageView drawerIcon;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ResourcesCompat.getColor(getResources(), R.color.top_bar_color, null));
        setContentView(R.layout.activity_common);

        init();
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.container, new StatusAndStockFragment(),"initial");
        tx.commit();
        setupDrawerContent(nvDrawer);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            default:
                fragmentClass = StatusAndStockFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        mDrawer.closeDrawers();
    }

    private void init() {
        container = findViewById(R.id.container);
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.nvView);
        drawerIcon = findViewById(R.id.drawerIcon);

        drawerIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mDrawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(mDrawer.isDrawerOpen(nvDrawer)){
            mDrawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}