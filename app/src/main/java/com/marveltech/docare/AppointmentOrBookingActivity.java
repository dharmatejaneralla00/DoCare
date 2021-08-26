package com.marveltech.docare;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.marveltech.docare.Fragments.DashboardFragment;
import com.marveltech.docare.Fragments.HistoryFragment;
import com.marveltech.docare.Fragments.SettingsFragment;

public class AppointmentOrBookingActivity extends AppCompatActivity {
    MaterialToolbar materialToolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_or_booking);
        materialToolbar = findViewById(R.id.toolbar_appoint);
        drawerLayout = findViewById(R.id.drawerlayout_appoint);
        setSupportActionBar(materialToolbar);
        navigationView = findViewById(R.id.navigatonbar_appoint);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,materialToolbar,R.string.closedrawer,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.navbarmenu_dashboard:
                    Intent i = new Intent(AppointmentOrBookingActivity.this,HomeActivity.class);
                    i.putExtra("view","dashboard");
                    startActivity(i);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.navbarmenu_history:
                    Intent i1 = new Intent(AppointmentOrBookingActivity.this,HomeActivity.class);
                    i1.putExtra("view","history");
                    startActivity(i1);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.navbarmenu_profile:
                case R.id.navbarmenu_help:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.navbarmenu_settings:
                    Intent i3 = new Intent(AppointmentOrBookingActivity.this,HomeActivity.class);
                    i3.putExtra("view","settings");
                    startActivity(i3);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return false;
        });
        findViewById(R.id.appoint_bedbooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

}