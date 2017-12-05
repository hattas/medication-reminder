package listobjects;

import javafx.beans.property.SimpleStringProperty;

public class HistoryEntry {

	private SimpleStringProperty date   = new SimpleStringProperty("");
	private SimpleStringProperty time   = new SimpleStringProperty("");
	private SimpleStringProperty name   = new SimpleStringProperty("");
	private SimpleStringProperty dose   = new SimpleStringProperty("");
	
	public HistoryEntry() {
		this("", "", "", "");
	}
	
	public HistoryEntry(String date, String time, String name, String dose) {
		setDate(date);
		setTime(time);
		setName(name);
		setDose(dose);
	}
	
	public String getDate() { 
		return date.get();
	}
	public void setDate(String date) {
		this.date.set(date);
	}
	
	public String getTime() {
		return time.get();
	}
	public void setTime(String time) {
		this.time.set(time);
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getDose() {
		return dose.get();
	}
	public void setDose(String dose) {
		this.dose.set(dose);
	}
	
	
}
