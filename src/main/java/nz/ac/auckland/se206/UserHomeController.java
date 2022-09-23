package nz.ac.auckland.se206;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserHomeController {

  public static int id = 1;
  @FXML private Label nameLabel;
  @FXML private Button backButton;
  @FXML private ImageView logoImage;
  @FXML private Button statsButton;
  @FXML private Button leftButton;
  @FXML private Button rightButton;
  @FXML private ImageView drawingImage;
  @FXML private Label drawingLabel;
  @FXML private Button zenButton;
  @FXML private Button definitionButton;
  @FXML private Button easyButton;
  @FXML private Button mediumButton;
  @FXML private Button hardButton;
  @FXML private Button masterButton;
  private User user;
  private List<String> wordsSeen;
  private int currentWordIndex = 1;

  /**
   * After userHome fxml and controller have been constructed this will set the current user and get
   * needed details. This method will also set the initial image in the image viewer - either to a
   * waiting image or the corresponding image from the first word in their played list.
   */
  public void initialize() {
    user = User.getUser(id);
    nameLabel.setText("Hi, " + user.getName() + "!"); // set the greeting label
    wordsSeen = user.getWordsSeen();
    System.out.println(wordsSeen.size());

    // load in users drawing as an image
    this.updateImage();
  }

  /*
   * Updates image viewer image to the previous image from words seen list.
   */
  @FXML
  private void onLeftButton() {
    // go to previous word, if was at first word then loop to last
    currentWordIndex -= 1;
    if (currentWordIndex < 0) {
      currentWordIndex = wordsSeen.size() - 1;
    }
    this.updateImage();
  }

  /*
   *Updates image viewer image to the next image from the words seen list.
   */
  @FXML
  private void onRightButton() {
    // go to next word, if was at last word then loop to first
    currentWordIndex += 1;
    if (currentWordIndex == wordsSeen.size()) {
      currentWordIndex = 0;
    }
    this.updateImage();
  }

  private void updateImage() {
    // load in users drawing as an image
    File file;
    if (wordsSeen.size() == 0) { // set waiting image if user has not drawn anything yet
      file = new File("src/main/resources/images/waiting-img.png");
    } else { // else set image to current index in list
      file =
          new File(
              "src/main/resources/users/user"
                  + id
                  + "/"
                  + wordsSeen.get(currentWordIndex)
                  + ".bmp");
    }
    Image image = new Image(file.toURI().toString());
    drawingImage.setImage(image);
  }

  /**
   * On back button click this will set the scene back to the user select scene
   *
   * @param event
   */
  @FXML
  public void onBackButton(ActionEvent event) {
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page back to user select
      currentScene.setRoot(App.loadFxml(""));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On back button click this will set the scene to the user stats scene
   *
   * @param event
   */
  @FXML
  public void onStatsButton(ActionEvent event) {
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page to user stats
      StatsPageController.id = id;
      currentScene.setRoot(App.loadFxml("statsPage"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On back button click this will set the scene to the easy game
   *
   * @param event
   */
  @FXML
  public void onEasyButton(ActionEvent event) {
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page to easy game
      currentScene.setRoot(App.loadFxml("canvas"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
