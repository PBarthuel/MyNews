package com.example.mynews;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<NamedFragment> fragments;

    MainViewPagerAdapter(FragmentManager supportFragmentManager, List<NamedFragment> fragments) {
        super(supportFragmentManager);

        this.fragments = fragments;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
