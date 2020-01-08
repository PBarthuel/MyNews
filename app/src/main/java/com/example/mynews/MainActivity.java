package com.example.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.mynews.Notification.NotificationActivity;
import com.example.mynews.Search.SearchActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    private static final String SECTION_NAME_HOME = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<NamedFragment> fragments = new ArrayList<>();

        fragments.add(TopStoryFragment.newInstance(SECTION_NAME_HOME, getString(R.string.menu_top_story)));
        fragments.add(new MostPopularFragment());
        fragments.add(TopStoryFragment.newInstance(getString(R.string.arts), getString(R.string.menu_arts)));
        fragments.add(TopStoryFragment.newInstance(getString(R.string.business), getString(R.string.menu_business)));
        fragments.add(TopStoryFragment.newInstance(getString(R.string.technology), getString(R.string.menu_technology)));

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        final NavigationView navigationView = findViewById(R.id.main_navigation_view_menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.main_vp);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments
        ));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int menuItemId = 0;
                switch (position) {
                    case 0 :
                        menuItemId = R.id.nav_top_story;
                        break;
                    case 1 :
                        menuItemId = R.id.nav_most_popular;
                        break;
                    case 2 :
                        menuItemId = R.id.nav_arts;
                        break;
                    case 3 :
                        menuItemId = R.id.nav_business;
                        break;
                    case 4 :
                        menuItemId = R.id.nav_technology;
                        break;
                }
                navigationView.setCheckedItem(menuItemId);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setCheckedItem(R.id.nav_top_story);

        TabLayout tabs = findViewById(R.id.main_tl);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_top_story) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_most_popular) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_arts) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_business) {
            viewPager.setCurrentItem(3);
        } else if (id == R.id.nav_technology) {
            viewPager.setCurrentItem(4);
        }

        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notifications) {
            startActivity(new Intent(this, NotificationActivity.class));
            return true;
        }
        if (id == R.id.action_help) {
            return true;
        }
        if (id == R.id.action_about) {
            return true;
        }
        if (id == R.id.menu_activity_search) {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
