package nz.ac.auckland.se206;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

public class UserHomeController {

  public static int id;
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
  private int currentWordIndex = 0;

  /**
   * After userHome fxml and controller have been constructed this will set the current user and get
   * needed details. This method will also set the initial image in the image viewer - either to a
   * waiting image or the corresponding image from the first word in their played list.
   *
   * @throws URISyntaxException
   */
  public void initialize() throws URISyntaxException {

    user = User.getUser(id);
    nameLabel.setText("Hi, " + user.getName() + "!"); // set the greeting label
    wordsSeen = user.getWordsSeen();
    if (wordsSeen.size() > 0) {
      currentWordIndex = wordsSeen.size() - 1;
    }
    // load in users drawing as an image
    this.updateImage();

    MusicPlayer.muteBackgroundSong(user);
  }

  /*
   * Updates image viewer image to the previous image from words seen list.
   */
  @FXML
  private void onLeftButton() throws URISyntaxException {

    MusicPlayer.playButtonSoundEffect(user);

    // go to previous word, if was at first word then loop to last
    currentWordIndex -= 1;
    if (currentWordIndex < 0) {
      currentWordIndex = wordsSeen.size() - 1;
    }
    this.updateImage();
  }

  /*
   * Updates image viewer image to the next image from the words seen list.
   */
  @FXML
  private void onRightButton() throws URISyntaxException {
    MusicPlayer.playButtonSoundEffect(user);
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
    } else { // else set image to current index in list from appropriate user folder
      file =
          new File(
              "src/main/resources/users/user"
                  + id
                  + "/images/"
                  + wordsSeen.get(currentWordIndex)
                  + ".png");
    }
    // set image to image viewer
    Image image = new Image(file.toURI().toString());
    drawingImage.setImage(image);
    // update the image label
    this.updateImageName();
  }

  private void updateImageName() {

    String currentWord;

    if (wordsSeen.size() == 0) { // if no past games then set waiting message
      currentWord = "Play a game to see your old doodles!";
    } else { // else set image label to formatted drawing name
      currentWord = wordsSeen.get(currentWordIndex);
      currentWord = currentWord.replace("-", " ");
      currentWord =
          currentWord.substring(0, 1).toUpperCase()
              + currentWord.substring(1).toLowerCase(); // formats
      // so
      // the
      // first
      // letter
      // is
      // captialised
    }
    drawingLabel.setText(currentWord); // set text to the label
  }

  /**
   * On back button click this will set the scene back to the user select scene
   *
   * @param event
   * @throws URISyntaxException
   */
  @FXML
  private void onBackButton(ActionEvent event) throws URISyntaxException {
    MusicPlayer.playButtonSoundEffect(user);
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page back to user select
      currentScene.setRoot(App.loadFxml("userprofilescene"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On back button click this will set the scene to the user stats scene
   *
   * @param event
   * @throws URISyntaxException
   */
  @FXML
  private void onStatsButton(ActionEvent event) throws URISyntaxException {
    MusicPlayer.playButtonSoundEffect(user);
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page to user stats (memories page)
      currentScene.setRoot(App.loadFxml("memories"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On back button click this will set the scene to the easy game
   *
   * @param event
   * @throws URISyntaxException
   */
  @FXML
  private void onEasyButton(ActionEvent event) throws URISyntaxException {
    MusicPlayer.playButtonSoundEffect(user);
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page to easy game
      currentScene.setRoot(App.loadFxml("canvas"));
      Window window = currentScene.getWindow();
      window.setWidth(810); // set window width to 810
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
