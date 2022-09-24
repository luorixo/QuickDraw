package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StatsPageController {

  public static int id;
  @FXML private ImageView labelLogo;
  @FXML private Button buttonBack;
  @FXML private Label labelGamesWon;
  @FXML private Label labelGamesPlayed;
  @FXML private Label labelGamesLost;
  @FXML private Label labelBestTime;
  @FXML private Label labelAverageTime;
  private User user;

  /**
   * On Scene intialisation set all of the label values to the respective users statistics. This
   * assumes that the static variable of the user id has been set prior to the scene being created
   * and called.
   */
  public void initialize() {
    user = User.getUser(id); // get the user then set all labels
    labelGamesWon.setText(String.valueOf(user.getGamesWon()));
    labelGamesPlayed.setText(String.valueOf(user.getGamesPlayed()));
    labelGamesLost.setText(String.valueOf(user.getGamesLost()));
    labelBestTime.setText(String.valueOf(user.getBestTime()));
    labelAverageTime.setText(String.valueOf(user.getAverageTime()));
  }

  /**
   * On back button click this will set the scene back to the user home
   *
   * @param event
   */
  @FXML
  private void onBackButton(ActionEvent event) {
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from stats page back to user home
      currentScene.setRoot(App.loadFxml("userHome"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
