package org.istanbuljug.json;

/**
 * Created by usta on 24.09.2016.
 */
public class Jug {

    private String name;
    private int year;
    private String website = "https://istanbul-jug.org";

    public Jug() {
    }

    public Jug(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
