package com.example.mynews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements DatePickerDialogFragment.OnDateSelectedListener {

    private SearchManager searchManager = new SearchManager();
    private Button selectPastDate;
    private Button selectFutureDate;
    private CheckBox artsCheckBox;
    private CheckBox politicsCheckBox;
    private CheckBox businessCheckBox;
    private CheckBox sportsCheckBox;
    private CheckBox entrepreneursCheckBox;
    private CheckBox travelCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        selectPastDate = findViewById(R.id.pastdate_btn);
        selectFutureDate = findViewById(R.id.futurdate_tv);

        Button button = findViewById(R.id.search_btn_launch_search);

        final List<String> tagList = new ArrayList<>();

        final EditText editText = findViewById(R.id.search_et_user);

        artsCheckBox = findViewById(R.id.search_arts_checkbox);
        artsCheckBox.setTag("arts");

        politicsCheckBox = findViewById(R.id.search_politics_checkbox);
        politicsCheckBox.setTag("politics");

        businessCheckBox = findViewById(R.id.search_business_checkbox);
        businessCheckBox.setTag("buisness");

        sportsCheckBox = findViewById(R.id.search_sports_checkbox);
        sportsCheckBox.setTag("sports");

        entrepreneursCheckBox = findViewById(R.id.search_entrepreneurs_checkbox);
        entrepreneursCheckBox.setTag("entrepreneurs");

        travelCheckBox = findViewById(R.id.search_travel_checkbox);
        travelCheckBox.setTag("travel");

        selectPastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(true);
            }
        });

        selectFutureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(false);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (searchManager.isUserInputCorrect(editText.getText().toString(),
                        getCheckTagList(tagList),
                        selectPastDate.getText().toString() ,
                        selectFutureDate.getText().toString())) {
                    case OK:
                        Toast.makeText(SearchActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                        startActivity(SearchResultActivity.navigate(SearchActivity.this, editText.getText().toString()));
                        break;
                    case INPUT_INCORRECT:
                        Toast.makeText(SearchActivity.this, "Input isn't ok", Toast.LENGTH_SHORT).show();
                        break;
                    case NO_SECTIONS_SELECTED:
                        Toast.makeText(SearchActivity.this, "You must select at least one section", Toast.LENGTH_SHORT).show();
                        break;
                    case DATE_IS_INCORRECT:
                        Toast.makeText(SearchActivity.this, "The date you select is incorrect", Toast.LENGTH_SHORT).show();
                        break;
                    case BEGIN_DATE_IS_IN_THE_FUTURE:
                        Toast.makeText(SearchActivity.this, "Begin date is in the future", Toast.LENGTH_SHORT).show();
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
        if (isStartDate) {
            selectPastDate.setText(localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else {
            selectFutureDate.setText(localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

    private List<String> getCheckTagList(List<String> tagList) {

        if (artsCheckBox.isChecked()) {
            tagList.add(artsCheckBox.getTag().toString());
        }else {
            tagList.remove(artsCheckBox.getTag().toString());
        }

        if (politicsCheckBox.isChecked()) {
            tagList.add(politicsCheckBox.getTag().toString());
        }else {
            tagList.remove(politicsCheckBox.getTag().toString());
        }

        if (businessCheckBox.isChecked()) {
            tagList.add(businessCheckBox.getTag().toString());
        }else {
            tagList.remove(businessCheckBox.getTag().toString());
        }

        if (sportsCheckBox.isChecked()) {
            tagList.add(sportsCheckBox.getTag().toString());
        }else {
            tagList.remove(sportsCheckBox.getTag().toString());
        }

        if (entrepreneursCheckBox.isChecked()) {
            tagList.add(entrepreneursCheckBox.getTag().toString());
        }else {
            tagList.remove(entrepreneursCheckBox.getTag().toString());
        }

        if (travelCheckBox.isChecked()) {
            tagList.add(travelCheckBox.getTag().toString());
        }else {
            tagList.remove(travelCheckBox.getTag().toString());
        }

        return tagList;
    }
}
