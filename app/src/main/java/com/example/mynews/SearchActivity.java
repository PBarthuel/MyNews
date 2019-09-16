package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private SearchManager searchManager = new SearchManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button button = findViewById(R.id.search_btn_launch_search);
        final EditText editText = findViewById(R.id.search_et_user);
        CheckBox artsCheckBox = findViewById(R.id.search_arts_checkbox);
        artsCheckBox.setTag("arts");
        CheckBox politicsCheckBox = findViewById(R.id.search_politics_checkbox);
        CheckBox buisnessCheckBox = findViewById(R.id.search_business_checkbox);
        CheckBox sportsCheckBox = findViewById(R.id.search_sports_checkbox);
        CheckBox entrepreneursCheckBox = findViewById(R.id.search_entrepreneurs_checkbox);
        CheckBox travelCheckBox = findViewById(R.id.search_travel_checkbox);

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

}
