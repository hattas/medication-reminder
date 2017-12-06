package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import listobjects.Medication;

public class NewMedicationController implements Initializable{
	
	@FXML private Label label;
	@FXML private Button addButton;
	@FXML private Button cancelButton;
	@FXML private TextField addNameField;
	@FXML private TextField addDoseField;
	@FXML private TextField addHourField;
	@FXML private TextField addMinuteField;
	
	@FXML private RadioButton sunday, monday, tuesday, wednesday, thursday, friday, saturday;

	
    @FXML private ChoiceBox<String> amPmChoiceBox;

	boolean addButtonClicked = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		label.setVisible(false);
		amPmChoiceBox.setItems(FXCollections.observableArrayList("AM","PM"));
		amPmChoiceBox.getSelectionModel().selectFirst();
	}
	
    public Medication getMedication() {
        return new Medication(addNameField.getText(),addHourField.getText()+":"+addMinuteField.getText()+" "+amPmChoiceBox.getSelectionModel().getSelectedItem(),
        		getFrequency(),addDoseField.getText(),"Not Taken");
    }
	
    public boolean addButtonIsClicked() {
    	return addButtonClicked;
    }
    
    
	@FXML
	private void addButtonClick() {
		if(allFieldsFilledOut()) {
			int hour, minute;
			
			try { minute = Integer.parseInt(addMinuteField.getText()); } 
			catch (NumberFormatException e) { minute = -1; }
			
			try { hour = Integer.parseInt(addHourField.getText()); } 
			catch (NumberFormatException e) { hour = -1; }
			
			if (hour == -1 || minute == -1) {
				label.setText("Hour/minutes must be numbers.");
				label.setVisible(true);
			}
			else if (hour < 1 || hour > 12) {
				label.setText("Hour must be between 1 and 12.");
				label.setVisible(true);
			}
			else if (minute < 0 || minute > 59) {
				label.setText("Minute must be between 00 and 59.");
				label.setVisible(true);
			}
			else {
			addButtonClicked = true;
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
			}
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
	
	@FXML
	private void selectAllButtonClick() {
		sunday.setSelected(true);
		monday.setSelected(true);
		tuesday.setSelected(true);
		wednesday.setSelected(true);
		thursday.setSelected(true);
		friday.setSelected(true);
		saturday.setSelected(true);
	}
	
	private String getFrequency() {
		String string = new String();
		if (sunday.isSelected()) string += "sun";
		if (monday.isSelected()) string += "mon";
		if (tuesday.isSelected()) string += "tue";
		if (wednesday.isSelected()) string += "wed";
		if (thursday.isSelected()) string += "thu";
		if (friday.isSelected()) string += "fri";
		if (saturday.isSelected()) string += "sat";
		return string;
	}
	
	private boolean allFieldsFilledOut() {
		if (    !addNameField.getText().equals("") && 
				!addDoseField.getText().equals("") &&
				!addMinuteField.getText().equals("") && 
				!addHourField.getText().equals("") && (
				sunday.isSelected() ||
				monday.isSelected() ||
				tuesday.isSelected() ||
				wednesday.isSelected() ||
				thursday.isSelected() ||
				friday.isSelected() ||
				saturday.isSelected() ))
			return true;
		else return false;
	}
	
}
