package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class BadgesController {

  @FXML private ImageView imageLogo;
  @FXML private Label bronzeTimeLabel;
  @FXML private Label silverTimeLabel;
  @FXML private Label goldTimeLabel;
  @FXML private Label legendaryTimeLabel;
  @FXML private Label bronzeConfLabel;
  @FXML private Label silverConfLabel;
  @FXML private Label goldConfLabel;
  @FXML private Label legendaryConfLabel;
  @FXML private Label bronzeStreakLabel;
  @FXML private Label silverStreakLabel;
  @FXML private Label goldStreakLabel;
  @FXML private Label legendaryStreakLabel;
  @FXML private Label bronzeWordLabel;
  @FXML private Label silverWordLabel;
  @FXML private Label goldWordLabel;
  @FXML private Label legendaryWordLabel;
  @FXML private Label bronzeAccuracyLabel;
  @FXML private Label silverAccuracyLabel;
  @FXML private Label goldAccuracyLabel;
  @FXML private Label LegendaryGamesLabel;
  private Label[] badgeArray;
  private static User user;

  public void initialize() {
    badgeArray =
        new Label[] {
          bronzeTimeLabel,
          silverTimeLabel,
          goldTimeLabel,
          legendaryTimeLabel,
          bronzeConfLabel,
          silverConfLabel,
          goldConfLabel,
          legendaryConfLabel,
          bronzeStreakLabel,
          silverStreakLabel,
          goldStreakLabel,
          legendaryStreakLabel,
          bronzeWordLabel,
          silverWordLabel,
          goldWordLabel,
          legendaryWordLabel,
          bronzeAccuracyLabel,
          silverAccuracyLabel,
          goldAccuracyLabel,
          LegendaryGamesLabel
        };
    user = User.getUser(UserHomeController.id);
    displayBadges();
  }

  /**
   * This function will get the users badge states and appropriately enable the labels for the
   * badges the user has won. All badge labels are disabled by default
   */
  @FXML
  private void displayBadges() {
    boolean[] badgesWon = user.getBadges();
    // go through all badges, if badge true (won) then enable display of appropriate label
    for (int i = 0; i < 20; i++) {
      if (badgesWon[i]) {
        badgeArray[i].setDisable(false);
      }
    }
  }

  /**
   * This function is too be called by game functions if a user has won a game. This should only be
   * called after all stats have been updated - DO NOT CALL IF USER HAS LOST THE GAME - DO NOT CALL
   * BEFORE ANY UPDATING STATISTICS OR USER JSON VALUES. This function must also be passed in the
   * user id and the number of seconds the user had left in the game.
   *
   * @param userId the integer id of the user the badges need to be updated for
   * @param gameTimeLeft the time left on a winning game
   * @return return the number of badges the player has earned in this game
   */
  public static int updateBadges(int userId, int gameTimeLeft) {
    user = User.getUser(userId);
    boolean[] badgeState = user.getBadges();
    int badgesWon = 0;

    // time badges
    if (gameTimeLeft <= 30) { // enable win in 30 sec
      if (!badgeState[0]) {
        badgesWon++;
      }
      badgeState[0] = true;
    }
    if (gameTimeLeft <= 20) { // enable win in 20 sec
      if (!badgeState[1]) {
        badgesWon++;
      }
      badgeState[1] = true;
    }
    if (gameTimeLeft <= 10) { // enable win in 10 sec
      if (!badgeState[2]) {
        badgesWon++;
      }
      badgeState[2] = true;
    }
    if (gameTimeLeft <= 5) { // enable win in 5 sec
      if (!badgeState[3]) {
        badgesWon++;
      }
      badgeState[3] = true;
    }

    // confidence badges
    switch (user.getConfidenceDifficulty()) {
      case EASY:
        if (!badgeState[4]) {
          badgesWon++;
        }
        badgeState[4] = true;
        break;
      case MEDIUM:
        if (!badgeState[5]) {
          badgesWon++;
        }
        badgeState[5] = true;
        break;
      case HARD:
        if (!badgeState[6]) {
          badgesWon++;
        }
        badgeState[6] = true;
        break;
      case MASTER:
        if (!badgeState[7]) {
          badgesWon++;
        }
        badgeState[7] = true;
        break;
    }

    // streak badges
    switch (user.getWinStreak()) {
      case 5:
        if (!badgeState[8]) {
          badgesWon++;
        }
        badgeState[8] = true;
        break;
      case 10:
        if (!badgeState[9]) {
          badgesWon++;
        }
        badgeState[9] = true;
        break;
      case 15:
        if (!badgeState[10]) {
          badgesWon++;
        }
        badgeState[10] = true;
        break;
      case 20:
        if (!badgeState[11]) {
          badgesWon++;
        }
        badgeState[11] = true;
        break;
    }

    // words badges
    switch (user.getWordDifficulty()) {
      case EASY:
        if (!badgeState[12]) {
          badgesWon++;
        }
        badgeState[12] = true;
        break;
      case MEDIUM:
        if (!badgeState[13]) {
          badgesWon++;
        }
        badgeState[13] = true;
        break;
      case HARD:
        if (!badgeState[14]) {
          badgesWon++;
        }
        badgeState[14] = true;
        break;
      case MASTER:
        if (!badgeState[15]) {
          badgesWon++;
        }
        badgeState[15] = true;
        break;
    }

    // accuracy badges
    switch (user.getAccuracyDifficulty()) {
      case EASY:
        if (!badgeState[16]) {
          badgesWon++;
        }
        badgeState[16] = true;
        break;
      case MEDIUM:
        if (!badgeState[17]) {
          badgesWon++;
        }
        badgeState[17] = true;
        break;
      case HARD:
        if (!badgeState[18]) {
          badgesWon++;
        }
        badgeState[18] = true;
        break;
      default:
        break;
    }

    // games won badge
    if (user.getGamesWon() == 100) {
      if (!badgeState[19]) {
        badgesWon++;
      }
      badgeState[19] = true;
    }

    user.setBadges(badgeState);
    user.saveData();

    return badgesWon;
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
      // change scene from badges page back to user home
      currentScene.setRoot(App.loadFxml("userHome"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
