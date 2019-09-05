package com.example.mynews;

import android.os.Bundle;

public class TopStoryFragment extends TopStoryAbsFragment {

    private static final String KEY_SECTION = "KEY_SECTION";

    public static TopStoryFragment newInstance (String sectionName) {

        Bundle args = new Bundle();
        args.putString(KEY_SECTION, sectionName);

        TopStoryFragment fragment = new TopStoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getSectionName() {
        return getArguments().getString(KEY_SECTION);
    }

    @Override
    String getTitle() {
        return getArguments().getString(KEY_SECTION);
    }
}
