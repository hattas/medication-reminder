package listobjects;

/**
 * Class for entries to be put in the history table.
 * Simply contains getters and setters to add history entries and populate the table.
 */

import javafx.beans.property.SimpleStringProperty;

public class HistoryEntry {

	private SimpleStringProperty date = new SimpleStringProperty("");
	private SimpleStringProperty time = new SimpleStringProperty("");
	private SimpleStringProperty name = new SimpleStringProperty("");
	private SimpleStringProperty dose = new SimpleStringProperty("");
	
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
	
	/**
	 * gets order in minutes for the history table
	 * minutes is based on years, months, days, hours, minutes, and seconds to make sure it is accurate
	 * and the history displays in chronological order, as it should.
	 * @return
	 */
	public Double getOrder() {
		String[] dateVals = date.get().split("/");
		String[] timeVals = time.get().split(":");
		int years   = Integer.parseInt(dateVals[2]) % 2000;
		int months  = Integer.parseInt(dateVals[0]);
		int days    = Integer.parseInt(dateVals[1]);
		int hours   = Integer.parseInt(timeVals[0]);
		int minutes = Integer.parseInt(timeVals[1]);
		int seconds = Integer.parseInt(timeVals[2].substring(0, 2));
		
		days = getTotalDays(days, months, years);
		
		if (timeVals[2].contains("AM")) {
			if (hours == 12)
				return seconds/60.0 + minutes + days*1440;
			return seconds/60.0 + minutes + hours * 60 + days*1440;
		}
		else {
			if (hours == 12)
				return seconds/60.0 + minutes + hours*60 + days*1440;
			return seconds/60.0 + minutes + (hours+12)*60 + days*1440;
		}
	}
	
	/**
	 * @return number of days based on the month and year
	 */
	private int getTotalDays(int days, int months, int years) {
		if (months == 1)
			return days + years*365;
		else if (months == 2)
			return 31 + days + years*365;
		else if (months == 3)
			return 59 + days + years*365;
		else if (months == 4)
			return 90 + days + years*365;
		else if (months == 5)
			return 120 + days + years*365;
		else if (months == 6)
			return 151 + days + years*365;
		else if (months == 7)
			return 181 + days + years*365;
		else if (months == 8)
			return 212 + days + years*365;
		else if (months == 9)
			return 243 + days + years*365;
		else if (months == 10)
			return 273 + days + years*365;
		else if (months == 11)
			return 304 + days + years*365;
		else
			return 334 + days + years*365;
	}
	
	
}
