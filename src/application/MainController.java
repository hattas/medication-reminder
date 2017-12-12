package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import listobjects.HistoryEntry;
import listobjects.Medication;

public class MainController implements Initializable {

    @FXML private Label homeTimeLabel, homeDateLabel;
    @FXML private Button newButton, editButton, deleteButton;   
    @FXML private TableColumn<Medication, String> colHomeTime, colHomeName, colHomeDose, colHomeStatus, colHomeOrder;
    @FXML private TableColumn<Medication, String     > colMyDose, colMyName, colMyFrequency, colMyTime;
    @FXML private TableColumn<HistoryEntry, String   > colHistoryDate, colHistoryTime, colHistoryName, colHistoryDose;
    @FXML private AnchorPane homePane; 
    @FXML private TableView<Medication> homeTable, medicationTable;
    @FXML private TableView<HistoryEntry> historyTable;
    private Date todaysDate;

    
    /**
     * initialize the controller class. runs when program starts
     */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("Initializing...");
		
		todaysDate = new Date();
		SimpleDateFormat dateSDF = new SimpleDateFormat("EEEE, MMMM d");
		homeDateLabel.setText(dateSDF.format(todaysDate));
		DigitalClock homeTimeLabel = new DigitalClock();
		homeTimeLabel.setStyle("-fx-font-size: 32pt;");
		homeTimeLabel.setMouseTransparent(true);
		AnchorPane.setTopAnchor(homeTimeLabel, 80.0);
		AnchorPane.setLeftAnchor(homeTimeLabel, 20.0);
		AnchorPane.setRightAnchor(homeTimeLabel, 20.0);
		homePane.getChildren().addAll(homeTimeLabel);
		
		setCellValueFactories();
		
		try {
			homeTable.setItems(getTodaysMedications());
			medicationTable.setItems(getMedications());
			historyTable.setItems(getHistoryEntries());
		} catch (FileNotFoundException e1) { e1.printStackTrace(); }

		// sort tables
		homeTable.getSortOrder().add(colHomeTime);
		medicationTable.getSortOrder().add(colMyName);
		historyTable.getSortOrder().add(colHistoryDate);

		startTimer();
		
