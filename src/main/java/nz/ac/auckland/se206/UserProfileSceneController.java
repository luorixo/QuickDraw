package nz.ac.auckland.se206;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Avinaash Krishnan
 */
public class UserProfileSceneController {

  // GUI elements for userOne Note: is it possible to use a constructor in order
  // to create fxml elements?
  @FXML private Pane userProfileOne;
  @FXML private ImageView userIconOne;
  @FXML private Label usernameOne;
  @FXML private ImageView addUserOne;
  @FXML private Button userButtonOne;
  @FXML private ImageView deleteUserOne;
  @FXML private ImageView redDeleteUserOne;
  @FXML private Label cancelDeleteOne;
  @FXML private ImageView yourNameOne;
  @FXML private TextField userCreateOne;
  private User userOne = User.getUser(1);

  // GUI elements for userTwo
  @FXML private Pane userProfileTwo;
  @FXML private ImageView userIconTwo;
  @FXML private Label usernameTwo;
  @FXML private ImageView addUserTwo;
  @FXML private Button userButtonTwo;
  @FXML private ImageView deleteUserTwo;
  @FXML private ImageView redDeleteUserTwo;
  @FXML private Label cancelDeleteTwo;
  @FXML private ImageView yourNameTwo;
  @FXML private TextField userCreateTwo;
  private User userTwo = User.getUser(2);

  // GUI elements for userThree
  @FXML private Pane userProfileThree;
  @FXML private ImageView userIconThree;
  @FXML private Label usernameThree;
  @FXML private ImageView addUserThree;
  @FXML private Button userButtonThree;
  @FXML private ImageView deleteUserThree;
  @FXML private ImageView redDeleteUserThree;
  @FXML private Label cancelDeleteThree;
  @FXML private ImageView yourNameThree;
  @FXML private TextField userCreateThree;
  private User userThree = User.getUser(3);

  // GUI elements for userFour
  @FXML private Pane userProfileFour;
  @FXML private ImageView userIconFour;
  @FXML private Label usernameFour;
  @FXML private ImageView addUserFour;
  @FXML private Button userButtonFour;
  @FXML private ImageView deleteUserFour;
  @FXML private ImageView redDeleteUserFour;
  @FXML private Label cancelDeleteFour;
  @FXML private ImageView yourNameFour;
  @FXML private TextField userCreateFour;
  private User userFour = User.getUser(4);

  /**
   * This function is called after the GUI elements have been created. This function sets up all
   * four possible users
   */
  public void initialize() {

    // all four functions cal the set up functions for the specific users
    setUserOne();
    setUserTwo();
    setUserThree();
    setUserFour();
  }

