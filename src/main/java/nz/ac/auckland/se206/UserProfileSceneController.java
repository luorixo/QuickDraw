package nz.ac.auckland.se206;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class UserProfileSceneController {

	// GUI elements for userOne
	@FXML
	private Pane userProfileOne;
	@FXML
	private ImageView userIconOne;
	@FXML
	private Label usernameOne;
	@FXML
	private ImageView addUserOne;
	@FXML
	private Button userButtonOne;
	@FXML
	private ImageView deleteUserOne;
	@FXML
	private ImageView redDeleteUserOne;
	@FXML
	private Label cancelDeleteOne;
	@FXML
	private ImageView yourNameOne;
	@FXML
	private TextField userCreateOne;
	private User userOne = User.getUser(1);

	// GUI elements for userTwo
	@FXML
	private Pane userProfileTwo;
	@FXML
	private ImageView userIconTwo;
	@FXML
	private Label usernameTwo;
	@FXML
	private ImageView addUserTwo;
	@FXML
	private Button userButtonTwo;
	@FXML
	private ImageView deleteUserTwo;
	@FXML
	private ImageView redDeleteUserTwo;
	@FXML
	private Label cancelDeleteTwo;

	// GUI elements for userThree
	@FXML
	private Pane userProfileThree;
	@FXML
	private ImageView userIconThree;
	@FXML
	private Label usernameThree;
	@FXML
	private ImageView addUserThree;
	@FXML
	private Button userButtonThree;
	@FXML
	private ImageView deleteUserThree;
	@FXML
	private ImageView redDeleteUserThree;
	@FXML
	private Label cancelDeleteThree;

	// GUI elements for userFour
	@FXML
	private Pane userProfileFour;
	@FXML
	private ImageView userIconFour;
	@FXML
	private Label usernameFour;
	@FXML
	private ImageView addUserFour;
	@FXML
	private Button userButtonFour;
	@FXML
	private ImageView deleteUserFour;
	@FXML
	private ImageView redDeleteUserFour;
	@FXML
	private Label cancelDeleteFour;
	private User userFour;

	public void initialize() {
		setUserOne();
		setUserTwo();
		setUserThree();
		setUserFour();
	}

	@FXML
	public void onUserCreateOne() {

		if (!userOne.hasBeenCreated()) {
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
			// userButtonOne.setVisible(false);
			yourNameOne.setVisible(true);
			userCreateOne.setVisible(true);
			userCreateOne.setDisable(false);
		} else {
			System.out.println("Take me to user home page");
		}
	}

	@FXML
	public void onDeleteUserOne() {
		deleteUserOne.setVisible(false);
		deleteUserOne.setDisable(true);
		redDeleteUserOne.setVisible(true);
		redDeleteUserOne.setDisable(false);
		cancelDeleteOne.setVisible(true);
		cancelDeleteOne.setDisable(false);
	}

	@FXML
	public void onCancelDeletionOne() {
		deleteUserOne.setVisible(true);
		deleteUserOne.setDisable(false);
		redDeleteUserOne.setVisible(false);
		redDeleteUserOne.setDisable(true);
		cancelDeleteOne.setVisible(false);
		cancelDeleteOne.setDisable(true);
	}

	@FXML
	public void onConfirmDeletionOne() {
		cancelDeleteOne.setVisible(false);
		cancelDeleteOne.setDisable(true);
		userIconOne.setVisible(false);
		usernameOne.setVisible(false);
		addUserOne.setVisible(true);
		// userButtonOne.setDisable(false);
		deleteUserOne.setVisible(false);
		deleteUserOne.setDisable(true);
		redDeleteUserOne.setVisible(false);
		redDeleteUserOne.setDisable(true);
		cancelDeleteOne.setVisible(false);
		cancelDeleteOne.setDisable(true);

		userOne.resetUser();

	}

	@FXML
	private void onNameEnteredOne(ActionEvent event) {

		String username = userCreateOne.getText();
		this.userOne = new User(username, 1);
		userCreateOne.clear();
		userButtonOne.setDisable(false);
		setUserOne();

	}

	@FXML
	public void onDeleteUserTwo() {
		deleteUserTwo.setVisible(false);
		deleteUserTwo.setDisable(true);
		redDeleteUserTwo.setVisible(true);
		redDeleteUserTwo.setDisable(false);
		cancelDeleteTwo.setVisible(true);
		cancelDeleteTwo.setDisable(false);
	}

	@FXML
	public void onCancelDeletionTwo() {
		deleteUserTwo.setVisible(true);
		deleteUserTwo.setDisable(false);
		redDeleteUserTwo.setVisible(false);
		redDeleteUserTwo.setDisable(true);
		cancelDeleteTwo.setVisible(false);
		cancelDeleteTwo.setDisable(true);
	}

	@FXML
	public void onConfirmDeletionTwo() {
		cancelDeleteTwo.setVisible(false);
		cancelDeleteTwo.setDisable(true);
		userIconTwo.setVisible(false);
		usernameTwo.setVisible(false);
		addUserTwo.setVisible(true);
		userButtonTwo.setDisable(false);
		deleteUserTwo.setVisible(false);
		deleteUserTwo.setDisable(true);
		redDeleteUserTwo.setVisible(false);
		redDeleteUserTwo.setDisable(true);
		cancelDeleteTwo.setVisible(false);
		cancelDeleteTwo.setDisable(true);
	}

	@FXML
	public void onDeleteUserThree() {
		deleteUserThree.setVisible(false);
		deleteUserThree.setDisable(true);
		redDeleteUserThree.setVisible(true);
		redDeleteUserThree.setDisable(false);
		cancelDeleteThree.setVisible(true);
		cancelDeleteThree.setDisable(false);
	}

	@FXML
	public void onCancelDeletionThree() {
		deleteUserThree.setVisible(true);
		deleteUserThree.setDisable(false);
		redDeleteUserThree.setVisible(false);
		redDeleteUserThree.setDisable(true);
		cancelDeleteThree.setVisible(false);
		cancelDeleteThree.setDisable(true);
	}

	@FXML
	public void onConfirmDeletionThree() {
		cancelDeleteThree.setVisible(false);
		cancelDeleteThree.setDisable(true);
		userIconThree.setVisible(false);
		usernameThree.setVisible(false);
		addUserThree.setVisible(true);
		userButtonThree.setDisable(false);
		deleteUserThree.setVisible(false);
		deleteUserThree.setDisable(true);
		redDeleteUserThree.setVisible(false);
		redDeleteUserThree.setDisable(true);
		cancelDeleteThree.setVisible(false);
		cancelDeleteThree.setDisable(true);
	}

	@FXML
	public void onDeleteUserFour() {
		deleteUserFour.setVisible(false);
		deleteUserFour.setDisable(true);
		redDeleteUserFour.setVisible(true);
		redDeleteUserFour.setDisable(false);
		cancelDeleteFour.setVisible(true);
		cancelDeleteFour.setDisable(false);
	}

	@FXML
	public void onCancelDeletionFour() {
		deleteUserFour.setVisible(true);
		deleteUserFour.setDisable(false);
		redDeleteUserFour.setVisible(false);
		redDeleteUserFour.setDisable(true);
		cancelDeleteFour.setVisible(false);
		cancelDeleteFour.setDisable(true);
	}

	@FXML
	public void onConfirmDeletionFour() {
		cancelDeleteFour.setVisible(false);
		cancelDeleteFour.setDisable(true);
		userIconFour.setVisible(false);
		usernameFour.setVisible(false);
		addUserFour.setVisible(true);
		userButtonFour.setDisable(false);
		deleteUserFour.setVisible(false);
		deleteUserFour.setDisable(true);
		redDeleteUserFour.setVisible(false);
		redDeleteUserFour.setDisable(true);
		cancelDeleteFour.setVisible(false);
		cancelDeleteFour.setDisable(true);

	}

	public void setUserOne() {

		cancelDeleteOne.setVisible(false);
		cancelDeleteOne.setDisable(true);
		redDeleteUserOne.setVisible(false);
		redDeleteUserOne.setDisable(true);
		// userButtonOne.setDisable(false);
		yourNameOne.setVisible(false);
		userCreateOne.setVisible(false);
		userCreateOne.setDisable(true);

		if (this.userOne.hasBeenCreated()) {

			userIconOne.setVisible(true);
			usernameOne.setVisible(true);
			usernameOne.setText(userOne.getName());
			addUserOne.setVisible(false);
			deleteUserOne.setVisible(true);
			deleteUserOne.setDisable(false);

		} else {

			userIconOne.setVisible(false);
			usernameOne.setVisible(false);
			addUserOne.setVisible(true);
			deleteUserOne.setVisible(false);
			deleteUserOne.setDisable(true);
		}
	}

	public void setUserTwo() {

		cancelDeleteTwo.setVisible(false);
		cancelDeleteTwo.setDisable(true);
		redDeleteUserTwo.setVisible(false);
		redDeleteUserTwo.setDisable(true);
		userButtonTwo.setDisable(false);

		if (false) {

			userIconTwo.setVisible(true);
			usernameTwo.setVisible(true);
			addUserTwo.setVisible(false);
			deleteUserTwo.setVisible(true);
			deleteUserTwo.setDisable(false);

		} else {

			userIconTwo.setVisible(false);
			usernameTwo.setVisible(false);
			addUserTwo.setVisible(true);
			deleteUserTwo.setVisible(false);
			deleteUserTwo.setDisable(true);
		}
	}

	public void setUserThree() {

		cancelDeleteThree.setVisible(false);
		cancelDeleteThree.setDisable(true);
		userButtonThree.setDisable(false);
		redDeleteUserThree.setVisible(false);
		redDeleteUserThree.setDisable(true);

		if (true) {

			userIconThree.setVisible(true);
			usernameThree.setVisible(true);
			addUserThree.setVisible(false);
			deleteUserThree.setVisible(true);
			deleteUserThree.setDisable(false);

		} else {

			userIconThree.setVisible(false);
			usernameThree.setVisible(false);
			addUserThree.setVisible(true);
			deleteUserThree.setVisible(false);
			deleteUserThree.setDisable(true);

		}
	}

	public void setUserFour() {

		cancelDeleteFour.setVisible(false);
		cancelDeleteFour.setDisable(true);
		redDeleteUserFour.setVisible(false);
		redDeleteUserFour.setDisable(true);
		userButtonFour.setDisable(false);

		if (true) {

			userIconFour.setVisible(true);
			usernameFour.setVisible(true);
			addUserFour.setVisible(false);
			deleteUserFour.setVisible(true);
			deleteUserFour.setDisable(false);

		} else {

			userIconFour.setVisible(false);
			usernameFour.setVisible(false);
			addUserFour.setVisible(true);
			deleteUserFour.setVisible(false);
			deleteUserFour.setDisable(true);

		}
	}

}
