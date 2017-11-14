package application;
	

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * javafx window is called the stage window=stage
 * content inside stage is scene
 * main starts program
 * start method has bulk of code
 * 
 */

public class Main extends Application {
	
	Stage window;
	Scene scene1, scene2;
	Button button1, button2, button3, button4;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			
			Label label1 = new Label("Home");
			button1 = new Button("My Medications");
			button1.setOnAction(e -> window.setScene(scene2));
			button3 = new Button("alert box");
			button3.setOnAction(e -> AlertBox.display("Medication Alert", "take ur meds"));
			button4 = new Button("yes or no????");
			button4.setOnAction(e -> {
				boolean result = ConfirmBox.display("yes or no?", "are you sure");
				System.out.println(result);
			});
			
			VBox layout1 = new VBox(20);
			layout1.getChildren().addAll(label1, button1, button3, button4);
			scene1 = new Scene(layout1, 400, 300);
			
			Label label2 = new Label("My Medications");
			button2 = new Button("Home");
			button2.setOnAction(e -> window.setScene(scene1));
			
			StackPane layout2 = new StackPane();
			layout2.getChildren().addAll(label2, button2);
			scene2 = new Scene(layout2, 200, 150);
			
			window.setScene(scene1);
			window.setTitle("Medication Reminder");
			window.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
