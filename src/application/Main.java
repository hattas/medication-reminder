package application;
	

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
			tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

			
			// Home Tab
			Tab homeTab = new Tab("Home");
			Label dateLabel = new Label("Saturday, November 18");
			Label med1 = new Label("Advil");
			GridPane.setConstraints(med1, 0, 0);
			Label time1 = new Label("11:00");
			GridPane.setConstraints(time1, 1, 0);
			Label med2 = new Label("Tylenol");
			GridPane.setConstraints(med2, 0, 1);
			Label time2 = new Label("1:00");
			GridPane.setConstraints(time2, 1, 1);
			Label med3 = new Label("Ibuprofen");
			GridPane.setConstraints(med3, 0, 2);
			Label time3 = new Label("4:00");
			GridPane.setConstraints(time3, 1, 2);
			
			GridPane grid = new GridPane();
	        grid.setPadding(new Insets(10, 10, 10, 10));
	        grid.setVgap(8);
	        grid.setHgap(10);
	        
	        grid.getChildren().addAll(med1, time1, med2, time2, med3, time3);
			
			VBox homeVbox = new VBox();
			homeVbox.getChildren().addAll(dateLabel, grid);
			homeTab.setContent(homeVbox);

			
			// Medications Tab
			Tab medTab = new Tab("Medications");
			// 
			//medTab.setContent();

			
			// History Log Tab
			Tab logTab = new Tab("History Log");
		
			//logTab.setContent();

			
			
			
			// add the three tabs to the tab pane.
			tabPane.getTabs().addAll(homeTab, medTab, logTab);

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