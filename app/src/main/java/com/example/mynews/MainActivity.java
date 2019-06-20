package com.example.mynews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragments = new ArrayList<Fragment>();
            fragments.add(new TopStoryFragment());
            fragments.add(new TopStoryFragment());
        ViewPager viewPager = findViewById(R.id.main_vp);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments
                ));
    }
}