  /**
   * @param event tells us the fxid of the element that initiated the action
   *     <p>This function controls the button for each of the user profiles. If a user has not been
   *     created, it will help the user create their own account. If the user has already been
   *     created, the button will lead them their user home page
   * @throws URISyntaxException
   */
  @FXML
  private void onUserCreationOrHomePage(ActionEvent event) throws URISyntaxException {

    MusicPlayer.playButtonSoundEffect();

    // checks to see which fxid the event is from
    Object userCreateButton = event.getSource();

    // based on the fxid, GUI elements for specific users are disabled/visible

    // this if statement checks if the action is relevant to user one
    if (userCreateButton.equals(userButtonOne)) {

      // checks to see if user one has been created
      if (!userOne.hasBeenCreated()) {

        // sets the relevant GUIS as the user needs to create their user
        userIconOne.setVisible(false);
        usernameOne.setVisible(false);
        addUserOne.setVisible(false);
        deleteUserOne.setVisible(false);
        deleteUserOne.setDisable(true);
        cancelDeleteOne.setVisible(false);
        cancelDeleteOne.setDisable(true);
        redDeleteUserOne.setVisible(false);
        redDeleteUserOne.setDisable(true);
        userButtonOne.setDisable(true);
        yourNameOne.setVisible(true);
        userCreateOne.setVisible(true);
        userCreateOne.setDisable(false);

        // disables all buttons so that the user cannot click out
        disableAllButtons();

        // if user has been created, send them to their designated scene
      } else {
        Scene currentScene = userButtonOne.getScene();
        try {
          // change scene from user switch scene to user home page

          // this sets their user id to 1, so they next scene knows that user one is
          // accessing the scene
          UserHomeController.id = 1;
          currentScene.setRoot(App.loadFxml("userHome"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      // this if statement checks if the action is relevant to user two
    } else if (userCreateButton.equals(userButtonTwo)) {

      // checks to see if user two has been created
      if (!userTwo.hasBeenCreated()) {

        // sets the relevant GUIS as the user needs to create their user
        userIconTwo.setVisible(false);
        usernameTwo.setVisible(false);
        addUserTwo.setVisible(false);
        deleteUserTwo.setVisible(false);
        deleteUserTwo.setDisable(true);
        cancelDeleteTwo.setVisible(false);
        cancelDeleteTwo.setDisable(true);
        redDeleteUserTwo.setVisible(false);
        redDeleteUserTwo.setDisable(true);
        userButtonTwo.setDisable(true);
        yourNameTwo.setVisible(true);
        userCreateTwo.setVisible(true);
        userCreateTwo.setDisable(false);

        // disables all buttons so that the user cannot click out
        disableAllButtons();

        // if user has been created, send them to their designated scene
      } else {
        Scene currentScene = userButtonTwo.getScene();
        try {
          // change scene from user switch scene to user home page
          // this sets their user id to 2, so they next scene knows that user two is
          // accessing the scene
          UserHomeController.id = 2;
          currentScene.setRoot(App.loadFxml("userHome"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      // this if statement checks if the action is relevant to user three
    } else if (userCreateButton.equals(userButtonThree)) {

      // checks to see if user three has been created
      if (!userThree.hasBeenCreated()) {

        // sets the relevant GUIS as the user needs to create their user
        userIconThree.setVisible(false);
        usernameThree.setVisible(false);
        addUserThree.setVisible(false);
        deleteUserThree.setVisible(false);
        deleteUserThree.setDisable(true);
        cancelDeleteThree.setVisible(false);
        cancelDeleteThree.setDisable(true);
        redDeleteUserThree.setVisible(false);
        redDeleteUserThree.setDisable(true);
        userButtonThree.setDisable(true);
        yourNameThree.setVisible(true);
        userCreateThree.setVisible(true);
        userCreateThree.setDisable(false);

        // disables all buttons so that the user cannot click out
        disableAllButtons();

        // if user has been created, send them to their designated scene
      } else {
        Scene currentScene = userButtonThree.getScene();
        try {
          // change scene from user switch scene to user home page
          // this sets their user id to 3, so they next scene knows that user three is
          // accessing the scene
          UserHomeController.id = 3;
          currentScene.setRoot(App.loadFxml("userHome"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      // this if statement checks if the action is relevant to user four
    } else {

      // checks to see if user four has been created
      if (!userFour.hasBeenCreated()) {

        // sets the relevant GUIS as the user needs to create their user
        userIconFour.setVisible(false);
        usernameFour.setVisible(false);
        addUserFour.setVisible(false);
        deleteUserFour.setVisible(false);
        deleteUserFour.setDisable(true);
        cancelDeleteFour.setVisible(false);
        cancelDeleteFour.setDisable(true);
        redDeleteUserFour.setVisible(false);
        redDeleteUserFour.setDisable(true);
        userButtonFour.setDisable(true);
        yourNameFour.setVisible(true);
        userCreateFour.setVisible(true);
        userCreateFour.setDisable(false);

        // disables all buttons so that the user cannot click out
        disableAllButtons();

        // if user has been created, send them to their designated scene
      } else {
        Scene currentScene = userButtonFour.getScene();
        try {
          // change scene from user switch scene to user home page
          // this sets their user id to 4, so they next scene knows that user four is
          // accessing the scene
          UserHomeController.id = 4;
          currentScene.setRoot(App.loadFxml("userHome"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * @param event tells us the fxid of the element that initiated the action
   *     <p>This function allows the user the option of deleting their profiles
   * @throws URISyntaxException
   */
  @FXML
  private void onDeleteUser(MouseEvent event) throws URISyntaxException {

    MusicPlayer.playButtonSoundEffect();

    // Checks to see which fxid the event is associated to
    Object deleteUserImage = event.getSource();

    // disables all other buttons
    disableAllButtons();

    // based on the fxid, these if/else statements dictate which GUI elements are
    // visible/disabled

    //// this if statement checks if the action is relevant to user one
    if (deleteUserImage.equals(deleteUserOne)) {

      deleteUserOne.setVisible(false);
      deleteUserOne.setDisable(true);
      redDeleteUserOne.setVisible(true);
      redDeleteUserOne.setDisable(false);
      cancelDeleteOne.setVisible(true);
      cancelDeleteOne.setDisable(false);
      userButtonOne.setDisable(true);

      // this if statement checks if the action is relevant to user two
    } else if (deleteUserImage.equals(deleteUserTwo)) {

      deleteUserTwo.setVisible(false);
      deleteUserTwo.setDisable(true);
      redDeleteUserTwo.setVisible(true);
      redDeleteUserTwo.setDisable(false);
      cancelDeleteTwo.setVisible(true);
      cancelDeleteTwo.setDisable(false);
      userButtonTwo.setDisable(true);

      // this if statement checks if the action is relevant to user three
    } else if (deleteUserImage.equals(deleteUserThree)) {

      deleteUserThree.setVisible(false);
      deleteUserThree.setDisable(true);
      redDeleteUserThree.setVisible(true);
      redDeleteUserThree.setDisable(false);
      cancelDeleteThree.setVisible(true);
      cancelDeleteThree.setDisable(false);
      userButtonThree.setDisable(true);

      // this if statement checks if the action is relevant to user four
    } else {

      deleteUserFour.setVisible(false);
      deleteUserFour.setDisable(true);
      redDeleteUserFour.setVisible(true);
      redDeleteUserFour.setDisable(false);
      cancelDeleteFour.setVisible(true);
      cancelDeleteFour.setDisable(false);
      userButtonFour.setDisable(true);
    }
  }

  /**
   * @param event tells us the fxid of the element that initiated the action
   *     <p>This function allows the user to cancel the deletion process if they accidentally
   *     pressed the deletion button
   * @throws URISyntaxException
   */
  @FXML
  private void onCancelDeletion(MouseEvent event) throws URISyntaxException {

    MusicPlayer.playButtonSoundEffect();

    Object cancelDeleteImage = event.getSource();

    // enabling all other buttons
    enableAllButtons();

    // this if statement checks if the action is relevant to user one
    if (cancelDeleteImage.equals(cancelDeleteOne)) {
      deleteUserOne.setVisible(true);
      deleteUserOne.setDisable(false);
      redDeleteUserOne.setVisible(false);
      redDeleteUserOne.setDisable(true);
      cancelDeleteOne.setVisible(false);
      cancelDeleteOne.setDisable(true);
      userButtonOne.setDisable(false);

      // this if statement checks if the action is relevant to user two
    } else if (cancelDeleteImage.equals(cancelDeleteTwo)) {
      deleteUserTwo.setVisible(true);
      deleteUserTwo.setDisable(false);
      redDeleteUserTwo.setVisible(false);
      redDeleteUserTwo.setDisable(true);
      cancelDeleteTwo.setVisible(false);
      cancelDeleteTwo.setDisable(true);
      userButtonTwo.setDisable(false);

      // this if statement checks if the action is relevant to user three
    } else if (cancelDeleteImage.equals(cancelDeleteThree)) {
      deleteUserThree.setVisible(true);
      deleteUserThree.setDisable(false);
      redDeleteUserThree.setVisible(false);
      redDeleteUserThree.setDisable(true);
      cancelDeleteThree.setVisible(false);
      cancelDeleteThree.setDisable(true);
      userButtonThree.setDisable(false);

      // this if statement checks if the action is relevant to user four
    } else {
      deleteUserFour.setVisible(true);
      deleteUserFour.setDisable(false);
      redDeleteUserFour.setVisible(false);
      redDeleteUserFour.setDisable(true);
      cancelDeleteFour.setVisible(false);
      cancelDeleteFour.setDisable(true);
      userButtonFour.setDisable(false);
    }
  }

  /**
   * @param event tells us the fxid of the element that initiated the action
   *     <p>This function allows the user to confirm the deletion process
   * @throws URISyntaxException
   */
  @FXML
  private void onConfirmDeletion(MouseEvent event) throws URISyntaxException {

    MusicPlayer.playButtonSoundEffect();

    Object confirmDeletionImage = event.getSource();

    // enabling all other buttons
    enableAllButtons();

    // this if statement checks if the action is relevant to user one
    if (confirmDeletionImage.equals(redDeleteUserOne)) {

      // shows the relevant GUIS as the user wants to delete their account
      cancelDeleteOne.setVisible(false);
      cancelDeleteOne.setDisable(true);
      userIconOne.setVisible(false);
      usernameOne.setVisible(false);
      addUserOne.setVisible(true);
      deleteUserOne.setVisible(false);
      deleteUserOne.setDisable(true);
      redDeleteUserOne.setVisible(false);
      redDeleteUserOne.setDisable(true);
      userButtonOne.setDisable(false);

      // we now need to reset the user as the user has opted to delete their user
      userOne.resetUser();

      // this if statement checks if the action is relevant to user two
    } else if (confirmDeletionImage.equals(redDeleteUserTwo)) {

      // shows the relevant GUIS as the user wants to delete their account
      cancelDeleteTwo.setVisible(false);
      cancelDeleteTwo.setDisable(true);
      userIconTwo.setVisible(false);
      usernameTwo.setVisible(false);
      addUserTwo.setVisible(true);
      deleteUserTwo.setVisible(false);
      deleteUserTwo.setDisable(true);
      redDeleteUserTwo.setVisible(false);
      redDeleteUserTwo.setDisable(true);
      userButtonTwo.setDisable(false);

      // we now need to reset the user as the user has opted to delete their user
      userTwo.resetUser();

      // this if statement checks if the action is relevant to user three
    } else if (confirmDeletionImage.equals(redDeleteUserThree)) {

      // shows the relevant GUIS as the user wants to delete their account
      cancelDeleteThree.setVisible(false);
      cancelDeleteThree.setDisable(true);
      userIconThree.setVisible(false);
      usernameThree.setVisible(false);
      addUserThree.setVisible(true);
      deleteUserThree.setVisible(false);
      deleteUserThree.setDisable(true);
      redDeleteUserThree.setVisible(false);
      redDeleteUserThree.setDisable(true);
      userButtonThree.setDisable(false);

      // we now need to reset the user as the user has opted to delete their user
      userThree.resetUser();

      // this if statement checks if the action is relevant to user four
    } else {

      // shows the relevant GUIS as the user wants to delete their account
      cancelDeleteFour.setVisible(false);
      cancelDeleteFour.setDisable(true);
      userIconFour.setVisible(false);
      usernameFour.setVisible(false);
      addUserFour.setVisible(true);
      deleteUserFour.setVisible(false);
      deleteUserFour.setDisable(true);
      redDeleteUserFour.setVisible(false);
      redDeleteUserFour.setDisable(true);
      userButtonFour.setDisable(false);

      // we now need to reset the user as the user has opted to delete their user
      userFour.resetUser();
    }
  }

  /**
   * @param event tells us the fxid of the element that initiated the action
   *     <p>This method allows the user to enter their own personal username. It then sets the
   *     username using the user class, and then resets the specific user
   * @throws URISyntaxException
   */
  @FXML
  private void onNameEntered(ActionEvent event) throws URISyntaxException {

    MusicPlayer.playButtonSoundEffect();

    Object userCreateTextField = event.getSource();

    // this if statement checks if the action is relevant to user one
    if (userCreateTextField.equals(userCreateOne)) {

      String username = userCreateOne.getText();
      this.userOne = new User(username, 1);
      userCreateOne.clear();
      enableAllButtons();
      setUserOne();

      // this if statement checks if the action is relevant to user two
    } else if (userCreateTextField.equals(userCreateTwo)) {

      String username = userCreateTwo.getText();
      this.userTwo = new User(username, 2);
      userCreateTwo.clear();
      enableAllButtons();
      setUserTwo();

      // this if statement checks if the action is relevant to user three
    } else if (userCreateTextField.equals(userCreateThree)) {

      String username = userCreateThree.getText();
      this.userThree = new User(username, 3);
      userCreateThree.clear();
      enableAllButtons();
      setUserThree();

      // this if statement checks if the action is relevant to user four
    } else {

      String username = userCreateFour.getText();
      this.userFour = new User(username, 4);
      userCreateFour.clear();
      enableAllButtons();
      setUserFour();
    }
  }

  /**
   * This function sets user One. It enables the correct GUI elements depending on whether user one
   * has been created.
   */
  private void setUserOne() {

    // these GUI elements should be set to these values no matter if the user has
    // been or has not been created
    cancelDeleteOne.setVisible(false);
    cancelDeleteOne.setDisable(true);
    redDeleteUserOne.setVisible(false);
    redDeleteUserOne.setDisable(true);
    yourNameOne.setVisible(false);
    userCreateOne.setVisible(false);
    userCreateOne.setDisable(true);

    // if a user has been created, we should make sure the Icon image, the username
    // label and the delete user images are visible.
    if (this.userOne.hasBeenCreated()) {

      userIconOne.setVisible(true);
      usernameOne.setVisible(true);
      usernameOne.setText(userOne.getName());
      addUserOne.setVisible(false);
      deleteUserOne.setVisible(true);
      deleteUserOne.setDisable(false);

      // if a user has not been created, we should make sure the Icon image, the
      // username
      // label and the delete user images are not visible.
    } else {

      userIconOne.setVisible(false);
      usernameOne.setVisible(false);
      addUserOne.setVisible(true);
      deleteUserOne.setVisible(false);
      deleteUserOne.setDisable(true);
    }
  }

  /**
   * This function sets user Two. It enables the correct GUI elements depending on whether user two
   * has been created.
   */
  private void setUserTwo() {

    // these GUI elements should be set to these values no matter if the user has
    // been or has not been created
    cancelDeleteTwo.setVisible(false);
    cancelDeleteTwo.setDisable(true);
    redDeleteUserTwo.setVisible(false);
    redDeleteUserTwo.setDisable(true);
    yourNameTwo.setVisible(false);
    userCreateTwo.setVisible(false);
    userCreateTwo.setDisable(true);

    // if a user has been created, we should make sure the Icon image, the username
    // label and the delete user images are visible.
    if (this.userTwo.hasBeenCreated()) {

      userIconTwo.setVisible(true);
      usernameTwo.setVisible(true);
      usernameTwo.setText(userTwo.getName());
      addUserTwo.setVisible(false);
      deleteUserTwo.setVisible(true);
      deleteUserTwo.setDisable(false);

      // if a user has not been created, we should make sure the Icon image, the
      // username
      // label and the delete user images are not visible.
    } else {

      userIconTwo.setVisible(false);
      usernameTwo.setVisible(false);
      addUserTwo.setVisible(true);
      deleteUserTwo.setVisible(false);
      deleteUserTwo.setDisable(true);
    }
  }

  /**
   * This function sets user Three. It enables the correct GUI elements depending on whether user
   * three has been created.
   */
  private void setUserThree() {

    // these GUI elements should be set to these values no matter if the user has
    // been or has not been created

    cancelDeleteThree.setVisible(false);
    cancelDeleteThree.setDisable(true);
    redDeleteUserThree.setVisible(false);
    redDeleteUserThree.setDisable(true);
    yourNameThree.setVisible(false);
    userCreateThree.setVisible(false);
    userCreateThree.setDisable(true);

    // if a user has been created, we should make sure the Icon image, the username
    // label and the delete user images are visible.
    if (this.userThree.hasBeenCreated()) {

      userIconThree.setVisible(true);
      usernameThree.setVisible(true);
      usernameThree.setText(userThree.getName());
      addUserThree.setVisible(false);
      deleteUserThree.setVisible(true);
      deleteUserThree.setDisable(false);

      // if a user has not been created, we should make sure the Icon image, the
      // username
      // label and the delete user images are not visible.
    } else {

      userIconThree.setVisible(false);
      usernameThree.setVisible(false);
      usernameFour.setText(userFour.getName());
      addUserThree.setVisible(true);
      deleteUserThree.setVisible(false);
      deleteUserThree.setDisable(true);
    }
  }

  /**
   * This function sets user Four. It enables the correct GUI elements depending on whether user
   * four has been created.
   */
  private void setUserFour() {

    // these GUI elements should be set to these values no matter if the user has
    // been or has not been created

    cancelDeleteFour.setVisible(false);
    cancelDeleteFour.setDisable(true);
    redDeleteUserFour.setVisible(false);
    redDeleteUserFour.setDisable(true);
    yourNameFour.setVisible(false);
    userCreateFour.setVisible(false);
    userCreateFour.setDisable(true);

    // if a user has been created, we should make sure the Icon image, the username
    // label and the delete user images are visible.
    if (this.userFour.hasBeenCreated()) {

      userIconFour.setVisible(true);
      usernameFour.setVisible(true);
      usernameFour.setText(userFour.getName());
      addUserFour.setVisible(false);
      deleteUserFour.setVisible(true);
      deleteUserFour.setDisable(false);

      // if a user has not been created, we should make sure the Icon image, the
      // username
      // label and the delete user images are not visible.
    } else {

      userIconFour.setVisible(false);
      usernameFour.setVisible(false);
      addUserFour.setVisible(true);
      deleteUserFour.setVisible(false);
      deleteUserFour.setDisable(true);
    }
  }

  /**
   * This function disables all relevant buttons when a user is in the process of creating a new
   * user
   */
  private void disableAllButtons() {
    // This function disables all relevant buttons when a user is in the process of
    // creating a new user
    userButtonOne.setDisable(true);
    userButtonTwo.setDisable(true);
    userButtonThree.setDisable(true);
    userButtonFour.setDisable(true);
    deleteUserOne.setDisable(true);
    deleteUserTwo.setDisable(true);
    deleteUserThree.setDisable(true);
    deleteUserFour.setDisable(true);
  }

  /**
   * This function enables all relevant buttons when a user has just finished creating a new account
   */
  private void enableAllButtons() {

    // setting all these buttons to false, as the user has finished making their
    // account, so they are free to click buttons
    userButtonOne.setDisable(false);
    userButtonTwo.setDisable(false);
    userButtonThree.setDisable(false);
    userButtonFour.setDisable(false);
    deleteUserOne.setDisable(false);
    deleteUserTwo.setDisable(false);
    deleteUserThree.setDisable(false);
    deleteUserFour.setDisable(false);
  }
}
