package nz.ac.auckland.se206;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	@FXML
	private ImageView yourNameTwo;
	@FXML
	private TextField userCreateTwo;
	private User userTwo = User.getUser(2);

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
	@FXML
	private ImageView yourNameThree;
	@FXML
	private TextField userCreateThree;
	private User userThree = User.getUser(3);

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
	@FXML
	private ImageView yourNameFour;
	@FXML
	private TextField userCreateFour;
	private User userFour = User.getUser(4);

	public void initialize() {
		setUserOne();
		setUserTwo();
		setUserThree();
		setUserFour();
	}

	@FXML
	public void onUserCreationOrHomePage(ActionEvent event) {

		// checks to see which fxid the event is from
		Object userCreateButton = event.getSource();

		// based on the fxid, GUI elements are disabled/visible
		if (userCreateButton.equals(userButtonOne)) {

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
				yourNameOne.setVisible(true);
				userCreateOne.setVisible(true);
				userCreateOne.setDisable(false);
			} else {
				System.out.println("Take me to user 1 home page");
			}

		} else if (userCreateButton.equals(userButtonTwo)) {

			if (!userTwo.hasBeenCreated()) {
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
			} else {
				System.out.println("Take me to user 2 home page");
			}

		} else if (userCreateButton.equals(userButtonThree)) {

			if (!userThree.hasBeenCreated()) {
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
			} else {
				System.out.println("Take me to user 3 home page");
			}
		} else {

			if (!userFour.hasBeenCreated()) {
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
			} else {
				System.out.println("Take me to user 4 home page");
			}
		}
	}

	@FXML
	public void onDeleteUser(MouseEvent event) {

		// Checks to see which fxid the event is associated to
		Object deleteUserImage = event.getSource();

		// based on the fxid, these if/else statements dictate which GUI elements are
		// visible/disabled
		if (deleteUserImage.equals(deleteUserOne)) {

			deleteUserOne.setVisible(false);
			deleteUserOne.setDisable(true);
			redDeleteUserOne.setVisible(true);
			redDeleteUserOne.setDisable(false);
			cancelDeleteOne.setVisible(true);
			cancelDeleteOne.setDisable(false);

		} else if (deleteUserImage.equals(deleteUserTwo)) {

			deleteUserTwo.setVisible(false);
			deleteUserTwo.setDisable(true);
			redDeleteUserTwo.setVisible(true);
			redDeleteUserTwo.setDisable(false);
			cancelDeleteTwo.setVisible(true);
			cancelDeleteTwo.setDisable(false);

		} else if (deleteUserImage.equals(deleteUserThree)) {

			deleteUserThree.setVisible(false);
			deleteUserThree.setDisable(true);
			redDeleteUserThree.setVisible(true);
			redDeleteUserThree.setDisable(false);
			cancelDeleteThree.setVisible(true);
			cancelDeleteThree.setDisable(false);

		} else {

			deleteUserFour.setVisible(false);
			deleteUserFour.setDisable(true);
			redDeleteUserFour.setVisible(true);
			redDeleteUserFour.setDisable(false);
			cancelDeleteFour.setVisible(true);
			cancelDeleteFour.setDisable(false);
		}

	}

	@FXML
	public void onCancelDeletion(MouseEvent event) {

		Object cancelDeleteImage = event.getSource();

		if (cancelDeleteImage.equals(cancelDeleteOne)) {
			deleteUserOne.setVisible(true);
			deleteUserOne.setDisable(false);
			redDeleteUserOne.setVisible(false);
			redDeleteUserOne.setDisable(true);
			cancelDeleteOne.setVisible(false);
			cancelDeleteOne.setDisable(true);
		} else if (cancelDeleteImage.equals(cancelDeleteTwo)) {
			deleteUserTwo.setVisible(true);
			deleteUserTwo.setDisable(false);
			redDeleteUserTwo.setVisible(false);
			redDeleteUserTwo.setDisable(true);
			cancelDeleteTwo.setVisible(false);
			cancelDeleteTwo.setDisable(true);
		} else if (cancelDeleteImage.equals(cancelDeleteThree)) {
			deleteUserThree.setVisible(true);
			deleteUserThree.setDisable(false);
			redDeleteUserThree.setVisible(false);
			redDeleteUserThree.setDisable(true);
			cancelDeleteThree.setVisible(false);
			cancelDeleteThree.setDisable(true);
		} else {
			deleteUserFour.setVisible(true);
			deleteUserFour.setDisable(false);
			redDeleteUserFour.setVisible(false);
			redDeleteUserFour.setDisable(true);
			cancelDeleteFour.setVisible(false);
			cancelDeleteFour.setDisable(true);
		}
	}

	@FXML
	public void onConfirmDeletion(MouseEvent event) {

		Object confirmDeletionImage = event.getSource();

		if (confirmDeletionImage.equals(redDeleteUserOne)) {

			cancelDeleteOne.setVisible(false);
			cancelDeleteOne.setDisable(true);
			userIconOne.setVisible(false);
			usernameOne.setVisible(false);
			addUserOne.setVisible(true);
			deleteUserOne.setVisible(false);
			deleteUserOne.setDisable(true);
			redDeleteUserOne.setVisible(false);
			redDeleteUserOne.setDisable(true);

			userOne.resetUser();

		} else if (confirmDeletionImage.equals(redDeleteUserTwo)) {

			cancelDeleteTwo.setVisible(false);
			cancelDeleteTwo.setDisable(true);
			userIconTwo.setVisible(false);
			usernameTwo.setVisible(false);
			addUserTwo.setVisible(true);
			deleteUserTwo.setVisible(false);
			deleteUserTwo.setDisable(true);
			redDeleteUserTwo.setVisible(false);
			redDeleteUserTwo.setDisable(true);

			userTwo.resetUser();

		} else if (confirmDeletionImage.equals(redDeleteUserThree)) {

			cancelDeleteThree.setVisible(false);
			cancelDeleteThree.setDisable(true);
			userIconThree.setVisible(false);
			usernameThree.setVisible(false);
			addUserThree.setVisible(true);
			deleteUserThree.setVisible(false);
			deleteUserThree.setDisable(true);
			redDeleteUserThree.setVisible(false);
			redDeleteUserThree.setDisable(true);

			userThree.resetUser();
		} else {
			cancelDeleteFour.setVisible(false);
			cancelDeleteFour.setDisable(true);
			userIconFour.setVisible(false);
			usernameFour.setVisible(false);
			addUserFour.setVisible(true);
			deleteUserFour.setVisible(false);
			deleteUserFour.setDisable(true);
			redDeleteUserFour.setVisible(false);
			redDeleteUserFour.setDisable(true);

			userFour.resetUser();
		}

	}

	@FXML
	private void onNameEntered(ActionEvent event) {

		Object userCreateTextField = event.getSource();

		if (userCreateTextField.equals(userCreateOne)) {

			String username = userCreateOne.getText();
			this.userOne = new User(username, 1);
			userCreateOne.clear();
			userButtonOne.setDisable(false);
			setUserOne();
		} else if (userCreateTextField.equals(userCreateTwo)) {

			String username = userCreateTwo.getText();
			this.userTwo = new User(username, 2);
			userCreateTwo.clear();
			userButtonTwo.setDisable(false);
			setUserTwo();

		} else if (userCreateTextField.equals(userCreateThree)) {

			String username = userCreateThree.getText();
			this.userThree = new User(username, 3);
			userCreateThree.clear();
			userButtonThree.setDisable(false);
			setUserThree();
		} else {

			String username = userCreateFour.getText();
			this.userFour = new User(username, 4);
			userCreateFour.clear();
			userButtonFour.setDisable(false);
			setUserFour();
		}

	}

	public void setUserOne() {

		cancelDeleteOne.setVisible(false);
		cancelDeleteOne.setDisable(true);
		redDeleteUserOne.setVisible(false);
		redDeleteUserOne.setDisable(true);
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
		yourNameTwo.setVisible(false);
		userCreateTwo.setVisible(false);
		userCreateTwo.setDisable(true);

		if (this.userTwo.hasBeenCreated()) {

			userIconTwo.setVisible(true);
			usernameTwo.setVisible(true);
			usernameTwo.setText(userTwo.getName());
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
		redDeleteUserThree.setVisible(false);
		redDeleteUserThree.setDisable(true);
		yourNameThree.setVisible(false);
		userCreateThree.setVisible(false);
		userCreateThree.setDisable(true);

		if (this.userThree.hasBeenCreated()) {

			userIconThree.setVisible(true);
			usernameThree.setVisible(true);
			usernameThree.setText(userThree.getName());
			addUserThree.setVisible(false);
			deleteUserThree.setVisible(true);
			deleteUserThree.setDisable(false);

		} else {

			userIconThree.setVisible(false);
			usernameThree.setVisible(false);
			usernameFour.setText(userFour.getName());
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
		yourNameFour.setVisible(false);
		userCreateFour.setVisible(false);
		userCreateFour.setDisable(true);

		if (this.userFour.hasBeenCreated()) {

			userIconFour.setVisible(true);
			usernameFour.setVisible(true);
			usernameFour.setText(userFour.getName());
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
