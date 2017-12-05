package listobjects;

import javafx.beans.property.SimpleStringProperty;

/**
 * when user adds a medication, instance of this class will be created
 * to store the info
 */

public class Medication {
	
	private SimpleStringProperty name  = new SimpleStringProperty("");
	private SimpleStringProperty time = new SimpleStringProperty("");
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
	
	// time of day to take med, type TBD, "14:30"
	public String getTime() {
		return time.get();
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
		return frequency.get();
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
	
}
