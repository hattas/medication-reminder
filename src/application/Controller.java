package application;

/**
 * in fxml file:
 * fx:id refers to the object, like newButton
 * onAction refers to the method that is called
 */
	
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class Controller {

    @FXML
    private Label homeTimeLabel;
    @FXML
    private Label homeDateLabel;
    @FXML
    private Button newButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn<?, ?> colHomeTime;
    @FXML
    private TableColumn<?, ?> colHomeName;
    @FXML
    private TableColumn<?, ?> colHomeStatus;
    @FXML
    private TableColumn<?, ?> colMyDose;
    @FXML
    private TableColumn<?, ?> colMyName;
    @FXML
    private TableColumn<?, ?> colMyFrequency;
    @FXML
    private TableColumn<?, ?> colMyTime;
    @FXML
    private TableColumn<?, ?> colLogName;
    @FXML
    private TableColumn<?, ?> colLogTime;
    @FXML
    private TableColumn<?, ?> colLogDescription;

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

