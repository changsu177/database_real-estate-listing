package pm6.model;

import java.util.Date;

public class Property {

    private int propertyKey;
    private String propertyName;
    private String propertyDescription;
    private int propertySize;
    private Date addTime;
    private Date updateTime;
    private int propertyContactKey;
    private int propertyLocationKey;
    private int defaultPrice;

    public Property(int propertyKey, String propertyName, String propertyDescription, int propertySize, Date addTime, Date updateTime, int propertyContactKey, int propertyLocationKey, int defaultPrice) {
        this.propertyKey = propertyKey;
        this.propertyName = propertyName;
        this.propertyDescription = propertyDescription;
        this.propertySize = propertySize;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.propertyContactKey = propertyContactKey;
        this.propertyLocationKey = propertyLocationKey;
        this.defaultPrice = defaultPrice;
    }
    
    public Property(int propertyKey) {
        this.propertyKey = propertyKey;
    }

    public int getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(int propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public int getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(int propertySize) {
        this.propertySize = propertySize;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getPropertyContactKey() {
        return propertyContactKey;
    }

    public void setPropertyContactKey(int propertyContactKey) {
        this.propertyContactKey = propertyContactKey;
    }

    public int getPropertyLocationKey() {
        return propertyLocationKey;
    }

    public void setPropertyLocationKey(int propertyLocationKey) {
        this.propertyLocationKey = propertyLocationKey;
    }

    public int getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
