package nz.ac.auckland.se206;

import ai.djl.ModelException;
import ai.djl.translate.TranslateException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import nz.ac.auckland.se206.ml.DoodlePrediction;
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
public class ZenModeController {

  @FXML private Canvas canvas;
  @FXML private Label categoryLabel;
  @FXML private ListView<?> predictionsList;
  @FXML private Button clearButton;
  @FXML private Button backButton;
  @FXML private Button eraseButton;
  @FXML private Button blackPaintButton;
  @FXML private Button redPaintButton;
  @FXML private Button orangePaintButton;
  @FXML private Button yellowPaintButton;
  @FXML private Button lightGreenPaintButton;
  @FXML private Button greenPaintButton;
  @FXML private Button bluePaintButton;
  @FXML private Button lightBluePaintButton;
  @FXML private Button purplePaintButton;
  @FXML private Button pinkPaintButton;
  @FXML private ImageView questionMark;
  @FXML private ImageView lightbulb;

  @FXML private Pane canvasPane;
  @FXML private Button readyButton;

  private int userId = UserHomeController.id;
  private User user = User.getUser(userId);
  private GraphicsContext graphic;
  private DoodlePrediction model;
  private int startingTime = 5;
  private int secondsLeft = 60;
  private int predictionWinNumber = 3;
  private boolean gameEnd = false;

  private String randomWord;

  // mouse coordinates
  private double currentX;
  private double currentY;

