package com.example.game1.presentation.model;

/** The customization choices of a Game. */
public class Customization {

  public enum CharacterColour {
    BLUE,
    RED,
    YELLOW
  }

  public enum ColourScheme {
    DARK,
    LIGHT
  }

  public enum MusicPath {
    SONG1,
    SONG2,
    SONG3
  }

  private CharacterColour characterColour;
  private ColourScheme colourScheme;
  private MusicPath musicPath;

  /**
   * Create a default Customization.
   */
  public Customization(){
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

}