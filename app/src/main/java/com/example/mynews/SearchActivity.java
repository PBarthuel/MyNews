package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private SearchManager searchManager = new SearchManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button button = findViewById(R.id.search_btn_launch_search);
        final EditText editText = findViewById(R.id.search_et_user);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchManager.isUserInputCorrect(editText.getText().toString())) {
                    Toast.makeText(SearchActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SearchActivity.this, "Not ok", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
