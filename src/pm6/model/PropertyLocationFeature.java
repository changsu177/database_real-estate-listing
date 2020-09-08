package pm6.model;

public class PropertyLocationFeature {
	protected int id;
	protected String type;
	protected String description;
	
	public PropertyLocationFeature(String id, String type, String des) {
		this.id = Integer.parseInt(id);
		this.type = type;
		this.description = des;
	}
	
	public PropertyLocationFeature(String id) {
		this.id = Integer.parseInt(id);
	}
	
	public int getKey() {
		return this.id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String des) {
		this.description = des;
	}
}
