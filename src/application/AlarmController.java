package application;

/**
 * Controller class for the alarm alert popup.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlarmController {

	@FXML private Button takeButton, noButton;
    @FXML private Label alarmName, alarmDose;
    boolean takeButtonClicked = false;
    
    /**
     * If take button is clicked, window closes and boolean is set to true.
     */
    @FXML
    void takeButtonClick() {
    	takeButtonClicked = true;
    	Stage stage = (Stage) takeButton.getScene().getWindow();
		stage.close();
    }
    
    /**
     * If cancel button is clicked, window closes the same as clicking "X"
     */
    @FXML
    void noButtonClick() {
    	takeButtonClicked = false;
    	Stage stage = (Stage) noButton.getScene().getWindow();
		stage.close();	
    }
    
    /**
     * Used by MainController to determine if the take button was clicked
     */
    public boolean takeButtonIsClicked() {
    	return takeButtonClicked;
    }
    
    /**
     * Setters for text of the alert used by MainController
     * @param name
     */
    public void setAlarmName(String name) {
    	alarmName.setText(name);
    }
    public void setAlarmDose(String dose) {
    	alarmDose.setText(dose);
    }

}
