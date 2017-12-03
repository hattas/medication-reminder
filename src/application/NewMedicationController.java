package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewMedicationController {
	
	@FXML
    private Button addButton;
	@FXML
    private Button cancelButton;
	
	@FXML
	private void addButtonClick() {

	}
	
	/**
	 * exits out of popup
	 */
	@FXML
	private void cancelButtonClick() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
