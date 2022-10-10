package nz.ac.auckland.se206;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

  public static Media backgroundSound;
  public static MediaPlayer player;

  public static void intilalise() throws URISyntaxException {
    backgroundSound =
        new Media(App.class.getResource("/sounds/backgroundSong.mp3").toURI().toString());
    player = new MediaPlayer(backgroundSound);
  }

  public static void playBackgroundSong() throws URISyntaxException {

    player.play();
  }

  public static void stopBackgroundSong() throws URISyntaxException {

    player.stop();
  }
}
