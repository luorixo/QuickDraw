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
    backgroundSound =
        new Media(App.class.getResource("/sounds/backgroundSong.mp3").toURI().toString());
    playBackgroundSong = new MediaPlayer(backgroundSound);
    buttonSoundEffect =
        new Media(App.class.getResource("/sounds/buttonClick.mp3").toURI().toString());
    playButtonSoundEffect = new MediaPlayer(buttonSoundEffect);
  }

  public static void playBackgroundSong() throws URISyntaxException {

    playBackgroundSong.seek(playBackgroundSong.getStartTime());
    playBackgroundSong.play();
  }

  public static void stopBackgroundSong() throws URISyntaxException {

    playBackgroundSong.stop();
  }

  public static void playButtonSoundEffect() throws URISyntaxException {

    playButtonSoundEffect.seek(playButtonSoundEffect.getStartTime());
    playButtonSoundEffect.play();
  }
}
