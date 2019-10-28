package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.game1.R;

public class CustomizationActivity extends AppCompatActivity {

    private final String[] characterColours = {"Blue", "Red", "Yellow"};
    private final String[] colourSchemes = {"Dark", "Light"};
    private final String[] musicChoices = {"Song1", "Song2", "Song3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        // Set up the character colour spinner.
        Spinner characterColoursSpinner = findViewById(R.id.characterColourSpinner);
        ArrayAdapter characterColoursAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, characterColours);
        characterColoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        characterColoursSpinner.setAdapter(characterColoursAdapter);

        // Set up the colour scheme spinner.
        Spinner colourSchemeSpinner = findViewById(R.id.colourSchemeSpinner);
        ArrayAdapter colourSchemeAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, colourSchemes);
        colourSchemeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colourSchemeSpinner.setAdapter(colourSchemeAdapter);

        // Set up the music choices spinner.
        Spinner musicSpinner = findViewById(R.id.musicSpinner);
        ArrayAdapter musicAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, musicChoices);
        musicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        musicSpinner.setAdapter(musicAdapter);
    }


    /** Called when the user taps the Done button */
    public void sendCustomizationChoices(View view) {
        // Get the customization choices from the spinner
        Spinner characterColourSpinner = findViewById(R.id.characterColourSpinner);
        String characterColourChoice = characterColourSpinner.getSelectedItem().toString();

        Spinner colourSchemeSpinner = findViewById(R.id.characterColourSpinner);
        String colourSchemeChoice = colourSchemeSpinner.getSelectedItem().toString();

        Spinner musicSpinner = findViewById(R.id.musicSpinner);
        String musicChoice = musicSpinner.getSelectedItem().toString();

        // TODO: send customization choices to UserManager
    }
}
