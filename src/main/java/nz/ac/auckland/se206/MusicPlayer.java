package nz.ac.auckland.se206;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer {

  public static Media backgroundSound;
  public static Media buttonSound;
  public static Media startSound;
  public static Media drawingSound;
  public static MediaPlayer playBackgroundSong;
  public static MediaPlayer playButtonSoundEffect;
  public static MediaPlayer startButtonSoundEffect;
  public static MediaPlayer drawingSoundEffect;

  public static void intilalise() throws URISyntaxException {

    intialiseBackgroundSong();
    intialiseSoundEffects();
    intialiseTextToSpeech();
  }

  private static void intialiseBackgroundSong() throws URISyntaxException {
    backgroundSound =
        new Media(App.class.getResource("/sounds/backgroundSong.mp3").toURI().toString());
    playBackgroundSong = new MediaPlayer(backgroundSound);
  }

  private static void intialiseSoundEffects() throws URISyntaxException {

    buttonSound = new Media(App.class.getResource("/sounds/buttonClick.mp3").toURI().toString());
    playButtonSoundEffect = new MediaPlayer(buttonSound);
    startSound = new Media(App.class.getResource("/sounds/gameStart.mp3").toURI().toString());
    startButtonSoundEffect = new MediaPlayer(startSound);
    drawingSound = new Media(App.class.getResource("/sounds/drawingSound.mp3").toURI().toString());
    drawingSoundEffect = new MediaPlayer(drawingSound);
  }

  private static void intialiseTextToSpeech() throws URISyntaxException {}

  public static void playBackgroundSong() throws URISyntaxException {

    playBackgroundSong.setMute(false);
    playBackgroundSong.seek(playBackgroundSong.getStartTime());
    playBackgroundSong.play();
  }

  public static void playBackgroundSong(User user) throws URISyntaxException {

    if (user.getMusicState()) {
      playBackgroundSong.setMute(false);
      playBackgroundSong.seek(playBackgroundSong.getStartTime());
      playBackgroundSong.play();
    }
  }

  public static void stopBackgroundSong() throws URISyntaxException {

    playBackgroundSong.stop();
  }

  public static void muteBackgroundSong(User user) throws URISyntaxException {

    if (!user.getMusicState()) {
      playBackgroundSong.setMute(true);
    } else {
      playBackgroundSong.setMute(false);
    }
  }

  public static void playButtonSoundEffect() throws URISyntaxException {

    playButtonSoundEffect.seek(playButtonSoundEffect.getStartTime());
    playButtonSoundEffect.play();
  }

  public static void playButtonSoundEffect(User user) throws URISyntaxException {

    if (user.getSoundEffectState()) {
      playButtonSoundEffect.seek(playButtonSoundEffect.getStartTime());
      playButtonSoundEffect.play();
    }
  }

  public static void startButtonSoundEffect(User user) throws URISyntaxException {

    if (user.getSoundEffectState()) {
      startButtonSoundEffect.seek(startButtonSoundEffect.getStartTime());
      startButtonSoundEffect.play();
    }
  }

  public static void drawingSoundEffect(User user) throws URISyntaxException {

    drawingSoundEffect.getTotalDuration();
    drawingSoundEffect.setStartTime(Duration.seconds(13.0));
    drawingSoundEffect.setStopTime(Duration.seconds(13.5));

    if (user.getSoundEffectState()) {
      drawingSoundEffect.seek(drawingSoundEffect.getStartTime());
      drawingSoundEffect.play();
    }
  }
}