		System.out.println("Done initializing.");
	}
	
	private void startTimer(){
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), a -> {
			Calendar cal = Calendar.getInstance();
			if (cal.get(Calendar.SECOND) == 0) {	// runs once per minute to check if time matches any medications.
				int calMinutes = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
				ObservableList<Medication> allMedications;
		    	allMedications = medicationTable.getItems();
				for (Medication medication : allMedications) {
					if (/*!medication.getStatus().equals("Taken") && */calMinutes == medication.getTimeInMinutes()) {
						FXMLLoader loader = new FXMLLoader();
				    	loader.setLocation(getClass().getResource("Alarm.fxml"));    
					    try {
							loader.load();
						} catch (IOException e) {
							e.printStackTrace();
						}
					    
					    AlarmController display = loader.getController();
					    System.out.println("displaying window....");	//TODO delete
					    Parent parent = loader.getRoot();
					    Stage stage = new Stage();
					    Scene scene = new Scene(parent);
					    scene.getStylesheets().add("application/Main.css");
					    stage.setTitle("Medication Alert");
					    stage.setScene(scene);
					    stage.initModality(Modality.APPLICATION_MODAL);
					    display.setAlarmDose(medication.getDose());
					    display.setAlarmName(medication.getName());
					    
					    stage.show();
					    
					    stage.setOnHidden(event -> {
						    System.out.println(display.takeButtonIsClicked());
						    if (display.takeButtonIsClicked()) {
						    	allMedications.remove(medication);
						    	medication.setStatus("Taken");
						    	System.out.println(medication.getStatus());
						    	allMedications.add(medication);
						    }
					    	
						    try {
						    rewriteMedications(allMedications);
						    addHistoryEntry(medication);
						    
					    	medicationTable.setItems(getMedications());
							medicationTable.getSortOrder().add(colMyName);
							
					    	homeTable.setItems(getTodaysMedications());
							homeTable.getSortOrder().add(colHomeTime);
						    } catch (IOException e) {
						    	e.printStackTrace();
						    }
					    });
					}
				}
			}
            System.out.println(cal.get(Calendar.SECOND));  //TODO remove this
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
     * Creates list of medications to take today on the home list.
     * Only adds medications whos frequency contains todays day ("Wed")
     * @throws FileNotFoundException 
     */
    private ObservableList<Medication> getTodaysMedications() throws FileNotFoundException {
    	ObservableList<Medication> todaysMedications = FXCollections.observableArrayList();
    	SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); //day of week ex: "Wed"
    	System.out.println(simpleDateformat.format(todaysDate));
    	for (Medication medication : getMedications())
    		if (medication.getDays().contains(simpleDateformat.format(todaysDate)))
    			todaysMedications.add(medication);
    	return todaysMedications;
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
    		medications.add(new Medication(nextLine[0],Integer.parseInt(nextLine[1]),nextLine[2],nextLine[3],nextLine[4]));
    	}
    	scanner.close();
    	return medications;
    }
    
    /***
     * 
     * @return list of history from history file
     * @throws FileNotFoundException
     */
    private ObservableList<HistoryEntry> getHistoryEntries() throws FileNotFoundException { 
    	ObservableList<HistoryEntry> historyEntry = FXCollections.observableArrayList();
    	Scanner scanner = new Scanner(new File("src\\library\\history.txt"));
    	while (scanner.hasNextLine()) {
    		String[] nextLine = scanner.nextLine().split(";");
    		historyEntry.add(new HistoryEntry(nextLine[0],nextLine[1],nextLine[2],nextLine[3]));
    	}
    	scanner.close();
    	return historyEntry;
    }
  
    /**
     * set satus of the medication to taken
	 * @throws IOException 
     */
    @FXML
    private void takeButtonClick() throws IOException {
    	
    	Medication medicationSelected = homeTable.getSelectionModel().getSelectedItem();
    	
    	if (medicationSelected != null && !medicationSelected.getStatus().equals("Taken")) {
	    	ObservableList<Medication> allMedications = homeTable.getItems();
	    	allMedications.remove(medicationSelected);
	    	medicationSelected.setStatus("Taken");
	    	allMedications.add(medicationSelected);
	    	rewriteMedications(allMedications);
	    	 
	    	homeTable.setItems(getTodaysMedications());
			homeTable.getSortOrder().add(colHomeTime);
			
			medicationTable.setItems(getTodaysMedications());
			medicationTable.getSortOrder().add(colHomeTime);
			
			addHistoryEntry(medicationSelected);
    	}
		
    }
    
	/**
     * creates pop up to add new medication!
	 * @throws IOException 
     */
    @FXML
    private void newButtonClick() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("NewMedication.fxml"));    
	    loader.load();
	    
	    NewMedicationController display = loader.getController();
	    
	    Parent parent = loader.getRoot();
	    Stage stage = new Stage();
	    Scene scene = new Scene(parent);
	    scene.getStylesheets().add("application/Main.css");
	    stage.setTitle("Add Medication");
	    stage.setScene(scene);
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.showAndWait();
	    

	    if (display.addButtonIsClicked()) {
	    	File newFile = new File("src\\library\\medications.txt");
	    	FileWriter writer = new FileWriter(newFile, true);
	        Medication med = display.getMedication(); 
	        writer.write(med.getName()+";"+med.getTimeInMinutes()+";"+med.getDays()+";"+med.getDose()+";"+med.getStatus()+"\n");
	        writer.close();
	    }
    	
    	medicationTable.setItems(getMedications());
		medicationTable.getSortOrder().add(colMyName);
		
    	homeTable.setItems(getTodaysMedications());
		homeTable.getSortOrder().add(colHomeTime);
    }
    

	/**
     * creates pop up to edit existing medication!
	 * @throws IOException 
     */
    @FXML
    private void editButtonClick() throws IOException {
    	
    	ObservableList<Medication> allMedications;
    	allMedications = medicationTable.getItems();
    	Medication editedMedication = medicationTable.getSelectionModel().getSelectedItem();
    	
    	
	    
	    if (editedMedication != null) {

	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("NewMedication.fxml"));    
		    loader.load();
		    
		    NewMedicationController display = loader.getController();
	    	
		    display.setDays(editedMedication.getDays());
		    display.setName(editedMedication.getName());
		    display.setDose(editedMedication.getDose());
		    display.setTime(editedMedication.getTimeInMinutes());
		    
		    String oldStatus = editedMedication.getStatus();
	    
		    Parent parent = loader.getRoot();
		    Stage stage = new Stage();
		    Scene scene = new Scene(parent);
		    scene.getStylesheets().add("application/Main.css");
		    stage.setTitle("Add Medication");
		    stage.setScene(scene);
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.showAndWait();
		    
		    System.out.println(oldStatus);
		    if (display.addButtonIsClicked()) {
		    	allMedications.remove(editedMedication);
		    	rewriteMedications(allMedications);
		    	File newFile = new File("src\\library\\medications.txt");
		    	FileWriter writer = new FileWriter(newFile, true);
		        Medication med = display.getMedication(); 
		        writer.write(med.getName()+";"+med.getTimeInMinutes()+";"+med.getDays()+";"+med.getDose()+";"+oldStatus+"\n");
		        writer.close();
		    }
	    	
		    medicationTable.setItems(getMedications());
			medicationTable.getSortOrder().add(colMyName);
			
	    	homeTable.setItems(getTodaysMedications());
			homeTable.getSortOrder().add(colHomeTime);
		
	    }
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
    
    /**
     * rewrites medication file based on medicatin list as input.
     * @param allMedications
     * @throws IOException
     */
    private void rewriteMedications(ObservableList<Medication> allMedications) throws IOException {
    	File newFile = new File("src\\library\\medications.txt");
    	FileWriter writer = new FileWriter(newFile, false);
    	for (Medication med : allMedications) {
    		writer.write(med.getName()+";"+med.getTimeInMinutes()+";"+med.getDays()+";"+med.getDose()+";"+med.getStatus()+"\n");
    	}
    	writer.close();
	}
    
    private void addHistoryEntry(Medication med) throws IOException {
    	
    	Date datetime = new Date();
    	SimpleDateFormat dateSDF = new SimpleDateFormat("M/d/yyyy");
    	SimpleDateFormat timeSDF = new SimpleDateFormat("H:mm:ss a");
    	
    	File newFile = new File("src\\library\\history.txt");
    	FileWriter writer = new FileWriter(newFile, true);
    	writer.write(dateSDF.format(datetime)+";"+timeSDF.format(datetime)+";"+med.getName()+";"+med.getDose()+"\n");
    	writer.close();
    	
    	historyTable.setItems(getHistoryEntries());
    	historyTable.getSortOrder().add(colHistoryDate);
    
    }    
    
    @FXML
    public void clearButtonClick() throws IOException {
    	File newFile = new File("src\\library\\history.txt");
    	FileWriter writer = new FileWriter(newFile, false);
        writer.close();
        
        historyTable.setItems(getHistoryEntries());
        historyTable.getSortOrder().add(colHistoryDate);
    }
    
    /**
     * sets up colums for all 3 tables
     */
    private void setCellValueFactories() {
    	colHomeName.setCellValueFactory(new PropertyValueFactory<Medication, String>("name"));
		colHomeTime.setCellValueFactory(new PropertyValueFactory<Medication, String>("time"));
		colHomeDose.setCellValueFactory(new PropertyValueFactory<Medication, String>("dose"));
		colHomeStatus.setCellValueFactory(new PropertyValueFactory<Medication, String>("status"));
		colHomeOrder.setCellValueFactory(new PropertyValueFactory<Medication, String>("order"));
		colHomeOrder.setSortType(TableColumn.SortType.DESCENDING);	

		
		colMyName.setCellValueFactory(new PropertyValueFactory<Medication, String>("name"));
		colMyName.setSortType(TableColumn.SortType.ASCENDING);
		colMyDose.setCellValueFactory(new PropertyValueFactory<Medication, String>("dose"));
		colMyTime.setCellValueFactory(new PropertyValueFactory<Medication, String>("time"));
		colMyFrequency.setCellValueFactory(new PropertyValueFactory<Medication, String>("frequency"));
		
		colHistoryDate.setCellValueFactory(new PropertyValueFactory<HistoryEntry, String>("date"));
		colHistoryDate.setSortType(TableColumn.SortType.DESCENDING);
		colHistoryTime.setCellValueFactory(new PropertyValueFactory<HistoryEntry, String>("time"));
		colHistoryName.setCellValueFactory(new PropertyValueFactory<HistoryEntry, String>("name"));
		colHistoryDose.setCellValueFactory(new PropertyValueFactory<HistoryEntry, String>("dose"));
    }
	
}

