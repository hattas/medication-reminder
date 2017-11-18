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
			VBox mainLayout = new VBox(20);
			
			// top of screen, buttons to go to meds, home, or log
			HBox buttonHeader = new HBox(100);
			homeButton = new Button("Home");
			homeButton.setId("main-button");
			
			medsButton = new Button("Medications");
			medsButton.setId("main-button");
			
			logButton = new Button("History Log");
			logButton.setId("main-button");

			buttonHeader.getChildren().addAll(homeButton, medsButton, logButton);			
			buttonHeader.setAlignment(Pos.CENTER);			
			mainLayout.getChildren().addAll(buttonHeader);
			
			
			
			
	        Scene scene = new Scene(mainLayout, 1280, 720);
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