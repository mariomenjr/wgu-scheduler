package Scheduler.Models;

import Scheduler.Utils.DateTime;

import java.util.Calendar;

public class Country {
    private int countryId;
    private String country;
    private DateTime createDate;
    private String createdBy;
    private DateTime lastUpdate;
    private String lastUpdateBy;

    public Country(int countryId, String country, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy) {
        this.countryId = countryId;
        this.country = country;
        this.createDate = new DateTime();
        this.createdBy = createdBy;
        this.lastUpdate = new DateTime();
        this.lastUpdateBy = lastUpdateBy;

        this.createDate.setTime(createDate.getTime());
        this.lastUpdate.setTime(lastUpdate.getTime());
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(DateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}
