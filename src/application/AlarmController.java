package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlarmController {

	@FXML private Button takeButton, noButton;
    @FXML private Label alarmName, alarmDose;
    boolean takeButtonClicked = false;
    
    
    @FXML
    void takeButtonClick() {
    	takeButtonClicked = true;
    	Stage stage = (Stage) takeButton.getScene().getWindow();
		stage.close();
    }
    
    @FXML
    void noButtonClick() {
    	takeButtonClicked = false;
    	Stage stage = (Stage) noButton.getScene().getWindow();
		stage.close();	
    }
    
    public boolean takeButtonIsClicked() {
    	return takeButtonClicked;
    }
    
    public void setAlarmName(String name) {
    	alarmName.setText(name);
    }
    
    public void setAlarmDose(String dose) {
    	alarmDose.setText(dose);
    }

}
