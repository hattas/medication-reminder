package application;
	

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * javafx window is called the stage window=stage
 * content inside stage is scene
 * main starts program
 * start method has bulk of code
 * 
 * 
 * choicebox = dropdown menu
 */

public class Main extends Application {
	
	Scene scene;
	Button medsButton, homeButton, logButton;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			// main vertical layout
			TabPane tabPane = new TabPane();
			Tab tab = new Tab("Main");
			BorderPane borderPane = new BorderPane();
			//Add some in borderPane
			tab.setContent(borderPane);
			

	        Scene scene = new Scene(tabPane, 1280, 720);
	        scene.getStylesheets().add("application/application.css");
			primaryStage.setScene(scene);
			primaryStage.setTitle("Medication Reminder");
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}