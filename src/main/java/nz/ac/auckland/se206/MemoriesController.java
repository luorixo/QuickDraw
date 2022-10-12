package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MemoriesController {

  @FXML private Button buttonBack;
  @FXML private Label statLabel;

  public void initialize() {}

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
