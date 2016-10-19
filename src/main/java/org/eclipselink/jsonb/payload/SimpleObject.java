package org.eclipselink.jsonb.payload;

/**
 * Simplest possible object.
 *
 * @author Roman Grigoriadi
 */
public class SimpleObject {

    private String stringValue;

    private int intValue;

    private String website = "https://istanbul-jug.org";

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
