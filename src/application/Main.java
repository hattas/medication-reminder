package application;
	

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * javafx window is called the stage window=stage
 * content inside stage is scene
 * main starts program
 * start method has bulk of code
 * stages have scenes, scenes have layout panes (containers)
 * https://www.tutorialspoint.com/javafx/javafx_application.htm
 * 
 * https://docs.oracle.com/javafx/2/best_practices/jfxpub-best_practices.htm
 * 
 * choicebox = dropdown menu
 */

public class Main extends Application {
	
	final static int WIDTH = 800;
	final static int HEIGHT = 500;

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Creating a line object 
		    Line line1 = new Line(); 
		         
		    // vertical divider line
		    line1.setStartX(200); 
		    line1.setStartY(0); 
		    line1.setEndX(200); 
		    line1.setEndY(HEIGHT);
		    
		    //top divider line
		    Line line2 = new Line(); 
		    line2.setStartX(200); 
		    line2.setStartY(HEIGHT / 10); 
		    line2.setEndX(WIDTH); 
		    line2.setEndY(HEIGHT / 10); 
			
		    // medication image
		    FileInputStream inputstream = new FileInputStream("med_bottles.png"); 
		    Image image = new Image(inputstream); 
		    ImageView imageView = new ImageView();
		    imageView.setImage(image);
	        imageView.setFitWidth(160);
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
	        imageView.setX(20); 
	        imageView.setY(100);
	        
	        // application title text.
	        Text text = new Text();      
	        text.setText("Medication\nReminder"); 
	        text.setX(20); 
	        text.setY(320);
	        text.setFont(new Font(30));
	        
	        // side pannel buttons and vertical box contatiner
	        Button settingsButton = new Button("settings");
	        Button helpButton = new Button("help");
	        HBox panelButtons = new HBox(20, settingsButton, helpButton);
	        panelButtons.setAlignment(Pos.CENTER);
	        VBox logo = new VBox(imageView, text, panelButtons);
	        logo.setMinWidth(200);
	        logo.setAlignment(Pos.CENTER);
	        
	        	        
	        // button panel 
	        Button homeButton = new Button("Home");
	        Button medButton = new Button("My Medications");
	        Button addButton = new Button("Add Medication");
	        Button logButton = new Button("History Log");
	        HBox buttonRow = new HBox(50, homeButton, medButton, addButton, logButton);
	        buttonRow.setAlignment(Pos.CENTER);
	        Pane bufferPane = new Pane();
	        bufferPane.setPrefHeight(10);
	        
	        VBox rightSide = new VBox(20, bufferPane, buttonRow, line2);
	        
	        HBox root = new HBox(logo, line1, rightSide);	// main layout container.
			
	        Scene scene = new Scene(root, WIDTH, HEIGHT);
	        
	        //scene.getStylesheets().add("application/application.css");
	        
			primaryStage.setScene(scene);
			
			primaryStage.setTitle("Medication Reminder");
			
			primaryStage.centerOnScreen();
			
			primaryStage.setMinWidth(WIDTH);
			primaryStage.setMinHeight(HEIGHT);
			primaryStage.setMaxWidth(WIDTH);
			primaryStage.setMaxHeight(HEIGHT);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void changePanel() {
		
	}
}