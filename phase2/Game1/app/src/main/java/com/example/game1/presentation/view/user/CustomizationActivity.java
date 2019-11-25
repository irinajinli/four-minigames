package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.game1.R;
import com.example.game1.AppManager;
import com.example.game1.presentation.presenter.UserManager;

public class CustomizationActivity extends AppCompatActivity {

    private UserManager userManager = AppManager.getInstance().getUserManager();

    /* The customization choices that appear in the spinners */
    private final String[] characterColours = {"Blue", "Red", "Yellow"};
    private final String[] colourSchemes = {"Dark", "Light"};
    private final String[] musicChoices = {"Chibi Ninja", "Arpanauts", "A Night Of Dizzy Spells"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        // Set up the spinners to have the current user's customization choices selected
        setCharacterColourSpinnerSelection();
        setColourSchemeSpinnerSelection();
        setMusicChoiceSpinnerSelection();
    }

    /**
     * Set up the character colour spinner to have the current user's choice selected
     */
    private void setCharacterColourSpinnerSelection() {
        Spinner characterColoursSpinner = findViewById(R.id.characterColourSpinner);
        ArrayAdapter characterColoursAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, characterColours);
        characterColoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        characterColoursSpinner.setAdapter(characterColoursAdapter);

        String userCharacColour = userManager.getCurrentUser().getCustomization().getCharacterColour().toString();
        if (userCharacColour.equals("RED")) {
            characterColoursSpinner.setSelection(1);
        } else if (userCharacColour.equals("YELLOW")) {
            characterColoursSpinner.setSelection(2);
        } else {
            characterColoursSpinner.setSelection(0);
        }
    }

    /**
     * Set up the colour scheme spinner to have the current user's choice selected
     */
    private void setColourSchemeSpinnerSelection() {
        Spinner colourSchemeSpinner = findViewById(R.id.colourSchemeSpinner);
        ArrayAdapter colourSchemeAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, colourSchemes);
        colourSchemeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colourSchemeSpinner.setAdapter(colourSchemeAdapter);

        String userColourScheme = userManager.getCurrentUser().getCustomization().getColourScheme().toString();
        if (userColourScheme.equals("LIGHT")) {
            colourSchemeSpinner.setSelection(1);
        } else {
            colourSchemeSpinner.setSelection(0);
        }
    }

    /**
     * Set up the music choice spinner to have the current user's choice selected
     */
    private void setMusicChoiceSpinnerSelection() {
        Spinner musicSpinner = findViewById(R.id.musicSpinner);
        ArrayAdapter musicAdapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, musicChoices);
        musicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        musicSpinner.setAdapter(musicAdapter);

        String userMusic = userManager.getCurrentUser().getCustomization().getMusicPath().toString();
        if (userMusic.equals("SONG2")) {
            musicSpinner.setSelection(1);
        } else if (userMusic.equals("SONG3")) {
            musicSpinner.setSelection(2);
        } else {
            musicSpinner.setSelection(0);
        }
    }

    /**
     * Called when the user taps the Done button
     */
    public void sendCustomizationChoices(View view) {
        Intent intent = new Intent(this, UserMenuActivity.class);

        // Get the customization choices from the spinners
        Spinner characterColourSpinner = findViewById(R.id.characterColourSpinner);
        String characterColourChoice = characterColourSpinner.getSelectedItem().toString();
        Spinner colourSchemeSpinner = findViewById(R.id.colourSchemeSpinner);
        String colourSchemeChoice = colourSchemeSpinner.getSelectedItem().toString();
        Spinner musicSpinner = findViewById(R.id.musicSpinner);
        String musicChoice = musicSpinner.getSelectedItem().toString();

        //Update the current user's customization choices
        userManager.updateCurrentUsersCustomization(characterColourChoice, colourSchemeChoice,
                musicChoice);

        // Go back to the main menu
        startActivity(intent);
    }
}
