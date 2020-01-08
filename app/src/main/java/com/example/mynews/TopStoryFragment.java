package com.example.mynews;

import android.os.Bundle;

public class TopStoryFragment extends TopStoryAbsFragment {

    private static final String KEY_SECTION = "KEY_SECTION";
    private static final String KEY_SECTION_TITLE = "KEY_SECTION_TITLE";


    static TopStoryFragment newInstance(String sectionName, String sectionTitle) {

        Bundle args = new Bundle();
        args.putString(KEY_SECTION, sectionName);
        args.putString(KEY_SECTION_TITLE, sectionTitle);

        TopStoryFragment fragment = new TopStoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getSectionName() {
        assert getArguments() != null;
        return getArguments().getString(KEY_SECTION);
    }

    @Override
    String getTitle() {
        assert getArguments() != null;
        return getArguments().getString(KEY_SECTION_TITLE);
    }
}
