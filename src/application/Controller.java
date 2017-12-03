package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * in fxml file:
 * fx:id refers to the object, like newButton
 * onAction refers to the method that is called
 */
	
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML private Label homeTimeLabel;
    @FXML private Label homeDateLabel;
    
    @FXML private Button newButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    
    @FXML private TableColumn<TodayMedication, String> colHomeTime;
    @FXML private TableColumn<TodayMedication, String> colHomeName;
    @FXML private TableColumn<TodayMedication, String> colHomeStatus;
    @FXML private TableColumn<Medication, String> colMyDose;
    @FXML private TableColumn<Medication, String> colMyName;
    @FXML private TableColumn<Medication, String> colMyFrequency;
    @FXML private TableColumn<Medication, String> colMyTime;
    @FXML private TableColumn<Medication, String> colLogName;
    @FXML private TableColumn<Medication, String> colLogTime;
    @FXML private TableColumn<Medication, String> colLogDescription;
    
    @FXML private TableView<TodayMedication> homeTable;
    @FXML private TableView<Medication> medicationTable;

    
    
    // initialize controller class
    @Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("Initializing...");
		
		colHomeTime.setCellValueFactory(new PropertyValueFactory<TodayMedication, String>("time"));
		colHomeTime.setSortType(TableColumn.SortType.ASCENDING);
		colHomeName.setCellValueFactory(new PropertyValueFactory<TodayMedication, String>("name"));
		colHomeStatus.setCellValueFactory(new PropertyValueFactory<TodayMedication, String>("status"));
		
		colMyName.setCellValueFactory(new PropertyValueFactory<Medication, String>("name"));
		colMyName.setSortType(TableColumn.SortType.ASCENDING);
		colMyDose.setCellValueFactory(new PropertyValueFactory<Medication, String>("dose"));
		colMyTime.setCellValueFactory(new PropertyValueFactory<Medication, String>("time"));
		colMyFrequency.setCellValueFactory(new PropertyValueFactory<Medication, String>("frequency"));
		
		try {
			homeTable.setItems(getTodaysMedications());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			medicationTable.setItems(getMedications());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// sort tables
		homeTable.getSortOrder().add(colHomeTime);
		medicationTable.getSortOrder().add(colMyName);

		// make columns editable
		medicationTable.setEditable(true);
		colMyName.setCellFactory(TextFieldTableCell.forTableColumn());
		colMyDose.setCellFactory(TextFieldTableCell.forTableColumn());
		colMyTime.setCellFactory(TextFieldTableCell.forTableColumn());
		colMyFrequency.setCellFactory(TextFieldTableCell.forTableColumn());
		
		System.out.println("Done initializing.");
	}
    
    /** 
     * @return List of medications for My Medications page from file.
     * @throws FileNotFoundException 
     */
    private ObservableList<Medication> getMedications() throws FileNotFoundException {
    	ObservableList<Medication> medications = FXCollections.observableArrayList();
    	Scanner scanner = new Scanner(new File("src\\library\\medications.txt"));
    	while (scanner.hasNextLine()) {
    		String[] nextLine = scanner.nextLine().split(";");
    		medications.add(new Medication(nextLine[0],nextLine[1],nextLine[2],nextLine[3]));
    	}
    	scanner.close();
    	return medications;
    }
    
    /**
     * Creates list of medications to take today on the home list.
     * @throws FileNotFoundException 
     */
    private ObservableList<TodayMedication> getTodaysMedications() throws FileNotFoundException {
    	ObservableList<TodayMedication> todaysMedications = FXCollections.observableArrayList();
    	for (Medication medication : getMedications()) {
    		todaysMedications.add(new TodayMedication(medication, "Taken"));
    	}
    	return todaysMedications;
    }

    /**
     * Event for editing cells
     * @throws IOException 
     */
    public void changeMyNameCellEvent(CellEditEvent editedCell) throws IOException {
    	ObservableList<Medication> allMedications = medicationTable.getItems();
    	Medication medicationSelected = medicationTable.getSelectionModel().getSelectedItem();
    	allMedications.remove(medicationSelected);
    	medicationSelected.setName(editedCell.getNewValue().toString());
    	allMedications.add(medicationSelected);
    	rewriteMedications(allMedications);
    		
    	medicationTable.setItems(getMedications());
    	medicationTable.getSortOrder().add(colMyName);
    	homeTable.setItems(getTodaysMedications());
		homeTable.getSortOrder().add(colHomeTime);	
    }
    
    public void changeMyTimeCellEvent(CellEditEvent editedCell) throws IOException {
    	ObservableList<Medication> allMedications = medicationTable.getItems();
    	Medication medicationSelected = medicationTable.getSelectionModel().getSelectedItem();
    	allMedications.remove(medicationSelected);
    	medicationSelected.setTime(editedCell.getNewValue().toString());
    	allMedications.add(medicationSelected);
    	rewriteMedications(allMedications);
    	
    	medicationTable.setItems(getMedications());
    	medicationTable.getSortOrder().add(colMyName);
    	homeTable.setItems(getTodaysMedications());
		homeTable.getSortOrder().add(colHomeTime);	
    }
    
    public void changeMyDoseCellEvent(CellEditEvent editedCell) throws IOException {
    	ObservableList<Medication> allMedications = medicationTable.getItems();
    	Medication medicationSelected = medicationTable.getSelectionModel().getSelectedItem();
    	allMedications.remove(medicationSelected);
    	medicationSelected.setDose(editedCell.getNewValue().toString());
    	allMedications.add(medicationSelected);
    	rewriteMedications(allMedications);
    	   	
    	medicationTable.setItems(getMedications());
    	medicationTable.getSortOrder().add(colMyName);
    	homeTable.setItems(getTodaysMedications());
		homeTable.getSortOrder().add(colHomeTime);	
    }
    
    public void changeMyFrequencyCellEvent(CellEditEvent editedCell) throws IOException {
    	ObservableList<Medication> allMedications = medicationTable.getItems();
    	Medication medicationSelected = medicationTable.getSelectionModel().getSelectedItem();
    	allMedications.remove(medicationSelected);
    	medicationSelected.setFrequency(editedCell.getNewValue().toString());
    	allMedications.add(medicationSelected);
    	rewriteMedications(allMedications);
    	  	
    	medicationTable.setItems(getMedications());
    	medicationTable.getSortOrder().add(colMyName);
    	homeTable.setItems(getTodaysMedications());
		homeTable.getSortOrder().add(colHomeTime);	
    }
    
    /**
     * allows medications to be deleted.
     * @throws IOException
     */
    @FXML
    public void deleteButtonClick() throws IOException {
    	ObservableList<Medication> allMedications;
    	allMedications = medicationTable.getItems();
    	Medication removedMedication = medicationTable.getSelectionModel().getSelectedItem();
    	allMedications.remove(removedMedication);
    	rewriteMedications(allMedications);
    	
    	// update home tab tableview
		homeTable.setItems(getTodaysMedications());
		homeTable.getSortOrder().add(colHomeTime);
    }
    
    private void rewriteMedications(ObservableList<Medication> allMedications) throws IOException {
		// TODO Auto-generated method stub
    	File newFile = new File("src\\library\\medications.txt");
    	FileWriter writer = new FileWriter(newFile, false);
    	for (Medication medication : allMedications) {
    		writer.write(medication.getName()+";"+medication.getTime()+";"+medication.getFrequency()+";"+medication.getDose()+"\n");
    	}
    	writer.close();
	}

	/**
     * creates pop up to add new medication!
	 * @throws IOException 
     */
    @FXML
    private void newButtonClick() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("NewMedication.fxml"));    
	    Scene scene = new Scene(root);
	    scene.getStylesheets().add("application/Main.css");
	    Stage stage = new Stage();
	    stage.setTitle("Add Medication");
	    stage.setScene(scene);
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.showAndWait();
    }
    
   


}

