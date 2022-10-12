package nz.ac.auckland.se206;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

  public static Media backgroundSound;
  public static Media buttonSoundEffect;
  public static MediaPlayer playBackgroundSong;
  public static MediaPlayer playButtonSoundEffect;

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

    buttonSoundEffect =
        new Media(App.class.getResource("/sounds/buttonClick.mp3").toURI().toString());
    playButtonSoundEffect = new MediaPlayer(buttonSoundEffect);
  }

  private static void intialiseTextToSpeech() throws URISyntaxException {}

  public static void playBackgroundSong() throws URISyntaxException {

    playBackgroundSong.setMute(false);
    playBackgroundSong.seek(playBackgroundSong.getStartTime());
    playBackgroundSong.play();
  }

  public static void stopBackgroundSong() throws URISyntaxException {

    playBackgroundSong.stop();
  }

  public static void muteBackgroundSong() throws URISyntaxException {

    playBackgroundSong.setMute(true);
  }

  public static void unMuteBackgroundSong() throws URISyntaxException {

    playBackgroundSong.setMute(true);
  }

  public static void playButtonSoundEffect() throws URISyntaxException {

    playButtonSoundEffect.seek(playButtonSoundEffect.getStartTime());
    playButtonSoundEffect.play();
  }
}
