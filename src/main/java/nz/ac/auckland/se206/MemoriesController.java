package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MemoriesController {

  @FXML private Button buttonBack;
  @FXML private Label statLabel;
  @FXML private ImageView imageLogo;
  @FXML private Label statTitleLabel;
  @FXML private Label statValueLabel;
  @FXML private Label thoughtLabel;
  @FXML private ImageView bowBulbImage;
  @FXML private ImageView glassesBulbImage;
  @FXML private ImageView littleBulbImage;
  @FXML private ImageView partyBulbImage;
  @FXML private ImageView jumpingBulbImage;
  @FXML private Button nextButton;
  private int statCycleNum;
  private User user = User.getUser(UserHomeController.id);

  /**
   * Called on the initialisation of the FXML scene, this function will set the scene to show the
   * first statistic - games played
   */
  public void initialize() {
    statCycleNum = 1;
    // set to default statistic display
    displayGamesPlayed();
  }

  /** Handles the switching of statistics displays when the next button is clicked. */
  @FXML
  private void onNextButton() {
    // depending on the cycle head display the appropriate display
    switch (statCycleNum) {
      case 1:
        displayGamesWon();
        break;
      case 2:
        displayGamesLost();
        break;
      case 3:
        displayBestTime();
        break;
      case 4:
        displayAverageTime();
        break;
      case 5:
        displayBestStreak();
        break;
      default:
        displayGamesPlayed();
    }
    // update cycle number for loop
    statCycleNum += 1;
    if (statCycleNum >= 7) {
      statCycleNum = 1;
    }
  }

  /**
   * Function will change the memories FXMl so only the flag bulb image is visible. it will also set
   * the labels to show the title and value of games played by the user. This function will also set
   * the thoughts text depending on the number of games the user has played.
   */
  @FXML
  private void displayGamesPlayed() {
    // set visible images (flag bulb is always visible by default)
    bowBulbImage.setVisible(false);
    glassesBulbImage.setVisible(false);
    littleBulbImage.setVisible(false);
    partyBulbImage.setVisible(false);
    jumpingBulbImage.setVisible(false);

    statTitleLabel.setText("Games Played");
    statValueLabel.setText(String.valueOf(user.getGamesPlayed()));

    // set thoughts text
    if (user.getGamesPlayed() == 0) {
      thoughtLabel.setText("Play some games and come back!");
    } else if (user.getGamesPlayed() > 50) {
      thoughtLabel.setText("WOW! That is so many drawings!");
    } else {
      thoughtLabel.setText("WooHoo! Wanna play some more?");
    }
  }

  /**
   * Function will change visible images, title text, value text and thoughts text to appropriate
   * values according to the number of games the user has won. This function will make the bow bulb
   * visible.
   */
  @FXML
  private void displayGamesWon() {
    // set visible images (flag bulb is always visible by default)
    bowBulbImage.setVisible(true);
    glassesBulbImage.setVisible(false);
    littleBulbImage.setVisible(false);
    partyBulbImage.setVisible(false);
    jumpingBulbImage.setVisible(false);

    statTitleLabel.setText("Games Won");
    statValueLabel.setText(String.valueOf(user.getGamesWon()));

    // set thoughts text
    if (user.getGamesWon() == 0) {
      thoughtLabel.setText("Win some games and come back!");
    } else if (user.getGamesWon() > 20) {
      thoughtLabel.setText("That has to be a record!");
    } else {
      thoughtLabel.setText("WOW!!! YOU ARE A WINNER!");
    }
  }

  /**
   * Function will change visible images, title text, value text and thoughts text to appropriate
   * values according to the number of games the user has lost. This function will make the bow and
   * glasses bulb visible.
   */
  @FXML
  private void displayGamesLost() {
    // set visible images (flag bulb is always visible by default)
    bowBulbImage.setVisible(true);
    glassesBulbImage.setVisible(true);
    littleBulbImage.setVisible(false);
    partyBulbImage.setVisible(false);
    jumpingBulbImage.setVisible(false);

    statTitleLabel.setText("Games Lost");
    statValueLabel.setText(String.valueOf(user.getGamesLost()));

    // set thoughts text
    if (user.getGamesLost() == 0) {
      thoughtLabel.setText("You are doing so well!");
    } else if (user.getGamesLost() > 20) {
      thoughtLabel.setText("Its ok! Im proud of you for trying.");
    } else {
      thoughtLabel.setText("Thats ok! Drawing is hard!");
    }
    if (user.getGamesPlayed() == 0) {
      thoughtLabel.setText("You haven't played any games though...");
    }
  }

  /**
   * Function will change visible images, title text, value text and thoughts text to appropriate
   * values according to the users best time. This function will make the bow, glasses and little
   * bulb visible.
   */
  @FXML
  private void displayBestTime() {
    // set visible images (flag bulb is always visible by default)
    bowBulbImage.setVisible(true);
    glassesBulbImage.setVisible(true);
    littleBulbImage.setVisible(true);
    partyBulbImage.setVisible(false);
    jumpingBulbImage.setVisible(false);

    statTitleLabel.setText("Best Time");
    statValueLabel.setText(String.valueOf(user.getBestTime()));

    // set thoughts text and update value text if no best time
    if (user.getGamesWon() == 0) {
      statValueLabel.setText("...");
      thoughtLabel.setText("Oh I Cant Remember!");
    } else {
      thoughtLabel.setText(user.getBestWord() + ", I remember!");
    }
  }

  /**
   * Function will change visible images, title text, value text and thoughts text to appropriate
   * values according to the users average time. This function will make the bow, glasses, little
   * and party bulb visible.
   */
  @FXML
  private void displayAverageTime() {
    // set visible images (flag bulb is always visible by default)
    bowBulbImage.setVisible(true);
    glassesBulbImage.setVisible(true);
    littleBulbImage.setVisible(true);
    partyBulbImage.setVisible(true);
    jumpingBulbImage.setVisible(false);

    statTitleLabel.setText("Average Time");
    statValueLabel.setText(String.valueOf(user.getAverageTime()));

    // set thoughts text and update value text if no average time
    if (user.getGamesPlayed() == 0) {
      statValueLabel.setText("...");
      thoughtLabel.setText("Go play a few games!");
    } else if (user.getAverageTime() < 30) {
      thoughtLabel.setText("Such good consistency!");
    } else {
      thoughtLabel.setText("It's so hard to draw fast");
    }
  }

  /**
   * Function will change visible images, title text, value text and thoughts text to appropriate
   * values according to the users best win streak. This function will make the bow, glasses,
   * little, party and jumping bulb visible visible.
   */
  @FXML
  private void displayBestStreak() {
    // set visible images (flag bulb is always visible by default)
    bowBulbImage.setVisible(true);
    glassesBulbImage.setVisible(true);
    littleBulbImage.setVisible(true);
    partyBulbImage.setVisible(true);
    jumpingBulbImage.setVisible(true);

    statTitleLabel.setText("Best Streak");
    statValueLabel.setText(String.valueOf(user.getBestWinStreak()));

    // set thoughts text
    if (user.getBestWinStreak() == 0) {
      thoughtLabel.setText("Thats ok! You'll win soon!");
    } else if (user.getBestWinStreak() >= 20) {
      thoughtLabel.setText("Wow! You are the next Picasso!");
    } else {
      thoughtLabel.setText("Good Job! Can you get higher?");
    }

    if (user.getGamesPlayed() == 0) {
      thoughtLabel.setText("Go play some games!");
    }
  }

  /**
   * On back button click this will set the scene back to the user home
   *
   * @param event Button click that triggers function call
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
