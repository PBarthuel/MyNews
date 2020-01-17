package com.example.mynews.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mynews.R;

import java.util.ArrayList;
import java.util.List;

public class SectionsCustomView extends ConstraintLayout {

    public SectionsCustomView(Context context) {
        this(context, null);
    }

    public SectionsCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectionsCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_view_sections, this, true);
    }

    public List<String> getSectionsSelected() {

        List<String> allSectionsSelected = new ArrayList<>();

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) getChildAt(i);
                if (checkBox.isChecked()) {
                    allSectionsSelected.add(checkBox.getText().toString());
                }
            }
        }

        return allSectionsSelected;
    }
}
