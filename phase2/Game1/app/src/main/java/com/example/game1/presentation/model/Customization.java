package com.example.game1.presentation.model;

/** The model object for the customization choices of a user or game. */
public class Customization {

  private CharacterColour characterColour;
  private ColourScheme colourScheme;
  private MusicPath musicPath;

  /** Constructs a new Customization with default choices. */
  public Customization() {
    characterColour = CharacterColour.BLUE;
    colourScheme = ColourScheme.DARK;
    musicPath = MusicPath.SONG1;
  }

  public CharacterColour getCharacterColour() {
    return characterColour;
  }

  public void setCharacterColour(CharacterColour characterColour) {
    this.characterColour = characterColour;
  }

  public ColourScheme getColourScheme() {
    return colourScheme;
  }

  public void setColourScheme(ColourScheme colourScheme) {
    this.colourScheme = colourScheme;
  }

  public MusicPath getMusicPath() {
    return musicPath;
  }

  public void setMusicPath(MusicPath musicPath) {
    this.musicPath = musicPath;
  }

  /** The character colour choices */
  public enum CharacterColour {
    BLUE,
    RED,
    YELLOW
  }

  /** The colours scheme choices */
  public enum ColourScheme {
    DARK,
    LIGHT
  }

  /** The song choices */
  public enum MusicPath {
    SONG1,
    SONG2,
    SONG3
  }
}
