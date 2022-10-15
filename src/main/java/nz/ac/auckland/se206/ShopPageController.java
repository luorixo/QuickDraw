package nz.ac.auckland.se206;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShopPageController {

  @FXML private ImageView logoImage;
  @FXML private Button backButton;
  @FXML private Label coinsLabel;
  @FXML private ImageView cityImage;
  @FXML private ImageView dessertImage;
  @FXML private ImageView logImage;
  @FXML private ImageView skyImage;
  @FXML private ImageView spaceImage;
  @FXML private ImageView purpleButtonImage;
  @FXML private ImageView cityButtonImage;
  @FXML private ImageView dessertButtonImage;
  @FXML private ImageView logButtonImage;
  @FXML private ImageView skyButtonImage;
  @FXML private ImageView spaceButtonImage;
  @FXML private Button purpleButton;
  @FXML private Button cityButton;
  @FXML private Button dessertButton;
  @FXML private Button logButton;
  @FXML private Button skyButton;
  @FXML private Button spaceButton;
  @FXML private Button cityCancel;
  @FXML private Button dessertCancel;
  @FXML private Button logCancel;
  @FXML private Button skyCancel;
  @FXML private Button spaceCancel;
  private User user = User.getUser(UserHomeController.id);

  /**
   * This function is called when the FXML is initialized. THis will set the correct display
   * depending on the users state for each background.
   */
  public void initialize() {
    displayOwned();
    coinsLabel.setText("$ " + user.getCoins());
  }

  /**
   * This function will update shop images to the unlocked versions if the user owns the background.
   * It will also change the buy button to the use button.
   */
  @FXML
  private void displayOwned() {
    boolean[] backgroundsOwned = user.getOwnedBackgrounds();
    File fileButton;
    // setting button image
    if (user.getCurrentBackground().equals("purple_background.png")) {
      fileButton = new File("src/main/resources/images/shopImages/equipped-button.png");
    } else {
      fileButton = new File("src/main/resources/images/shopImages/use_button.png");
    }

    Image imageButton = new Image(fileButton.toURI().toString());
    purpleButtonImage.setImage(imageButton);

    if (backgroundsOwned[1]) { // if owns city image
      File file = new File("src/main/resources/images/shopImages/city_background.png");
      Image image = new Image(file.toURI().toString());
      cityImage.setImage(image);
      // setting button image
      if (user.getCurrentBackground().equals("city_background.png")) {
        fileButton = new File("src/main/resources/images/shopImages/equipped-button.png");
      } else {
        fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      }
      imageButton = new Image(fileButton.toURI().toString());
      cityButtonImage.setImage(imageButton);
    } else { // else set cost button image
      fileButton = new File("src/main/resources/images/shopImages/cost-button.png");
      imageButton = new Image(fileButton.toURI().toString());
      cityButtonImage.setImage(imageButton);
    }
    if (backgroundsOwned[2]) { // if owns dessert image
      File file = new File("src/main/resources/images/shopImages/dessert_background.png");
      Image image = new Image(file.toURI().toString());
      dessertImage.setImage(image);
      // setting button image
      if (user.getCurrentBackground().equals("dessert_background.png")) {
        fileButton = new File("src/main/resources/images/shopImages/equipped-button.png");
      } else {
        fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      }
      imageButton = new Image(fileButton.toURI().toString());
      dessertButtonImage.setImage(imageButton);

    } else { // else set cost button image
      fileButton = new File("src/main/resources/images/shopImages/cost-button.png");
      imageButton = new Image(fileButton.toURI().toString());
      dessertButtonImage.setImage(imageButton);
    }
    if (backgroundsOwned[3]) { // if owns log image
      File file = new File("src/main/resources/images/shopImages/log_background.png");
      Image image = new Image(file.toURI().toString());
      logImage.setImage(image);
      // setting button image
      if (user.getCurrentBackground().equals("log_background.png")) {
        fileButton = new File("src/main/resources/images/shopImages/equipped-button.png");
      } else {
        fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      }
      imageButton = new Image(fileButton.toURI().toString());
      logButtonImage.setImage(imageButton);

    } else { // else set cost button image
      fileButton = new File("src/main/resources/images/shopImages/cost-button.png");
      imageButton = new Image(fileButton.toURI().toString());
      logButtonImage.setImage(imageButton);
    }
    if (backgroundsOwned[4]) { // if owns sky image
      File file = new File("src/main/resources/images/shopImages/sky_background.png");
      Image image = new Image(file.toURI().toString());
      skyImage.setImage(image);
      // setting button image
      if (user.getCurrentBackground().equals("sky_background.png")) {
        fileButton = new File("src/main/resources/images/shopImages/equipped-button.png");
      } else {
        fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      }
      imageButton = new Image(fileButton.toURI().toString());
      skyButtonImage.setImage(imageButton);

    } else { // else set cost button image
      fileButton = new File("src/main/resources/images/shopImages/cost-button.png");
      imageButton = new Image(fileButton.toURI().toString());
      skyButtonImage.setImage(imageButton);
    }
    if (backgroundsOwned[5]) { // if owns space image
      File file = new File("src/main/resources/images/shopImages/space_background.png");
      Image image = new Image(file.toURI().toString());
      spaceImage.setImage(image);
      // setting button image
      if (user.getCurrentBackground().equals("space_background.png")) {
        fileButton = new File("src/main/resources/images/shopImages/equipped-button.png");
      } else {
        fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      }
      imageButton = new Image(fileButton.toURI().toString());
      spaceButtonImage.setImage(imageButton);

    } else { // else set cost button image
      fileButton = new File("src/main/resources/images/shopImages/cost-button.png");
      imageButton = new Image(fileButton.toURI().toString());
      spaceButtonImage.setImage(imageButton);
    }

    // set all cancel buttons to disabled and invisible
    cityCancel.setDisable(true);
    cityCancel.setVisible(false);
    dessertCancel.setDisable(true);
    dessertCancel.setVisible(false);
    logCancel.setDisable(true);
    logCancel.setVisible(false);
    skyCancel.setDisable(true);
    skyCancel.setVisible(false);
    spaceCancel.setDisable(true);
    spaceCancel.setVisible(false);
  }

  /**
   * On back button click this will set the scene back to the user home
   *
   * @param event Button click that will trigger this function call - back button
   */
  @FXML
  private void onBackButton(ActionEvent event) {
    Button button = (Button) event.getSource();
    Scene currentScene = button.getScene();

    try {
      // change scene from settings page back to user home
      currentScene.setRoot(App.loadFxml("userHome"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function is used by the buttons in the shop fxml. It will call the appropriate buy or set
   * function depending on the state of the buttons.
   *
   * @param event Button event that will trigger function call - $500, buy, use button
   */
  @FXML
  private void onButton(ActionEvent event) {
    Button button = (Button) event.getSource();
    String buttonId = button.getId();
    switch (buttonId) {
      case "purpleButton": // purple is always owned so no need to check
        user.setCurrentBackground("purple_background.png");
        displayOwned();
        break;
      case "cityButton":
        if (user.getOwnedBackgrounds()[1]) { // if owned and hence on use
          user.setCurrentBackground("city_background.png");
          displayOwned();
        } else { // else trying to buy
          confirmOrBuy(1);
        }
        break;
      case "dessertButton":
        if (user.getOwnedBackgrounds()[2]) { // if owned and hence on use
          user.setCurrentBackground("dessert_background.png");
          displayOwned();
        } else { // else trying to buy
          confirmOrBuy(2);
        }
        break;
      case "logButton":
        if (user.getOwnedBackgrounds()[3]) { // if owned and hence on use
          user.setCurrentBackground("log_background.png");
          displayOwned();
        } else { // else trying to buy
          confirmOrBuy(3);
        }
        break;
      case "skyButton":
        if (user.getOwnedBackgrounds()[4]) { // if owned and hence on use
          user.setCurrentBackground("sky_background.png");
          displayOwned();
        } else { // else trying to buy
          confirmOrBuy(4);
        }
        break;
      case "spaceButton":
        if (user.getOwnedBackgrounds()[5]) { // if owned and hence on use
          user.setCurrentBackground("space_background.png");
          displayOwned();
        } else { // else trying to buy
          confirmOrBuy(5);
        }
        break;
    }
    user.saveData();
  }

  /**
   * This is the function called when the $500 or buy button is pressed. If the button has been
   * pressed for the first time then the buttons enter the confirm state. If the button is in the
   * confirm state then the payment and buying process goes through.
   *
   * @param backgroundNum int value of which background is being referred to , number matches index
   *     number of state in user json array
   */
  private void confirmOrBuy(int backgroundNum) {
    // get confirm buy image
    File file = new File("src/main/resources/images/shopImages/buy_button.png");
    Image image = new Image(file.toURI().toString());

    // check cancelled buttons, if disabled then go to confirm stage, else go to buy stage
    switch (backgroundNum) {
      case 1: // city
        if (cityCancel.isDisabled()) {
          cityButtonImage.setImage(image);
          cityCancel.setDisable(false);
          cityCancel.setVisible(true);
        } else {
          buyBackground(backgroundNum);
        }
        break;
      case 2: // dessert
        if (dessertCancel.isDisabled()) {
          dessertButtonImage.setImage(image);
          dessertCancel.setDisable(false);
          dessertCancel.setVisible(true);
        } else {
          buyBackground(backgroundNum);
        }
        break;
      case 3: // log
        if (logCancel.isDisabled()) {
          logButtonImage.setImage(image);
          logCancel.setDisable(false);
          logCancel.setVisible(true);
        } else {
          buyBackground(backgroundNum);
        }
        break;
      case 4: // sky
        if (skyCancel.isDisabled()) {
          skyButtonImage.setImage(image);
          skyCancel.setDisable(false);
          skyCancel.setVisible(true);
        } else {
          buyBackground(backgroundNum);
        }
        break;
      case 5: // space
        if (spaceCancel.isDisabled()) {
          spaceButtonImage.setImage(image);
          spaceCancel.setDisable(false);
          spaceCancel.setVisible(true);
        } else {
          buyBackground(backgroundNum);
        }
        break;
    }
  }

  /**
   * This function will allow the user to buy a background is they can afford it. If they can then
   * the background is bought, image is unlocked, json is updated and button switches to use. Each
   * background costs 500
   *
   * @param background the name of the background the user wishes to buy
   * @param backgroundNum the number of the background
   */
  private void buyBackground(int backgroundNum) {
    if (user.getCoins() >= 500) { // if the user can pay
      user.addCoins(-500); // charge
      // set images to appropriate image views, set current background and hide cancel buttons
      switch (backgroundNum) {
        case 1: // city
          user.setOwnedBackgrounds(1);
          cityCancel.setDisable(true);
          cityCancel.setVisible(false);
          displayOwned();
          break;
        case 2: // dessert
          user.setOwnedBackgrounds(2);
          dessertCancel.setDisable(true);
          dessertCancel.setVisible(false);
          displayOwned();
          break;
        case 3: // log
          user.setOwnedBackgrounds(3);
          logCancel.setDisable(true);
          logCancel.setVisible(false);
          displayOwned();
          break;
        case 4: // sky
          user.setOwnedBackgrounds(4);
          skyCancel.setDisable(true);
          skyCancel.setVisible(false);
          displayOwned();
          break;
        case 5: // space
          user.setOwnedBackgrounds(5);
          spaceCancel.setDisable(true);
          spaceCancel.setVisible(false);
          displayOwned();
          break;
      }

      user.saveData();
      coinsLabel.setText(String.valueOf(user.getCoins()));
    }
  }

  /**
   * This function is called when a cancel button is clicked. This will exit the button out of the
   * confirm buy state and hide the cancel button again.
   *
   * @param event Button event which triggers function - cancel red cross button
   */
  @FXML
  private void onCancelButton(ActionEvent event) {
    Button button = (Button) event.getSource();
    button.setDisable(true);
    button.setVisible(false);
    displayOwned();
  }
}
