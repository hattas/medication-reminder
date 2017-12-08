package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlarmController {

    @FXML private Label alarmName, alarmDose;
    private boolean takeButtonClicked = false;
    
    @FXML
    void takeButtonClick() {
    	Stage stage = (Stage) alarmName.getScene().getWindow();
		stage.close();
    	takeButtonClicked = true;
    }
    
    @FXML
    void noButtonClick() {
    	Stage stage = (Stage) alarmName.getScene().getWindow();
		stage.close();
		takeButtonClicked = false;
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
