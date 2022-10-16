package nz.ac.auckland.se206;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.ToolTipManager;

public class SettingsController {

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
  private User user = User.getUser(UserHomeController.id);

  /**
   * This function is called before the GUI elements are set up when the user enters the settings
   * page. It intialises the page.
   *
   * @throws URISyntaxException
   */
  public void initialize() throws URISyntaxException {

    // shorted tooltip delay
    ToolTipManager.sharedInstance().setInitialDelay(0);
    ToolTipManager.sharedInstance().setReshowDelay(0);

    // get current chosen difficulty and display for each label
    wordsLabel.setText(convertDifficultyToString(user.getWordDifficulty()));
    accuracyLabel.setText(convertDifficultyToString(user.getAccuracyDifficulty()));
    confidenceLabel.setText(convertDifficultyToString(user.getConfidenceDifficulty()));
    timeLabel.setText(convertDifficultyToString(user.getTimeDifficulty()));

    // if user current audio states are off then switch image from default (default is on)
    if (!user.getMusicState()) {
      File file = new File("src/main/resources/images/settings_text/no-music.png");
      Image image = new Image(file.toURI().toString());
      musicImage.setImage(image);
    }

    if (!user.getSoundEffectState()) {
      File file = new File("src/main/resources/images/settings_text/no-sound-effects.png");
      Image image = new Image(file.toURI().toString());
      soundImage.setImage(image);
    }
    if (!user.getTextToSpeechState()) {
      File file = new File("src/main/resources/images/settings_text/no-speech.png");
      Image image = new Image(file.toURI().toString());
      speechImage.setImage(image);
    }
  }

  /**
   * Converts and returns the string version of the difficulty enum. will return all caps e.g. EASY
   * MEDIUM HARD MASTER
   *
   * @param difficulty User.Difficutly enum input to be converted
   */
  private String convertDifficultyToString(User.Difficulty difficulty) {
    switch (difficulty) {
      case EASY:
        return "EASY";
      case MEDIUM:
        return "MEDIUM";
      case HARD:
        return "HARD";
      case MASTER:
        return "MASTER";
      default:
        return "";
    }
  }

  /**
   * This function will allow the left button to change the difficulty of the game, both the
   * appropriate display and the user json will be updated. This function will allow cycling between
   * EASY MEDIUM HARD and MASTER.
   *
   * @param event left button which has been clicked to trigger function
   */
  @FXML
  private void onLeftButtonFourDifficulty(ActionEvent event) {
    Button leftButton = (Button) event.getSource();
    String buttonName = leftButton.getId();
    User.Difficulty difficulty = null;
    String difficultyText = "";

    // play sound effect
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // get current difficulty of relevant metric
    switch (buttonName) {
      case "wordsLeft":
        difficulty = user.getWordDifficulty();
        break;
      case "confidenceLeft":
        difficulty = user.getConfidenceDifficulty();
        break;
      case "timeLeft":
        difficulty = user.getTimeDifficulty();
        break;
    }
    // left cycle for button
    switch (difficulty) {
      case EASY:
        difficulty = User.Difficulty.MASTER;
        difficultyText = "MASTER";
        break;
      case MEDIUM:
        difficulty = User.Difficulty.EASY;
        difficultyText = "EASY";
        break;
      case HARD:
        difficulty = User.Difficulty.MEDIUM;
        difficultyText = "MEDIUM";
        break;
      case MASTER:
        difficulty = User.Difficulty.HARD;
        difficultyText = "HARD";
        break;
    }

    // set user profile preference and display label the new difficulty
    switch (buttonName) {
      case "wordsLeft":
        user.setWordDifficulty(difficulty);
        wordsLabel.setText(difficultyText);
        break;
      case "confidenceLeft":
        user.setConfidenceDifficulty(difficulty);
        confidenceLabel.setText(difficultyText);
        break;
      case "timeLeft":
        user.setTimeDifficulty(difficulty);
        timeLabel.setText(difficultyText);
        break;
    }

    user.saveData();
  }

  /**
   * This function will allow the right button to change the difficulty of the game, both the
   * appropriate display and the user json will be updated. This function will allow cycling between
   * EASY MEDIUM HARD and MASTER.
   *
   * @param event right button which has been clicked to trigger function
   */
  @FXML
  private void onRightButtonFourDifficulty(ActionEvent event) {
    Button rightButton = (Button) event.getSource();
    String buttonName = rightButton.getId();
    User.Difficulty difficulty = null;
    String difficultyText = "";

    // play sound effect
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // get current difficulty of relevant metric
    switch (buttonName) {
      case "wordsRight":
        difficulty = user.getWordDifficulty();
        break;
      case "confidenceRight":
        difficulty = user.getConfidenceDifficulty();
        break;
      case "timeRight":
        difficulty = user.getTimeDifficulty();
        break;
    }
    // right cycle for button
    switch (difficulty) {
      case EASY:
        difficulty = User.Difficulty.MEDIUM;
        difficultyText = "MEDIUM";
        break;
      case MEDIUM:
        difficulty = User.Difficulty.HARD;
        difficultyText = "HARD";
        break;
      case HARD:
        difficulty = User.Difficulty.MASTER;
        difficultyText = "MASTER";
        break;
      case MASTER:
        difficulty = User.Difficulty.EASY;
        difficultyText = "EASY";
        break;
    }

    // set user profile preference and display label the new difficulty
    switch (buttonName) {
      case "wordsRight":
        user.setWordDifficulty(difficulty);
        wordsLabel.setText(difficultyText);
        break;
      case "confidenceRight":
        user.setConfidenceDifficulty(difficulty);
        confidenceLabel.setText(difficultyText);
        break;
      case "timeRight":
        user.setTimeDifficulty(difficulty);
        timeLabel.setText(difficultyText);
        break;
    }

    user.saveData();
  }

