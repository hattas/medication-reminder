package listobjects;

import javafx.beans.property.SimpleStringProperty;

/**
 * when user adds a medication, instance of this class will be created
 * to store the info
 */

public class Medication {
	
	private SimpleStringProperty name  = new SimpleStringProperty("");
	private SimpleStringProperty frequency = new SimpleStringProperty("");
	private SimpleStringProperty dose = new SimpleStringProperty("");
	private SimpleStringProperty status = new SimpleStringProperty("");
	private int time = -1;	// time is in minutes. 122 = 2:02 AM


	public Medication() {
		this("",0,"","","");
	}
	
	public Medication(String name, int time, String frequency, String dose, String status) {
		setName(name);
		setTime(time);
		setFrequency(frequency);
		setDose(dose);
		if (status.toLowerCase().equals("taken")) setStatus("Taken");
		else setStatus("Not Taken");
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
		int totalMinutes = time % 1440;
		int hours    = (totalMinutes / 60) % 12;
		if (hours == 0) hours = 12;
		int minutes  = totalMinutes % 60;
		if (totalMinutes >= 720)
			return "" + hours + ":" + String.format("%02d", minutes) + " PM";
		else
			return "" + hours + ":" + String.format("%02d", minutes) + " AM";
	}
	public void setTime(int time) {
		this.time = time;
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
		String multiple = "";
		String day = "";
		if (frequency.get().contains("Sun")) {day = "Sunday";    multiple += "Su"; numDays++;}
		if (frequency.get().contains("Mon")) {day = "Monday";    multiple += "Mo"; numDays++;}
		if (frequency.get().contains("Tue")) {day = "Tuesday";   multiple += "Tu"; numDays++;}
		if (frequency.get().contains("Wed")) {day = "Wednesday"; multiple += "We"; numDays++;}
		if (frequency.get().contains("Thu")) {day = "Thursday";  multiple += "Th"; numDays++;}
		if (frequency.get().contains("Fri")) {day = "Friday";    multiple += "Fr"; numDays++;}
		if (frequency.get().contains("Sat")) {day = "Saturday";  multiple += "Sa"; numDays++;}
		if (numDays == 7) return "Daily";
		else if (numDays <= 1) return day;
		else if (multiple.equals("SuSa")) return "Weekends";
		else if (multiple.equals("MoTuWeThFr")) return "Weekdays";
		else return multiple;
	}
	public void setFrequency(String frequency) {
		this.frequency.set(frequency);
	}
	
	public String getStatus() {
		return status.get();
	}
	public void setStatus(String status) {
		this.status.set(status);
	}
	
	public int getTimeInMinutes() {
		return time;
	}
	
	public String getDays() {
		return frequency.get();
	}
	
	/**
	 * Table uses this number to order the medications correctly.
	 * Must order based on minutes, not based on string time for correct order.
	 * @return time in minutes as integer
	 */
	public Integer getOrder() {
		return time;
	}
}
