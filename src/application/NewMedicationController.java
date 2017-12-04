package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import listobjects.Medication;

public class NewMedicationController implements Initializable{
	
	@FXML private Label label;
	@FXML private Button addButton;
	@FXML private Button cancelButton;
	@FXML private TextField addNameField;
	@FXML private TextField addFrequencyField;
	@FXML private TextField addDoseField;
	@FXML private TextField addTimeField;
	boolean addButtonClicked = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		label.setVisible(false);
	}
	
    public Medication getMedication() {
        return new Medication(addNameField.getText(),addTimeField.getText(),addFrequencyField.getText(),addDoseField.getText());
    }
	
    public boolean addButtonClicked() {
    	return addButtonClicked;
    }
    
	@FXML
	private void addButtonClick() {	
		if(!addNameField.getText().equals("") && !addTimeField.getText().equals("") && !addDoseField.getText().equals("") && !addFrequencyField.getText().equals("")) {
			addButtonClicked = true;
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		}
		else {
			label.setVisible(true);
		}
	}
	
	/**
	 * exits out of popup
	 */
	@FXML
	private void cancelButtonClick() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
		addButtonClicked = false;
	}

	
}
