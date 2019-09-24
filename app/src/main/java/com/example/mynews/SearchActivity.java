package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements DatePickerDialogFragment.OnDateSelectedListener {

    private SearchManager searchManager = new SearchManager();
    private Button selectPastDate;
    private Button selectFuturDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        selectPastDate = findViewById(R.id.pastdate_btn);
        selectFuturDate = findViewById(R.id.futurdate_tv);

        Button button = findViewById(R.id.search_btn_launch_search);

        final EditText editText = findViewById(R.id.search_et_user);

        CheckBox artsCheckBox = findViewById(R.id.search_arts_checkbox);
        artsCheckBox.setTag("arts");

        CheckBox politicsCheckBox = findViewById(R.id.search_politics_checkbox);
        politicsCheckBox.setTag("politics");

        CheckBox businessCheckBox = findViewById(R.id.search_business_checkbox);
        businessCheckBox.setTag("buisness");

        CheckBox sportsCheckBox = findViewById(R.id.search_sports_checkbox);
        sportsCheckBox.setTag("sports");

        CheckBox entrepreneursCheckBox = findViewById(R.id.search_entrepreneurs_checkbox);
        entrepreneursCheckBox.setTag("entrepreneurs");

        CheckBox travelCheckBox = findViewById(R.id.search_travel_checkbox);
        travelCheckBox.setTag("travel");

        selectPastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(true);
            }
        });

        selectFuturDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(false);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchManager.isUserInputCorrect(editText.getText().toString(), new ArrayList<String>())) {
                    Toast.makeText(SearchActivity.this, "Ok", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SearchActivity.this, "Not ok", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDatePickerDialog(boolean isStartDate) {
        DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.newInstance(isStartDate, LocalDate.now());
        datePickerDialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onDateSelected(LocalDate localDate, boolean isStartDate) {
        if(isStartDate) {
            selectPastDate.setText(localDate.toString());
        }else {
            selectFuturDate.setText(localDate.toString());

        }
    }

    private List<String> getCheckTag (CheckBox checkBox, List<String> tagList) {
        return tagList;
    }
}
