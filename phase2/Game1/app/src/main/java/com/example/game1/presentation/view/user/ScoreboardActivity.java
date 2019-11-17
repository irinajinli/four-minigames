package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.game1.R;

public class ScoreboardActivity extends AppCompatActivity {

    private final String[] spinnerChoices = {"Total Score", "Points", "Stars", "Taps"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        // Set up the spinner choices
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerChoices);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(0);

        // Set up the spinner listener
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                        EditText header2 = findViewById(R.id.tableHeader2);
                        header2.setText(item.toString());
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

    /**
     * Called when the user taps the Back button
     */
    public void goBack(View view) {
        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }

}
