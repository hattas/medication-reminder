package listobjects;

import javafx.beans.property.SimpleStringProperty;

/**
 * when user adds a medication, instance of this class will be created
 * to store the info
 */

public class Medication {
	
	private SimpleStringProperty name  = new SimpleStringProperty("");
	private SimpleStringProperty time = new SimpleStringProperty("");	// time is in minutes. 122 = 2:02 AM
	private SimpleStringProperty frequency = new SimpleStringProperty("");
	private SimpleStringProperty dose = new SimpleStringProperty("");
	private SimpleStringProperty status = new SimpleStringProperty("");

	public Medication() {
		this("","","","","");
	}
	
	public Medication(String name, String time, String frequency, String dose, String status) {
		setName(name);
		setTime(time);
		setFrequency(frequency);
		setDose(dose);
		if (status.toLowerCase().equals("taken")) setStatus(true);
		else setStatus(false);
	}
	
	// name of medication, string, "Advil"
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	
	// converts time in mins to a displayable digital time.
	public String getTime() {
		int totalMinutes = Integer.parseInt(time.get()) % 1440;
		int hours    = (totalMinutes / 60) % 12;
		if (hours == 0) hours = 12;
		int minutes  = totalMinutes % 60;
		System.out.println("hours: " + hours + "\tminutes: " + minutes + " totalMinutes: " + totalMinutes);
		if (totalMinutes >= 720)
			return "" + hours + ":" + String.format("%02d", minutes) + " PM";
		else
			return "" + hours + ":" + String.format("%02d", minutes) + " AM";
	}
	public void setTime(String time) {
		this.time.set(time);
	}
	
	// dose of med to be taken at time, String, ex. 4
	public String getDose() {
		return dose.get();
	}
	public void setDose(String dose) {
		this.dose.set(dose);
	}
	
	// how often med is taken, String, "Weekly" "Daily" etc.
	public String getFrequency() {
		int numDays = 0;
		String day = "";
		if (frequency.get().contains("Sun")) {day = "Sunday";   numDays++;}
		if (frequency.get().contains("Mon")) {day = "Monday";   numDays++;}
		if (frequency.get().contains("Tue")) {day = "Tuesday";  numDays++;}
		if (frequency.get().contains("Wed")) {day = "Wednesday";numDays++;}
		if (frequency.get().contains("Thu")) {day = "Thursday"; numDays++;}
		if (frequency.get().contains("Fri")) {day = "Friday";   numDays++;}
		if (frequency.get().contains("Sat")) {day = "Saturday"; numDays++;}
		if (numDays == 7) return "Daily";
		else if (numDays > 1) return "Multiple";
		else return day;
	}
	public void setFrequency(String frequency) {
		this.frequency.set(frequency);
	}
	
	public String getStatus() {
		return status.get();
	}
	public void setStatus(boolean status) {
		if (status)
			this.status.set("Taken");
		else
			this.status.set("Not Taken");
	}
	
	public int getTimeInMinutes() {
		return Integer.parseInt(time.get());
	}
}
