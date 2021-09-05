package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.diagnosahamapadi.fragment.ResultFragment;
import com.google.android.material.navigation.NavigationView;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = "HistoryActivity";

    private DrawerLayout draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        draw = findViewById(R.id.drawer_layout_history);
        final ActionBarDrawerToggle t = new ActionBarDrawerToggle(this, draw,
                R.string.open,
                R.string.close);

        draw.addDrawerListener(t);
        t.syncState();

        NavigationView navigationview = findViewById(R.id.nav_view_history);
        navigationview.setItemIconTintList(null);

        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.item1_history:
                        Toast.makeText(HistoryActivity.this,
                                "Ini Home", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.item2_hitory:
                        Toast.makeText(HistoryActivity.this,
                                "Item2 di klik", Toast.LENGTH_SHORT).show();
                       break;
                    case R.id.item3_history:
                        Toast.makeText(HistoryActivity.this,
                                "Item3 di klik", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}