package nz.ac.auckland.se206;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import nz.ac.auckland.se206.speech.TextToSpeech;

public class MusicPlayer {

  private static Media backgroundSound;
  private static Media buttonSound;
  private static Media startSound;
  private static Media drawingSound;
  private static Media coinSound;
  private static MediaPlayer playBackgroundSong;
  private static MediaPlayer playButtonSoundEffect;
  private static MediaPlayer startButtonSoundEffect;
  private static MediaPlayer drawingSoundEffect;
  private static MediaPlayer coinSoundEffect;

  /**
   * This function intialises the background song and the sound effects the game uses
   *
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void intilalise() throws URISyntaxException {

    intialiseBackgroundSong();
    intialiseSoundEffects();
  }

  /**
   * THis function downloads the background song from resources
   *
   * @throws URISyntaxException in case the URI is wrong
   */
  private static void intialiseBackgroundSong() throws URISyntaxException {
    backgroundSound =
        new Media(App.class.getResource("/sounds/backgroundSong.mp3").toURI().toString());
    playBackgroundSong = new MediaPlayer(backgroundSound);
    playBackgroundSong.setCycleCount(MediaPlayer.INDEFINITE);
  }

  /**
   * This function is called when we want to play the background song
   *
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void playBackgroundSong() throws URISyntaxException {

    playBackgroundSong.setMute(false);
    playBackgroundSong.seek(playBackgroundSong.getStartTime());
    playBackgroundSong.play();
  }

  /**
   * This function is called when the user chooses to mute the background song
   *
   * @param user allows us to check the user setting
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void muteBackgroundSong(User user) throws URISyntaxException {

    if (!user.getMusicState()) {
      playBackgroundSong.setMute(true);
    } else {
      playBackgroundSong.setMute(false);
    }
  }

  /**
   * This function allows us to play the background song if the user decides to
   *
   * @param user allows us to check the user setting
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void playBackgroundSong(User user) throws URISyntaxException {

    if (user.getMusicState()) {
      playBackgroundSong.setMute(false);
      playBackgroundSong.seek(playBackgroundSong.getStartTime());
      playBackgroundSong.play();
    }
  }

  /**
   * This function plays the coin sound effect if the user has the preference on.
   *
   * @param user User type object that contains user preferences.
   */
  public static void playCoinSoundEffect(User user) {
    if (user.getSoundEffectState()) {
      coinSoundEffect.seek(coinSoundEffect.getStartTime());
      coinSoundEffect.play();
    }
  }

  /**
   * This function downloads all the sounds effects that will be used in the game from resources
   *
   * @throws URISyntaxException in case the URI is wrong
   */
  private static void intialiseSoundEffects() throws URISyntaxException {

    buttonSound = new Media(App.class.getResource("/sounds/buttonClick.mp3").toURI().toString());
    playButtonSoundEffect = new MediaPlayer(buttonSound);
    startSound = new Media(App.class.getResource("/sounds/gameStart.mp3").toURI().toString());
    startButtonSoundEffect = new MediaPlayer(startSound);
    drawingSound = new Media(App.class.getResource("/sounds/drawingSound.mp3").toURI().toString());
    drawingSoundEffect = new MediaPlayer(drawingSound);
    coinSound = new Media(App.class.getResource("/sounds/coinsSound.mp3").toURI().toString());
    coinSoundEffect = new MediaPlayer(coinSound);
  }

  /**
   * This method plays the button sound effect on the landing page
   *
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void playButtonSoundEffect() throws URISyntaxException {

    playButtonSoundEffect.seek(playButtonSoundEffect.getStartTime());
    playButtonSoundEffect.play();
  }

  /**
   * This methods plays the button sound effect on every other page
   *
   * @param user allows us to check the user settings for sound effects
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void playButtonSoundEffect(User user) throws URISyntaxException {

    if (user.getSoundEffectState()) {
      playButtonSoundEffect.seek(playButtonSoundEffect.getStartTime());
      playButtonSoundEffect.play();
    }
  }

  /**
   * This method plays the start button sound effect
   *
   * @param user allows us to check the user settings for sound effects
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void startButtonSoundEffect(User user) throws URISyntaxException {

    if (user.getSoundEffectState()) {
      startButtonSoundEffect.seek(startButtonSoundEffect.getStartTime());
      startButtonSoundEffect.play();
    }
  }

  /**
   * This method plays the drawing sound effect when the plays chooses to draw
   *
   * @param user allows us to check the user settings for sound effects
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void drawingSoundEffect(User user) throws URISyntaxException {

    drawingSoundEffect.getTotalDuration();
    drawingSoundEffect.setStartTime(Duration.seconds(13.0));
    drawingSoundEffect.setStopTime(Duration.seconds(13.5));

    if (user.getSoundEffectState()) {
      drawingSoundEffect.seek(drawingSoundEffect.getStartTime());
      drawingSoundEffect.play();
    }
  }

  /**
   * Whenever a class uses texttospeech they will pass it through this function here
   *
   * @param user allows us to check the user's settings for text to speech
   * @param speakString the string of what the text to speech will say
   * @throws URISyntaxException in case the URI is wrong
   */
  public static void TextToSpeech(User user, String speakString) throws URISyntaxException {

    if (user.getTextToSpeechState()) {
      TextToSpeech textToSpeech = new TextToSpeech();
      textToSpeech.speak(speakString);
    }
  }
}
