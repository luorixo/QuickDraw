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
  private User user = User.getUser(UserHomeController.id);

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

    if (backgroundsOwned[1]) { // if owns city image
      File file = new File("src/main/resources/images/shopImages/city_background.png");
      Image image = new Image(file.toURI().toString());
      cityImage.setImage(image);
      File fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      Image imageButton = new Image(fileButton.toURI().toString());
      cityButtonImage.setImage(imageButton);
    }
    if (backgroundsOwned[2]) { // if owns dessert image
      File file = new File("src/main/resources/images/shopImages/dessert_background.png");
      Image image = new Image(file.toURI().toString());
      dessertImage.setImage(image);
      File fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      Image imageButton = new Image(fileButton.toURI().toString());
      dessertButtonImage.setImage(imageButton);
    }
    if (backgroundsOwned[3]) { // if owns log image
      File file = new File("src/main/resources/images/shopImages/log_background.png");
      Image image = new Image(file.toURI().toString());
      logImage.setImage(image);
      File fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      Image imageButton = new Image(fileButton.toURI().toString());
      logButtonImage.setImage(imageButton);
    }
    if (backgroundsOwned[4]) { // if owns sky image
      File file = new File("src/main/resources/images/shopImages/sky_background.png");
      Image image = new Image(file.toURI().toString());
      skyImage.setImage(image);
      File fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      Image imageButton = new Image(fileButton.toURI().toString());
      skyButtonImage.setImage(imageButton);
    }
    if (backgroundsOwned[5]) { // if owns space image
      File file = new File("src/main/resources/images/shopImages/space_background.png");
      Image image = new Image(file.toURI().toString());
      spaceImage.setImage(image);
      File fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      Image imageButton = new Image(fileButton.toURI().toString());
      spaceButtonImage.setImage(imageButton);
    }
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
   * @param event
   */
  @FXML
  private void onButton(ActionEvent event) {
    Button button = (Button) event.getSource();
    String buttonId = button.getId();

    switch (buttonId) {
      case "purpleButton": // purple is always owned so no need to check
        user.setCurrentBackground("purple_background.png");
        break;
      case "cityButton":
        if (user.getOwnedBackgrounds()[1]) { // if owned and hence on use
          user.setCurrentBackground("city_background.png");
        } else { // else trying to buy
          buyBackground("city_background.png", 1);
        }
        break;
      case "dessertButton":
        if (user.getOwnedBackgrounds()[2]) { // if owned and hence on use
          user.setCurrentBackground("dessert_background.png");
        } else { // else trying to buy
          buyBackground("dessert_background.png", 2);
        }
        break;
      case "logButton":
        if (user.getOwnedBackgrounds()[3]) { // if owned and hence on use
          user.setCurrentBackground("log_background.png");
        } else { // else trying to buy
          buyBackground("log_background.png", 3);
        }
        break;
      case "skyButton":
        if (user.getOwnedBackgrounds()[4]) { // if owned and hence on use
          user.setCurrentBackground("sky_background.png");
        } else { // else trying to buy
          buyBackground("sky_background.png", 4);
        }
        break;
      case "spaceButton":
        if (user.getOwnedBackgrounds()[5]) { // if owned and hence on use
          user.setCurrentBackground("space_background.png");
        } else { // else trying to buy
          buyBackground("space_background.png", 5);
        }
        break;
    }
    user.saveData();
  }

  /**
   * This function will allow the user to buy a background is they can afford it. If they can then
   * the background is bought, image is unlocked, json is updated and button switches to use. Each
   * background costs 500
   *
   * @param background the name of the background the user wishes to buy
   * @param backgroundNum the number of the background
   */
  private void buyBackground(String background, int backgroundNum) {
    if (user.getCoins() >= 500) { // if the user can pay
      user.addCoins(-500); // charge
      // get unlocked images and use images
      File file = new File("src/main/resources/images/shopImages/" + background);
      Image image = new Image(file.toURI().toString());
      File fileButton = new File("src/main/resources/images/shopImages/use_button.png");
      Image imageButton = new Image(fileButton.toURI().toString());
      // set images to appropriate image views
      switch (backgroundNum) {
        case 1: // city
          cityImage.setImage(image);
          cityButtonImage.setImage(imageButton);
          user.setOwnedBackgrounds(1);
          break;
        case 2: // dessert
          dessertImage.setImage(image);
          dessertButtonImage.setImage(imageButton);
          user.setOwnedBackgrounds(2);
          break;
        case 3: // log
          logImage.setImage(image);
          logButtonImage.setImage(imageButton);
          user.setOwnedBackgrounds(3);
          break;
        case 4: // sky
          skyImage.setImage(image);
          skyButtonImage.setImage(imageButton);
          user.setOwnedBackgrounds(4);
          break;
        case 5: // space
          spaceImage.setImage(image);
          spaceButtonImage.setImage(imageButton);
          user.setOwnedBackgrounds(5);
          break;
      }

      user.saveData();
      coinsLabel.setText(String.valueOf(user.getCoins()));
    }
  }
}
