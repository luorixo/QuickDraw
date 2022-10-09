package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.swing.ToolTipManager;

public class SettingsController {

  public static int id;
  @FXML private ImageView logoImage;
  @FXML private Button backButton;
  @FXML private Button wordsLeft;
  @FXML private Button wordsRight;
  @FXML private Label wordsLabel;
  @FXML private Button accuracyLeft;
  @FXML private Button accuracyRight;
  @FXML private Label accuracyLabel;
  @FXML private Button confidenceLeft;
  @FXML private Button confidenceRight;
  @FXML private Label confidenceLabel;
  @FXML private Button timeLeft;
  @FXML private Button timeRight;
  @FXML private Label timeLabel;
  @FXML private Button speechButton;
  @FXML private ImageView speechImage;
  @FXML private Button musicButton;
  @FXML private ImageView musicImage;
  @FXML private Button soundButton;
  @FXML private ImageView soundImage;
  private User user;

  public void intialize() {

    // shorted tooltip delay
    ToolTipManager.sharedInstance().setInitialDelay(0);
    ToolTipManager.sharedInstance().setReshowDelay(0);

    user = User.getUser(id);
    // get current chosen difficulty and display for each label
    // get current option for music, sound and speech and display appropriate image
  }

  /*
   * This function will allow the left button to change the difficulty of the game, both the appropriate display and the user json will be updated. This function will allow cycling between EASY MEDIUM HARD and MASTER.
   */
  @FXML
  private void onLeftButtonFourDifficulty(ActionEvent event) {
    Button leftButton = (Button) event.getSource();
    String buttonName = leftButton.getId();
    String difficulty = "";

    // get current difficulty of relevant metric
    switch (buttonName) {
      case "wordsLeft":
        // get words difficulty
      case "confidenceLeft":
        // get confidence difficulty
      case "timeLeft":
        // get time difficulty
    }
    // left cycle for button
    switch (difficulty) {
      case "EASY":
        difficulty = "MASTER";
      case "MEDIUM":
        difficulty = "EASY";
      case "HARD":
        difficulty = "MEDIUM";
      case "MASTER":
        difficulty = "HARD";
    }

    // set user profile preference and display label the new difficulty
    switch (buttonName) {
      case "wordsLeft":
        // set words difficulty
        wordsLabel.setText(difficulty);
      case "confidenceLeft":
        // set confidence difficulty
        confidenceLabel.setText(difficulty);
      case "timeLeft":
        // set time difficulty
        timeLabel.setText(difficulty);
    }
  }

  /*
   * This function will allow the right button to change the difficulty of the game, both the appropriate display and the user json will be updated. This function will allow cycling between EASY MEDIUM HARD and MASTER.
   */
  @FXML
  private void onRightButtonFourDifficulty(ActionEvent event) {
    Button rightButton = (Button) event.getSource();
    String buttonName = rightButton.getId();
    String difficulty = "";

    // get current difficulty of relevant metric
    switch (buttonName) {
      case "wordsRight":
        // get words difficulty
      case "confidenceRight":
        // get confidence difficulty
      case "timeRight":
        // get time difficulty
    }
    // right cycle for button
    switch (difficulty) {
      case "EASY":
        difficulty = "MEDIUM";
      case "MEDIUM":
        difficulty = "HARD";
      case "HARD":
        difficulty = "MASTER";
      case "MASTER":
        difficulty = "EASY";
    }

    // set user profile preference and display label the new difficulty
    switch (buttonName) {
      case "wordsRight":
        // set words difficulty
        wordsLabel.setText(difficulty);
      case "confidenceRight":
        // set confidence difficulty
        confidenceLabel.setText(difficulty);
      case "timeRight":
        // set time difficulty
        timeLabel.setText(difficulty);
    }
  }

  /*
   * This function will allow the left button to change the difficulty of the game, both the appropriate display and the user json will be updated. This function will allow cycling between EASY MEDIUM and HARD.
   */
  @FXML
  private void onLeftButtonThreeDifficulty(ActionEvent event) {
    Button leftButton = (Button) event.getSource();
    String buttonName = leftButton.getId();
    String difficulty = "";

    if (buttonName.equals("accuracyLeft")) {
      // get difficulty of accuracy
    }
    // left cycle for button
    switch (difficulty) {
      case "EASY":
        difficulty = "HARD";
      case "MEDIUM":
        difficulty = "EASY";
      case "HARD":
        difficulty = "MEDIUM";
    }

    // set user profile preference and display label the new difficulty
    if (buttonName.equals("accuracyLeft")) {
      // set difficulty of accuracy
      accuracyLabel.setText(difficulty);
    }
  }

  /*
   * This function will allow the right button to change the difficulty of the game, both the appropriate display and the user json will be updated. This function will allow cycling between EASY MEDIUM and HARD.
   */
  @FXML
  private void onRightButtonThreeDifficulty(ActionEvent event) {
    Button rightButton = (Button) event.getSource();
    String buttonName = rightButton.getId();
    String difficulty = "";

    if (buttonName.equals("accuracyRight")) {
      // get difficulty of accuracy
    }
    // left cycle for button
    switch (difficulty) {
      case "EASY":
        difficulty = "MEDIUM";
      case "MEDIUM":
        difficulty = "HARD";
      case "HARD":
        difficulty = "EASY";
    }

    // set user profile preference and display label the new difficulty
    if (buttonName.equals("accuracyRight")) {
      // set difficulty of accuracy
      accuracyLabel.setText(difficulty);
    }
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
      // change scene from settings page back to user home
      currentScene.setRoot(App.loadFxml("userHome"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
