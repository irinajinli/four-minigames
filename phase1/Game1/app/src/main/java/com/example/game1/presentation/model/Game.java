package com.example.game1.presentation.model;

import com.example.game1.presentation.model.Customization;

/**
 * The model object for a game. If needed, it can serve as a parent class for the model object of
 * a specific game.
 *
 * This object will be used by the domain layer and maps to the data objects in the data layer.
 */
public class Game {
    String name;
    int level;
    int numPoints;
    int numStars;
    int numTaps;
    Customization.CharacterColour characterColour;
    Customization.ColourScheme colourScheme;
    Customization.MusicPath musicPath;

    /**
     * Create a new game with default customization choices
     *
     @param name the Game's name
     @param level the Game's level
     */
    public Game (String name, int level) {
        this.name = name;
        this.level = level;
        numPoints = 0;
        numStars = 0;
        numTaps = 0;
        characterColour = Customization.CharacterColour.BLUE;
        colourScheme = Customization.ColourScheme.DARK;
        musicPath = Customization.MusicPath.SONG1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public int getNumStars() {
        return numStars;
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public int getNumTaps() {
        return numTaps;
    }

    public void setNumTaps(int numTaps) {
        this.numTaps = numTaps;
    }

    public Customization.CharacterColour getCharacterColour() {
        return characterColour;
    }

    public void setCharacterColour(Customization.CharacterColour characterColour) {
        this.characterColour = characterColour;
    }

    public Customization.ColourScheme getColourScheme() {
        return colourScheme;
    }

    public void setColourScheme(Customization.ColourScheme colourScheme) {
        this.colourScheme = colourScheme;
    }

    public Customization.MusicPath getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(Customization.MusicPath musicPath) {
        this.musicPath = musicPath;
    }
}
