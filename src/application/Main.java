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
	Label dateLabel;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			// main vertical layout
			VBox mainLayout = new VBox(20);
			
			dateLabel = new Label("Friday, November 17");
			dateLabel.setStyle("-fx-font-size: 50pt;");
			dateLabel.setMaxWidth(Double.MAX_VALUE);
			
			HBox threePanes = new HBox(200);
			
			VBox medBox     = new VBox(20);
			Label medLabel = new Label("My Medications");
			medLabel.setId("label-header");
			medBox.getChildren().add(medLabel);
			
			VBox homeBox    = new VBox(20);
			Label homeLabel = new Label("Home");
			homeLabel.setId("label-header");
			homeBox.getChildren().add(homeLabel);
			
			VBox logBox     = new VBox(20);
			Label logLabel = new Label("History Log");
			logLabel.setId("label-header");
			logBox.getChildren().add(logLabel);

			
			// add buttons to medications pane
			Button [] buttonArray = new Button[10];
			for (int i = 0; i < buttonArray.length; i++)
				buttonArray[i] = new Button("Medication Button " + i);
			for (int i = 0; i < buttonArray.length; i++)
				medBox.getChildren().addAll(buttonArray[i]);
			
			// add test buttons to home
			Button [] buttonArray2 = new Button[10];
			for (int i = 0; i < buttonArray2.length; i++)
				buttonArray2[i] = new Button("Home Button " + i);
			for (int i = 0; i < buttonArray2.length; i++)
				homeBox.getChildren().addAll(buttonArray2[i]);
			
			// add test labels to 
			Label [] labelArray = new Label[10];
			for (int i = 0; i < labelArray.length; i++)
				labelArray[i] = new Label("November " + (16-i) + ": you didnt take pills and died");
			for (int i = 0; i < labelArray.length; i++)
				logBox.getChildren().addAll(labelArray[i]);
			
			threePanes.getChildren().addAll(medBox, homeBox, logBox);
			threePanes.setAlignment(Pos.CENTER);	
			
			
			mainLayout.getChildren().addAll(dateLabel, threePanes);
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
