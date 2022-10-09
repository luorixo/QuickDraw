package nz.ac.auckland.se206;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.swing.ToolTipManager;

public class SettingsController {

  @FXML ImageView logoImage;
  @FXML Button backButton;
  @FXML Button wordsLeft;
  @FXML Button wordsRight;
  @FXML Label wordsLabel;
  @FXML Button accuracyLeft;
  @FXML Button accuracyRight;
  @FXML Label accuracyLabel;
  @FXML Button confidenceLeft;
  @FXML Button confidenceRight;
  @FXML Label confidenceLabel;
  @FXML Button timeLeft;
  @FXML Button timeRight;
  @FXML Label timeLabel;
  @FXML Button speechButton;
  @FXML ImageView speechImage;
  @FXML Button musicButton;
  @FXML ImageView musicImage;
  @FXML Button soundButton;
  @FXML ImageView soundImage;

  public void intialize() {
    // shorted tooltip delay
    ToolTipManager.sharedInstance().setInitialDelay(0);
    ToolTipManager.sharedInstance().setReshowDelay(0);
  }
}
