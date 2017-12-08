package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
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
	@FXML private RadioButton amButton, pmButton, sunday, monday, tuesday, wednesday, thursday, friday, saturday;
    @FXML private ComboBox<String> hourComboBox, minuteComboBox;

	boolean addButtonClicked = false; // boolean value for whether the 'add' button is clicked
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ToggleGroup group=new ToggleGroup();
		amButton.setToggleGroup(group);
		pmButton.setToggleGroup(group);
		amButton.setSelected(true);
		
		label.setVisible(false);
		
		hourComboBox.setItems(FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12"));
		hourComboBox.getSelectionModel().selectFirst();
		hourComboBox.setTooltip(new Tooltip("Hour"));
		
		minuteComboBox.setItems(FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09",//TODO
																  "10","11","12","13","14","15","16","17","18","19",
																  "20","21","22","23","24","25","26","27","28","29",
																  "30","31","32","33","34","35","36","37","38","39",
																  "40","41","42","43","44","45","46","47","48","49",
																  "50","51","52","53","54","55","56","57","58","59"));
		minuteComboBox.getSelectionModel().selectFirst();
		minuteComboBox.setTooltip(new Tooltip("Minutes"));

	}
	
	/**
	 * Used by the main controller to get the medicaiton that was just added by the user, after the program is closed.
	 * @return
	 */
    public Medication getMedication() {
    	// public Medication(String name, String time, String frequency, String dose, String status) {
        return new Medication(addNameField.getText(),getTimeInMinutes(),
        		getFrequency(),addDoseField.getText(),"Not Taken");
    }
	  
    /**
     * used by the MainController only
     * @return whether the add button was clicked upon close or not.
     */
	public boolean addButtonIsClicked() {
    	return addButtonClicked;
    }
    
    /**
     * checks to make sure fields are filled out properly so user can't break the program.
     * if everything is, boolean is set to true to tell MainController that the add button was clicked upon close.
     */
	@FXML
	private void addButtonClick() {
		if (addNameField.getText().equals("") || addDoseField.getText().equals("")) {
			label.setText("Please fill out all fields.");
			label.setVisible(true);
		}
		else if ((addNameField.getText() + addDoseField.getText()).contains(";")) {
			label.setText("Text cannot contain \";\"");
			label.setVisible(true);
		}
		else if (!dayIsPicked()) {
			label.setText("Pick one or more days.");
			label.setVisible(true);
		}
		else {
			addButtonClicked = true;
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		}
	}
	
	/**
	 * exits out of popup without adding the med (same as pressing X to close)
	 */
	@FXML
	private void cancelButtonClick() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
		addButtonClicked = false;
	}
	
	/**
	 * selects all days of the week radio buttons
	 */
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
	
	/**
	 * 
	 * @return converts the days selected into a string with all days as 3 letters each
	 */
	private String getFrequency() {
		String string = new String();
		if (sunday.isSelected())    string += "Sun";
		if (monday.isSelected())    string += "Mon";
		if (tuesday.isSelected())   string += "Tue";
		if (wednesday.isSelected()) string += "Wed";
		if (thursday.isSelected())  string += "Thu";
		if (friday.isSelected())    string += "Fri";
		if (saturday.isSelected())  string += "Sat";
		return string;
	}
	
	/**
	 * 
	 * @return makes sure that at least one day is selected
	 */
	private boolean dayIsPicked() {
		if (    sunday.isSelected() ||
				monday.isSelected() ||
				tuesday.isSelected() ||
				wednesday.isSelected() ||
				thursday.isSelected() ||
				friday.isSelected() ||
				saturday.isSelected() )
			return true;
		else return false;
	}
	
	/**
	 * @return time converted to minutes 2:02 AM -> 122
	 */
    private int getTimeInMinutes() {
    	System.out.println("hour " + hourComboBox.getValue()  + " minute " + minuteComboBox.getValue());
		int hours = Integer.parseInt(hourComboBox.getValue());
		int minutes = Integer.parseInt(minuteComboBox.getValue());
		if (amButton.isSelected()) {
			if (hours == 12)
				return minutes;
			return hours * 60 + minutes;
		}
		else {
			if (hours == 12)
				return hours + minutes;
			return (hours + 12) * 60 + minutes;
		}
	}
    
	
}
