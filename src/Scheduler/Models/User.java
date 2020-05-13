package Scheduler.Models;

import Scheduler.Utils.DateTime;

import java.util.Calendar;

public class User {
    private int userId;
    private String userName;
    private String password;
    private boolean active;
    private DateTime createDate;
    private String createdBy;
    private DateTime lastUpdate;
    private String lastUpdateBy;

    public User(int userId, String userName, String password, boolean active, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.createDate = new DateTime();
        this.createdBy = createdBy;
        this.lastUpdate = new DateTime();
        this.lastUpdateBy = lastUpdateBy;

        // Casting-ish the Calendar() types to DateTime()
        this.createDate.setTime(createDate.getTime());
        this.createDate.setTime(lastUpdate.getTime());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
