package application;


/**
 * when user adds a medication, instance of this class will be created
 * to store the info
 */

public class Medication {
	
	private String time, name, frequency;
	private int amount;

	public Medication() {
		// this can be a constructor....
	}
	
	// name of medication, string, "Advil"
	public void setName(String name) {
		this.name = name;}
	public String getName() {
		return name;}
	
	// time of day to take med, type TBD, "14:30"
	public void setTime(String time) {
		this.time = time;}
	public String getTime() {
		return time;}
	
	// amount of med to be taken at time, integer, ex. 4
	public void setAmount(int amount) {
		this.amount = amount;}
	public int getAmount() {
		return amount;}
	
	// how often med is taken, String, "Weekly" "Daily" etc.
	public void setFrequency(String frequency) {
		this.frequency = frequency;}
	public String getFrequency() {
		return frequency;}
	
}
