package application;

/**
 * Extension of label class that keeps track of time.
 * Displays time like a digital clock and updates every second.
 * Used on home screen.
 */

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class DigitalClock extends Label {
	
	public DigitalClock() {
		bindToTime();
	}
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");

	// the digital clock updates once a second.
	private void bindToTime() {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0), event -> setText(LocalTime.now().format(formatter))),
				new KeyFrame(Duration.seconds(1)));
		
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}