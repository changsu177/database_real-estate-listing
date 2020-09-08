package pm6.model;

public class Feature {

    public enum FeatureType {
        BEDROOM,
        BATHROOM
    }

    private int featureKey;
    private FeatureType featureType;
    private int count;
    private String description;

    public Feature(int featureKey, FeatureType featureType, int count, String description) {
        this.featureKey = featureKey;
        this.featureType = featureType;
        this.count = count;
        this.description = description;
    }

    public int getFeatureKey() {
        return featureKey;
    }

    public void setFeatureKey(int featureKey) {
        this.featureKey = featureKey;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
