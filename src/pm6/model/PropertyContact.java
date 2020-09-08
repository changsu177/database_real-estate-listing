package pm6.model;

public class PropertyContact {

    private int propertyContactKey;
    private String contactName;
    private String contactPhone1;
    private String contactPhone2;
    private boolean active;

    public PropertyContact(int propertyContactKey, String contactName, String contactPhone1, String contactPhone2, boolean active) {
        this.propertyContactKey = propertyContactKey;
        this.contactName = contactName;
        this.contactPhone1 = contactPhone1;
        this.contactPhone2 = contactPhone2;
        this.active = active;
    }

    public int getPropertyContactKey() {
        return propertyContactKey;
    }

    public void setPropertyContactKey(int propertyContactKey) {
        this.propertyContactKey = propertyContactKey;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone1() {
        return contactPhone1;
    }

    public void setContactPhone1(String contactPhone1) {
        this.contactPhone1 = contactPhone1;
    }

    public String getContactPhone2() {
        return contactPhone2;
    }

    public void setContactPhone2(String contactPhone2) {
        this.contactPhone2 = contactPhone2;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
