package nz.ac.auckland.se206;

import ai.djl.ModelException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import nz.ac.auckland.se206.dict.DictionaryLookup;
import nz.ac.auckland.se206.dict.WordNotFoundException;
import nz.ac.auckland.se206.words.CategorySelector;

/**
 * This is the controller of the canvas. You are free to modify this class and the corresponding
 * FXML file as you see fit. For example, you might no longer need the "Predict" button because the
 * DL model should be automatically queried in the background every second.
 *
 * <p>!! IMPORTANT !!
 *
 * <p>Although we added the scale of the image, you need to be careful when changing the size of the
 * drawable canvas and the brush size. If you make the brush too big or too small with respect to
 * the canvas size, the ML model will not work correctly. So be careful. If you make some changes in
 * the canvas and brush sizes, make sure that the prediction works fine.
 */
public class PreDefinitionsController {

  @FXML private Label categoryLabel;
  @FXML private Label timeLabel;
  @FXML private Label hintLabel;
  @FXML private Button skipButton;

  private int userId = UserHomeController.id;
  private User user = User.getUser(userId);
  private int startingTime = 30;
  private int secondsLeft = startingTime;
  public static String randomWord;
  public static String randomDefinition;
  private String hint;
  private TimerTask timedTask;

  /**
   * endGame is called whenever the player has won/run out of time. It sets certain nodes
   * off/invisible
   */
  private void endGame() {
    skipButton.fire();
  }

  /**
   * JavaFX calls this method once the GUI elements are loaded. In our case we create a listener for
   * the drawing, and we load the ML model.
   *
   * @throws ModelException If there is an error in reading the input/output of the DL model.
   * @throws IOException If the model cannot be found on the file system.
   */
  public void initialize() throws ModelException, IOException {
    Timer timer = new Timer();

    CategorySelector categorySelector = new CategorySelector();
    randomWord =
        categorySelector.getRandomCategory(
            user.getWordDifficulty()); // sets depending on difficulty
    while (true) {
      try {
        randomDefinition =
            DictionaryLookup.searchWordInfo(randomWord)
                .getWordEntries()
                .get(0)
                .getDefinitions()
                .get(0);
        break;

      } catch (IOException | WordNotFoundException e) {
        randomWord = categorySelector.getRandomCategory(user.getWordDifficulty());
      }
    }

    // creates task to speak the random category name
    Task<Void> sayCategoryTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            MusicPlayer.TextToSpeech(user, randomDefinition);
            this.cancel();
            return null;
          }
        };

    // assigns speaking task to thread
    Thread newThreadTwo = new Thread(sayCategoryTask);
    newThreadTwo.setDaemon(true);
    newThreadTwo.start();

    // create a new timer task for updating top 10 predictions list
    timedTask =
        new TimerTask() {
          public void run() {
            secondsLeft--;
            Platform.runLater(
                () -> {
                  timeLabel.setText(String.valueOf(secondsLeft)); // sets timer
                });
            if (secondsLeft == 0) {
              this.cancel();
              timer.cancel();
              endGame();
            }
          }
        };

    // creates new thread task for updating top 10 predictions list
    Task<Void> updateTimerTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            timer.scheduleAtFixedRate(timedTask, 1000, 1000);
            return null;
          }
        };

    // creates new thread and assigns tasks
    Thread newThread = new Thread(updateTimerTask);
    newThread.setDaemon(true);
    newThread.start();

    System.out.println(randomWord);

    categoryLabel.setText(randomDefinition);
    categoryLabel.setWrapText(true);

    this.hint = "_".repeat(randomWord.length());
    hintLabel.setText(this.hint);

    System.out.println(randomDefinition);
    timeLabel.setText(String.valueOf(secondsLeft));
  }

  /**
   * This method is called when the user wants to go back to the user home
   *
   * @param event current event
   */
  @FXML
  private void onSkip(ActionEvent event) {
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();
    timedTask.cancel();

    try {
      Window window = currentScene.getWindow();
      window.setWidth(810); // set window width to 810
      // restarts game back to the home page
      currentScene.setRoot(App.loadFxml("definitions"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
