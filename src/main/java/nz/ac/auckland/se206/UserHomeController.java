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
  @FXML private Button gameButton;
  @FXML private Label streakLabel;
  @FXML private ImageView streakImage;
  @FXML private Button storeButton;
  @FXML private Button badgesButton;
  @FXML private Button memoriesButton;
  @FXML private Button settingsButton;
  @FXML private ImageView backgroundImage;
  private User user;
  private List<String> wordsSeen;
  private int currentWordIndex = 0;

  /**
   * After userHome fxml and controller have been constructed this will set the current user and get
   * needed details. This method will also set the initial image in the image viewer - either to a
   * waiting image or the corresponding image from the first word in their played list.
   */
  public void initialize() {

    user = User.getUser(id);
    nameLabel.setText("Hi, " + user.getName() + "!"); // set the greeting label
    wordsSeen = user.getWordsSeen();
    if (wordsSeen.size() > 0) {
      currentWordIndex = wordsSeen.size() - 1;
    }
    // load in users drawing as an image
    this.updateImage();
    // set streak image and value
    setStreak();

    try {
      MusicPlayer.muteBackgroundSong(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // set background image
    File file = new File("src/main/resources/images/shopImages/" + user.getCurrentBackground());
    Image image = new Image(file.toURI().toString());
    backgroundImage.setImage(image);
  }

  /**
   * Sets the appropriate image and value for the streak fire depending on the users current win
   * streak
   */
  @FXML
  private void setStreak() {
    File file;
    if (user.getWinStreak() == 0) {
      streakLabel.setText("");
      file = new File("src/main/resources/images/user_home_text/blue-fire.png");
    } else {
      streakLabel.setText(String.valueOf(user.getWinStreak()));
      file = new File("src/main/resources/images/user_home_text/red-fire.png");
    }
    Image image = new Image(file.toURI().toString());
    streakImage.setImage(image);
  }

  /*
   * Updates image viewer image to the previous image from words seen list.
   */
  @FXML
  private void onLeftButton() {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
  private void onRightButton() {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
   * @param event Button click that triggers function call
   */
  @FXML
  private void onBackButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
   * @param event Button click that triggers function call
   */
  @FXML
  private void onStatsButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
   * On game button click this will set the scene to the normal game
   *
   * @param event Button click that triggers function call
   */
  @FXML
  private void onGameButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page to normal game
      currentScene.setRoot(App.loadFxml("canvas"));

      Window window = currentScene.getWindow();
      window.setWidth(810); // set window width to 810
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On zen mode click this will set the scene to the zen mode scene
   *
   * @param event Button click that triggers function call
   */
  @FXML
  private void onZenButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page to zen game //currently set to normal game
      // as filler
      currentScene.setRoot(App.loadFxml("zenMode"));
      Window window = currentScene.getWindow();
      window.setWidth(810); // set window width to 810
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On definition mode click this will set the scene to the definition mode scene
   *
   * @param event Button click that triggers function call
   */
  @FXML
  private void onDefinitionButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from user home page to definition game //currently set to normal
      // game as
      // filler
      currentScene.setRoot(App.loadFxml("definitions"));
      Window window = currentScene.getWindow();
      window.setWidth(810); // set window width to 810
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On settings button click this will set the scene to the settings scene
   *
   * @param event Button click that triggers function call
   */
  @FXML
  private void onSettingsButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene to settings
      currentScene.setRoot(App.loadFxml("settings"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On memories button click this will set the scene to the memories scene
   *
   * @param event Button click that triggers function call
   */
  @FXML
  private void onMemoriesButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene to memories
      currentScene.setRoot(App.loadFxml("memories"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On badges button click this will set the scene to the badges scene
   *
   * @param event Button click that triggers function call
   */
  @FXML
  private void onBadgesButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene to badges //currently set to memories
      currentScene.setRoot(App.loadFxml("badges"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * On shop button click this will set the scene to the shop scene
   *
   * @param event Button click that triggers function call
   */
  @FXML
  private void onShopButton(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene to shop //currently set to memories
      currentScene.setRoot(App.loadFxml("shoppage"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
