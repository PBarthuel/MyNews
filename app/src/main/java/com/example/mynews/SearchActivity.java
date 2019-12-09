package com.example.mynews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

public class SearchActivity extends AppCompatActivity implements DatePickerDialogFragment.OnDateSelectedListener {

    private SearchManager searchManager = new SearchManager();
    private Button selectPastDate;
    private Button selectFutureDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        selectPastDate = findViewById(R.id.pastdate_btn);
        selectFutureDate = findViewById(R.id.futurdate_tv);

        Button button = findViewById(R.id.search_btn_launch_search);

        final SectionsCustomView sectionsCustomViewSearch = findViewById(R.id.cv_search_checkbox);

        final EditText editText = findViewById(R.id.search_et_user);

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
                        sectionsCustomViewSearch.getSectionsSelected(),
                        selectPastDate.getText().toString(),
                        selectFutureDate.getText().toString())) {
                    case OK:
                        Toast.makeText(SearchActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                        startActivity(
                                SearchResultActivity.navigate(
                                        SearchActivity.this,
                                        searchManager.getLucene(
                                                editText.getText().toString(),
                                                sectionsCustomViewSearch.getSectionsSelected()
                                        ),
                                        searchManager.getBeginDate(selectPastDate.getText().toString()),
                                        searchManager.getEndDate(selectFutureDate.getText().toString())
                                )
                        );
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
}
