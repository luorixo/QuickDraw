package nz.ac.auckland.se206;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class UserProfileSceneController {

	@FXML
	private Button userButtonOne;

	@FXML
	private Pane userProfileOne;

	@FXML
	private Button deleteUserButtonOne;

	@FXML
	private ImageView quickDrawLogo;

	@FXML
	private ImageView deleteUserOne;

	@FXML
	private ImageView redDeleteUserOne;

	@FXML
	private Label cancelDeleteOne;

	@FXML
	private ImageView userIconOne;

	@FXML
	private Label usernameOne;

	@FXML
	private ImageView addUserOne;

	@FXML
	private Pane userProfileTwo;

	@FXML
	private Pane userProfileThree;

	@FXML
	private Pane userProfileFour;

	@FXML
	private ImageView userIconTwo;

	@FXML
	private ImageView userIconThree;

	@FXML
	private ImageView userIconFour;

	@FXML
	private Label usernameTwo;

	@FXML
	private Label usernameThree;

	@FXML
	private Label usernameFour;

	@FXML
	private ImageView addUserTwo;

	@FXML
	private ImageView addUserThree;

	@FXML
	private ImageView addUserFour;

	@FXML
	private Button userButtonTwo;

	@FXML
	private Button userButtonThree;

	@FXML
	private Button userButtonFour;

	@FXML
	private ImageView deleteUserTwo;

	@FXML
	private ImageView deleteUserThree;

	@FXML
	private ImageView deleteUserFour;

	@FXML
	private ImageView redDeleteUserTwo;

	@FXML
	private ImageView redDeleteUserThree;

	@FXML
	private ImageView redDeleteUserFour;

	@FXML
	private Label cancelDeleteTwo;

	@FXML
	private Label cancelDeleteThree;

	@FXML
	private Label cancelDeleteFour;

	/**
	 * JavaFX calls this method once the GUI elements are loaded.
	 */
	public void initialize() {
		addUserOne.setVisible(true);
		addUserOne.setDisable(false);
		userIconOne.setVisible(false);
		usernameOne.setVisible(false);
		cancelDeleteOne.setVisible(false);
		redDeleteUserOne.setDisable(true);
		redDeleteUserOne.setVisible(false);
		cancelDeleteOne.setDisable(true);
		deleteUserOne.setVisible(false);
		deleteUserOne.setDisable(true);

	}

	@FXML
	private void onCancelDeletionUserOne() {
		cancelDeleteOne.setVisible(false);
		redDeleteUserOne.setDisable(true);
		redDeleteUserOne.setVisible(false);
		deleteUserOne.setVisible(true);
	}

	@FXML
	private void confirmDeletionOne() {
		userIconOne.setVisible(false);
		usernameOne.setVisible(false);
		cancelDeleteOne.setVisible(false);
		redDeleteUserOne.setVisible(false);
		addUserOne.setVisible(true);

	}

	// User user = new User("name",2);
	// user.saveToJson();

}
