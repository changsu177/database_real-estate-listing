package pm6.model;

public class PropertyLocation {
	protected int locationKey;
	protected String state;
	protected String city;
	protected String street1;
	protected String street2;
	protected String zip;
	
	public PropertyLocation(String state, String city, String st1, String st2, String zip) {
		this.state = state;
		this.city = city;
		this.street1 = st1;
		this.street2 = st2;
		this.zip = zip;
	}
	public PropertyLocation(String id) {
		this.locationKey = Integer.parseInt(id);
	}
	
	public int getKey() {
		return this.locationKey;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getStreet1() {
		return this.street1;
	}
	
	public String getStreet2() {
		return this.street2;
	}
	
	public String getZip() {
		return this.zip;
	}
	
	public void setStreet1(String st1) {
		this.street1 = st1;
	}
}
