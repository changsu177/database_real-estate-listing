package pm6.model;

public class FeaturePropertyMapping {

    private int id;
    private int propertyId;
    private int featureId;

    public FeaturePropertyMapping(int id, int propertyId, int featureId) {
        this.id = id;
        this.propertyId = propertyId;
        this.featureId = featureId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }
}
