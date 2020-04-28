package Scheduler.Models;

import Scheduler.Utils.DateTime;

import java.util.Calendar;

public class City {
    private int cityId;
    private String city;
    private int countryId;
    private DateTime createDate;
    private String createdBy;
    private DateTime lastUpdate;
    private String lastUpdateBy;

    public City(int cityId, String city, int countryId, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy) {
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.createDate = new DateTime();
        this.createdBy = createdBy;
        this.lastUpdate = new DateTime();
        this.lastUpdateBy = lastUpdateBy;

        this.createDate.setTime(createDate.getTime());
        this.lastUpdate.setTime(lastUpdate.getTime());
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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
