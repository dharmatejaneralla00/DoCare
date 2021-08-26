package com.marveltech.docare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.marveltech.docare.Fragments.DashboardFragment;
import com.marveltech.docare.Fragments.HistoryFragment;
import com.marveltech.docare.Fragments.SettingsFragment;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    MaterialToolbar materialToolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,new DashboardFragment()).commit();
        bottomNavigationView = findViewById(R.id.bottomnavbar_home);
        materialToolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        setSupportActionBar(materialToolbar);
        navigationView = findViewById(R.id.navigatonbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navbar_dashboard:
                    replace(new DashboardFragment());
                    break;
                case R.id.navbar_history:
                    replace(new HistoryFragment());
                    break;
                case R.id.navbar_settings:
                    replace(new SettingsFragment());
                    break;
            }
            return true;
        });
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,materialToolbar,R.string.closedrawer,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.navbarmenu_dashboard:
                    replace(new DashboardFragment());
                    bottomNavigationView.setSelectedItemId(R.id.navbar_dashboard);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.navbarmenu_history:
                    replace(new HistoryFragment());
                    bottomNavigationView.setSelectedItemId(R.id.navbar_history);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.navbarmenu_profile:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.navbarmenu_settings:
                    replace(new SettingsFragment());
                    bottomNavigationView.setSelectedItemId(R.id.navbar_settings);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.navbarmenu_help:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return false;
        });
        String view = getIntent().getStringExtra("value");
        if (TextUtils.equals(view,"dashboard"))
            replace(new DashboardFragment());
        else if (TextUtils.equals(view,"history"))
            replace(new HistoryFragment());
        else if (TextUtils.equals(view,"settings"))
            replace(new SettingsFragment());
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, fragment).commit();
    }

}