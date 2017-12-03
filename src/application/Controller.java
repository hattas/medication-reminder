package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * in fxml file:
 * fx:id refers to the object, like newButton
 * onAction refers to the method that is called
 */
	
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable {

    @FXML private Label homeTimeLabel;
    @FXML private Label homeDateLabel;
    
    @FXML private Button newButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    
    @FXML private TableColumn<?, ?> colHomeTime;
    @FXML private TableColumn<?, ?> colHomeName;
    @FXML private TableColumn<?, ?> colHomeStatus;
    @FXML private TableColumn<Medication, String> colMyDose;
    @FXML private TableColumn<Medication, String> colMyName;
    @FXML private TableColumn<Medication, String> colMyFrequency;
    @FXML private TableColumn<Medication, String> colMyTime;
    @FXML private TableColumn<?, ?> colLogName;
    @FXML private TableColumn<?, ?> colLogTime;
    @FXML private TableColumn<?, ?> colLogDescription;
    
    @FXML private TableView<Medication> medicationTable;
    
    
    // initialize controller class
    @Override
	public void initialize(URL url, ResourceBundle resources) {
		System.out.println("Initializing...");
		colMyName.setCellValueFactory(new PropertyValueFactory<Medication, String>("name"));
		colMyDose.setCellValueFactory(new PropertyValueFactory<Medication, String>("dose"));
		colMyTime.setCellValueFactory(new PropertyValueFactory<Medication, String>("time"));
		colMyFrequency.setCellValueFactory(new PropertyValueFactory<Medication, String>("frequency"));
		
		medicationTable.setItems(getMedications());
		
		System.out.println("Done initializing.");
	}
    
    private ObservableList<Medication> getMedications() {
    	ObservableList<Medication> medications = FXCollections.observableArrayList();
    	medications.add(new Medication("Advil", "12:00", "Daily", "2"));
    	medications.add(new Medication("Tylenol", "3:00", "Daily", "1"));
    	medications.add(new Medication("Ibuprofen", "18:00", "Weekly", "3"));
    	return medications;
    }

    
    @FXML
    private void newButtonClick() {
    	System.out.println("new");
    	newButton.setText("clicked -_-");
    }

    @FXML
    private void editButtonClick() {
    	System.out.println("edit");
    	editButton.setText("uhhhh -.-");
    }

    @FXML
    private void deleteButtonClick() {
    	System.out.println("delete");
    	deleteButton.setText("i am doing something `_`");
    }

}

