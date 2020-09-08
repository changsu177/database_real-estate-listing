package pm6.model;

public class PropertyImage {
	protected int propertyImageKey;
	protected String image;
	protected int propertyFk;
	
	public PropertyImage(int key, String image, int fk) {
		this.propertyImageKey = key;
		this.image = image;
		this.propertyFk = fk;
	}
	
	public PropertyImage(String image) {
		this.image = image;
	}
	
	public int getKey() {
		return this.propertyImageKey;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public int getFK() {
		return this.propertyFk;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

}
