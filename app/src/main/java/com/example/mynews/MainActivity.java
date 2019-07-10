package com.example.mynews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new ArrayList<Fragment>();

        fragments.add(new ScienceTopStoryFragment());
        fragments.add(new MostPopularFragment());

        ViewPager viewPager = findViewById(R.id.main_vp);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments
        ));

        TabLayout tabs = findViewById(R.id.main_tl);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}