  /** This method here starts the game when the user is ready */
  @FXML
  private void onStartGame() {
    allPaintButtonsVisible(true);
    readyButton.setVisible(false);
    eraseButton.setVisible(true);
    clearButton.setVisible(true);
    user = User.getUser(userId);
    try {
      MusicPlayer.startButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Timer timer = new Timer();
    // creates task to speak the random category name

    Task<Void> sayCategoryTask =
        new Task<Void>() {

          @Override
          protected Void call() throws Exception {
            MusicPlayer.TextToSpeech(user, randomWord);
            this.cancel();
            return null;
          }
        };

    // assigns speaking task to thread
    Thread newThreadTwo = new Thread(sayCategoryTask);
    newThreadTwo.setDaemon(true);
    newThreadTwo.start();

    // create a new timer task for updating top 10 predictions list
    TimerTask timedTask =
        new TimerTask() {
          public void run() {
            if (gameEnd) {
              this.cancel(); // cancels timer task
              timer.cancel();
            } else {
              Platform.runLater(
                  () -> {
                    displayPrediction(); // places top 10 predictions into list
                  });
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

    canvas.setDisable(false);
    canvasPane.setVisible(true);
    questionMark.setVisible(false);
  }

  /** finishGame is called whenever the player has pressed the back button */
  private void finishGame() {

    this.user.addToWordsSeen(randomWord);

    try {
      // creates image directory if it doesn't exist
      Files.createDirectories(
          Paths.get(
              System.getProperty("user.dir")
                  + "/src/main/resources/users/user"
                  + userId
                  + "/images"));
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    File newImage =
        new File(
            System.getProperty("user.dir")
                + "/src/main/resources/users/user"
                + userId
                + "/images/"
                + randomWord.replaceAll("\\s", "-")
                + ".png");

    try {
      ImageIO.write(getCurrentSnapshotColour(), "png", newImage);
    } catch (IOException e) {
      e.printStackTrace();
    } // Save the image to a file
  }

  /**
   * This method sets the brush type either eraser or a pencil
   *
   * @param brushType the colour of the drawing
   * @param isErase whether the user is wanting to erase
   */
  private void setBrushType(Color brushType, boolean isErase) {
    canvas.setOnMousePressed(
        e -> {
          currentX = e.getX();
          currentY = e.getY();
        });

    canvas.setOnMouseDragged(
        e -> {
          try {
            MusicPlayer.drawingSoundEffect(user);
          } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          // Brush size (you can change this, it should not be too small or too large).
          double size = 6;
          if (isErase) {
            size = 20;
          }

          final double x = e.getX() - size / 2;
          final double y = e.getY() - size / 2;

          // This is the colour of the brush.
          graphic.setStroke(brushType);
          graphic.setLineWidth(size);

          // Create a line that goes from the point (currentX, currentY) and (x,y)
          graphic.strokeLine(currentX, currentY, x, y);

          // update the coordinates
          currentX = x;
          currentY = y;
        });
  }

  /** displayPrediction task gets the predictions from the DL model and puts it into the list */
  private void displayPrediction() {

    BufferedImage thisImage = getCurrentSnapshot(); // current canvas image as BufferedImage

    // sets the prediction task
    Task<Void> predictTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            ObservableList predictions =
                (ObservableList)
                    DoodlePrediction.getBasePredictionsList(
                        model.getPredictions(thisImage, 10)); // get top 10 predictions

            boolean isInTop =
                DoodlePrediction.getPredictionsList(
                        model.getPredictions(thisImage, predictionWinNumber))
                    .contains(randomWord); // get
            // top
            // predictions
            // based
            Platform.runLater(
                () -> {
                  // puts the top 10 predictions in the list
                  predictionsList.setItems(predictions);
                  if (isInTop && secondsLeft != startingTime) {
                    File file =
                        new File(
                            System.getProperty("user.dir")
                                + "/src/main/resources/images/canvas_images/excitedBulb.png");
                    Image image = new Image(file.toURI().toString());
                    lightbulb.setImage(image);
                  } else {
                    try {
                      if (DoodlePrediction.getBasePredictionsList(
                              model.getPredictions(thisImage, 10))
                          .contains(randomWord)) {
                        File file =
                            new File(
                                System.getProperty("user.dir")
                                    + "/src/main/resources/images/canvas_images/clueBulb.png");
                        Image image = new Image(file.toURI().toString());
                        lightbulb.setImage(image);
                      } else if (DoodlePrediction.getBasePredictionsList(
                              model.getPredictions(thisImage, 20))
                          .contains(randomWord)) {
                        File file =
                            new File(
                                System.getProperty("user.dir")
                                    + "/src/main/resources/images/canvas_images/confusedBulb.png");
                        Image image = new Image(file.toURI().toString());
                        lightbulb.setImage(image);
                      } else if (DoodlePrediction.getBasePredictionsList(
                              model.getPredictions(thisImage, 30))
                          .contains(randomWord)) {
                        File file =
                            new File(
                                System.getProperty("user.dir")
                                    + "/src/main/resources/images/canvas_images/sadBulb.png");
                        Image image = new Image(file.toURI().toString());
                        lightbulb.setImage(image);
                      } else {
                        File file =
                            new File(
                                System.getProperty("user.dir")
                                    + "/src/main/resources/images/canvas_images/angryBulb.png");
                        Image image = new Image(file.toURI().toString());
                        lightbulb.setImage(image);
                      }
                    } catch (TranslateException e) {
                      e.printStackTrace();
                    }
                  }
                });
            return null;
          }
        };

    // assigns the task
    Thread newThread = new Thread(predictTask);
    newThread.setDaemon(true);
    newThread.start();
  }

  /**
   * JavaFX calls this method once the GUI elements are loaded. In our case we create a listener for
   * the drawing, and we load the ML model.
   *
   * @throws ModelException If there is an error in reading the input/output of the DL model.
   * @throws IOException If the model cannot be found on the file system.
   */
  public void initialize() throws ModelException, IOException {
    graphic = canvas.getGraphicsContext2D();

    eraseButton.setVisible(false);
    clearButton.setVisible(false);

    // makes the brush black by default
    setBrushType(Color.BLACK, false);

    model = new DoodlePrediction();
    // displayPrediction(); // puts top 10 guesses on the listview

    CategorySelector categorySelector = new CategorySelector();
    randomWord = categorySelector.getRandomCategory(user.getWordDifficulty()); // sets to easy mode

    canvas.setDisable(true);
    canvasPane.setVisible(true);
    categoryLabel.setText(randomWord);

    allPaintButtonsVisible(false);
  }

  /** This method is called when the "Clear" button is pressed. */
  @FXML
  private void onClear() {
    graphic.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
  }

  /**
   * This method changes the colour of the pencil drawings
   *
   * @param event this allows us to get the fxid of what is clicked
   */
  @FXML
  private void onPaint(ActionEvent event) {

    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Object penButton = event.getTarget();

    if (penButton.equals(blackPaintButton)) {

      setBrushType(Color.BLACK, false);

    } else if (penButton.equals(redPaintButton)) {

      setBrushType(Color.RED, false);

    } else if (penButton.equals(orangePaintButton)) {

      setBrushType(Color.ORANGE, false);

    } else if (penButton.equals(yellowPaintButton)) {

      setBrushType(Color.YELLOW, false);

    } else if (penButton.equals(greenPaintButton)) {

      setBrushType(Color.GREEN, false);

    } else if (penButton.equals(lightGreenPaintButton)) {

      setBrushType(Color.LIGHTGREEN, false);

    } else if (penButton.equals(bluePaintButton)) {

      setBrushType(Color.BLUE, false);

    } else if (penButton.equals(lightBluePaintButton)) {

      setBrushType(Color.LIGHTBLUE, false);

    } else if (penButton.equals(purplePaintButton)) {

      setBrushType(Color.PURPLE, false);

    } else {
      setBrushType(Color.PINK, false);
    }
  }

  /** This function allows the user to erase the drawings of what they have done */
  @FXML
  private void onErase() {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    setBrushType(Color.WHITE, true);
  }

  /**
   * This method executes when the user clicks the "Predict" button. It gets the current drawing,
   * queries the DL model and prints on the console the top 5 predictions of the DL model and the
   * elapsed time of the prediction in milliseconds.
   *
   * @throws TranslateException If there is an error in reading the input/output of the DL model.
   */
  @FXML
  private void onPredict() throws TranslateException {}

  @FXML
  private void onBackHome(ActionEvent event) {
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    finishGame();

    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      Window window = currentScene.getWindow();
      window.setWidth(610);
      // restarts game back to the home page
      currentScene.setRoot(App.loadFxml("userHome"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function allows the user to save their own image
   *
   * @param event allows us to get the stage of the window
   */
  @FXML
  private void onSaveImage(ActionEvent event) {
    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    try {
      MusicPlayer.playButtonSoundEffect(user);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      // saves the image
      saveCurrentSnapshotOnFile(stage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get the current snapshot of the canvas.
   *
   * @return The BufferedImage corresponding to the current canvas content.
   */
  private BufferedImage getCurrentSnapshot() {
    final Image snapshot = canvas.snapshot(null, null);
    final BufferedImage image = SwingFXUtils.fromFXImage(snapshot, null);

    // Convert into a binary image.
    final BufferedImage imageBinary =
        new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

    final Graphics2D graphics = imageBinary.createGraphics();

    graphics.drawImage(image, 0, 0, null);

    // To release memory we dispose.
    graphics.dispose();

    return imageBinary;
  }

  /**
   * Get the current snapshot of the canvas in colour.
   *
   * @return The BufferedImage corresponding to the current canvas content.
   */
  private BufferedImage getCurrentSnapshotColour() {
    final Image snapshot = canvas.snapshot(null, null);
    final BufferedImage image = SwingFXUtils.fromFXImage(snapshot, null);

    // Convert into a binary image.
    final BufferedImage imageBinary =
        new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_USHORT_565_RGB);

    final Graphics2D graphics = imageBinary.createGraphics();

    graphics.drawImage(image, 0, 0, null);

    // To release memory we dispose.
    graphics.dispose();

    return imageBinary;
  }

  /**
   * Save the current snapshot on a bitmap file.
   *
   * @return The file of the saved image.
   * @throws IOException If the image cannot be saved.
   */
  private File saveCurrentSnapshotOnFile(Stage stage) throws IOException {
    // file chooser so the user can choose directory/name of the file to save
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save");
    fileChooser.setInitialFileName("your-" + randomWord + "-drawing");
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("BMP files (*.bmp)", "*.bmp"));
    File tmpFolder = fileChooser.showSaveDialog(stage);

    final File imageToClassify = new File(tmpFolder.getCanonicalPath() + ".bmp");
    ImageIO.write(getCurrentSnapshotColour(), "bmp", imageToClassify); // Save the image to a file

    return imageToClassify;
  }

  /**
   * This sets all the pencils the user will use to (in)visible
   *
   * @param visible is the boolean to change the visibility of the pencils
   */
  private void allPaintButtonsVisible(boolean visible) {
    blackPaintButton.setVisible(visible);

    redPaintButton.setVisible(visible);

    orangePaintButton.setVisible(visible);

    yellowPaintButton.setVisible(visible);

    lightGreenPaintButton.setVisible(visible);

    greenPaintButton.setVisible(visible);

    bluePaintButton.setVisible(visible);

    lightBluePaintButton.setVisible(visible);

    purplePaintButton.setVisible(visible);

    pinkPaintButton.setVisible(visible);
  }
}