  /**
   * This function will allow the left button to change the difficulty of the game, both the
   * appropriate display and the user json will be updated. This function will allow cycling between
   * EASY MEDIUM and HARD.
   *
   * @param event left button which has been clicked to trigger function
   */
  @FXML
  private void onLeftButtonThreeDifficulty(ActionEvent event) {
    Button leftButton = (Button) event.getSource();
    String buttonName = leftButton.getId();
    User.Difficulty difficulty = null;
    String difficultyText = "";

    // play sound effect
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    if (buttonName.equals("accuracyLeft")) {
      difficulty = user.getAccuracyDifficulty();
    }
    // left cycle for button
    switch (difficulty) {
      case EASY:
        difficulty = User.Difficulty.HARD;
        difficultyText = "HARD";
        break;
      case MEDIUM:
        difficulty = User.Difficulty.EASY;
        difficultyText = "EASY";
        break;
      case HARD:
        difficulty = User.Difficulty.MEDIUM;
        difficultyText = "MEDIUM";
        break;
      case MASTER:
        break;
    }

    // set user profile preference and display label the new difficulty
    if (buttonName.equals("accuracyLeft")) {
      user.setAccuracyDifficulty(difficulty);
      accuracyLabel.setText(difficultyText);
    }

    user.saveData();
  }

  /**
   * This function will allow the right button to change the difficulty of the game, both the
   * appropriate display and the user json will be updated. This function will allow cycling between
   * EASY MEDIUM and HARD.
   *
   * @param event right button which has been clicked to trigger function
   */
  @FXML
  private void onRightButtonThreeDifficulty(ActionEvent event) {
    Button rightButton = (Button) event.getSource();
    String buttonName = rightButton.getId();
    User.Difficulty difficulty = null;
    String difficultyText = "";

    // play sound effect
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    if (buttonName.equals("accuracyRight")) {
      difficulty = user.getAccuracyDifficulty();
    }
    // left cycle for button
    switch (difficulty) {
      case EASY:
        difficulty = User.Difficulty.MEDIUM;
        difficultyText = "MEDIUM";
        break;
      case MEDIUM:
        difficulty = User.Difficulty.HARD;
        difficultyText = "HARD";
        break;
      case HARD:
        difficulty = User.Difficulty.EASY;
        difficultyText = "EASY";
        break;
      case MASTER:
        break;
    }

    // set user profile preference and display label the new difficulty
    if (buttonName.equals("accuracyRight")) {
      user.setAccuracyDifficulty(difficulty);
      accuracyLabel.setText(difficultyText);
    }

    user.saveData();
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

    // play sound effect
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    try {
      // change scene from settings page back to user home
      currentScene.setRoot(App.loadFxml("userHome"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Function will switch settings for text to speech. Will update image on button and will update
   * users json for preference.
   */
  @FXML
  private void onChangeSpeech() {
    Boolean speechOn = user.getTextToSpeechState(); // get user current speech to text settings
    File file;

    // play sound effect
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    if (speechOn) { // if on then turn off and change image
      user.setTextToSpeech(false);
      file = new File("src/main/resources/images/settings_text/no-speech.png");
    } else { // else off then turn on and change image
      user.setTextToSpeech(true);
      file = new File("src/main/resources/images/settings_text/speech.png");
    }
    // set new image
    Image image = new Image(file.toURI().toString());
    speechImage.setImage(image);

    user.saveData();
  }

  /**
   * Function will switch settings for music. Will update image on button and will update users json
   * for preference.
   *
   * @throws URISyntaxException
   */
  @FXML
  private void onChangeMusic() throws URISyntaxException {
    Boolean musicOn = user.getMusicState(); // get user current music settings
    File file;

    // play sound effect
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    if (musicOn) { // if on then turn off and change image
      user.setMusic(false);
      file = new File("src/main/resources/images/settings_text/no-music.png");
    } else { // else off then turn on and change image
      user.setMusic(true);
      file = new File("src/main/resources/images/settings_text/music.png");
    }
    // set new image
    Image image = new Image(file.toURI().toString());
    musicImage.setImage(image);

    user.saveData();

    MusicPlayer.muteBackgroundSong(user);
  }

  /**
   * Function will switch settings for sound effects. Will update image on button and will update
   * users json for preference.
   */
  @FXML
  private void onChangeSound() {
    Boolean soundOn = user.getSoundEffectState(); // get user current sound effects settings
    File file;

    // play sound effect
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    if (soundOn) { // if on then turn off and change image
      user.setSoundEffects(false);
      file = new File("src/main/resources/images/settings_text/no-sound-effects.png");
    } else { // else off then turn on and change image
      user.setSoundEffects(true);
      file = new File("src/main/resources/images/settings_text/sound-effects.png");
    }
    // set new image
    Image image = new Image(file.toURI().toString());
    soundImage.setImage(image);

    user.saveData();
  }
}
