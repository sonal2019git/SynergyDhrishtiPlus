package com.synergy.synergydhrishtiplus;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.synergy.synergydhrishtiplus.fragments.PumpListFragment;
import com.synergy.synergydhrishtiplus.fragments.StatusAndStockFragment;

public class CommonActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout container;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ImageView drawerIcon;
    MenuItem menuItem;
    FragmentManager fragmentManager;
    private ConstraintLayout topBarLayout;
    private TextView toolBarHeader;
    private ImageView toolBarMessageImage, toolBarLogoImage;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ResourcesCompat.getColor(getResources(), R.color.top_bar_color, null));
        setContentView(R.layout.activity_common);
        init();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.container, new StatusAndStockFragment(), "initial");
        tx.commit();
        setupDrawerContent(nvDrawer);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            this.menuItem = menuItem;
            selectDrawerItem(menuItem);
            return true;
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_dispenser:
                fragmentClass = PumpListFragment.class;
                changeToolbar(false);
                break;
            default:
                fragmentClass = StatusAndStockFragment.class;
                changeToolbar(true);
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(fragmentClass.getName()).commit();
        mDrawer.closeDrawers();
    }

    private void init() {
        container = findViewById(R.id.container);
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.nvView);
        drawerIcon = findViewById(R.id.drawerIcon);
        topBarLayout = findViewById(R.id.topBarLayout);
        toolBarHeader = findViewById(R.id.toolBarHeader);
        toolBarMessageImage = findViewById(R.id.toolBarMessageImage);
        toolBarLogoImage = findViewById(R.id.toolBarLogoImage);

        drawerIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mDrawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(nvDrawer)) {
            mDrawer.closeDrawers();
        } if(fragmentManager.getBackStackEntryCount() > 0) {
            changeToolbar(true);
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void changeToolbar(boolean isDefault){
        if(!isDefault) {
            topBarLayout.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.tool_bar_bg, null));
            toolBarHeader.setVisibility(View.GONE);
            toolBarLogoImage.setVisibility(View.VISIBLE);
            toolBarMessageImage.setVisibility(View.GONE);
        } else {
            topBarLayout.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.top_bar_color, null));
            toolBarHeader.setVisibility(View.VISIBLE);
            toolBarLogoImage.setVisibility(View.GONE);
            toolBarMessageImage.setVisibility(View.VISIBLE);
        }
    }
}