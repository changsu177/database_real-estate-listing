package pm6.model;

public class PropertyLocationFeatureMapping {
	protected int id;
	protected int locationKey;
	protected int featureId;
	protected int score;
	
	public PropertyLocationFeatureMapping(int locationK, int featureId, int score) {
		this.locationKey = locationK;
		this.featureId = featureId;
		this.score = score;
	}
	
	public int getKey() {
		return this.featureId;
	}
	
	public int getLocationId() {
		return this.locationKey;
	}
	
	public int getFeatureId() {
		return this.featureId;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
