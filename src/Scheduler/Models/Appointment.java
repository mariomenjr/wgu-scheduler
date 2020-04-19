package Scheduler.Models;

import Scheduler.Utils.DateTime;

import java.util.Calendar;

public class Appointment {
    private int appointmentId;
    private int customerId;
    private int userId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private DateTime start;
    private DateTime end;

    private DateTime createDate;
    private String createdBy;
    private DateTime lastUpdate;
    private String lastUpdateBy;

    public Appointment(int appointmentId, int customerId, int userId, String title, String description, String location, String contact, String type, String url, Calendar start, Calendar end, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy){
        this.appointmentId = appointmentId;

        this.customerId = customerId;
        this.userId = userId;

        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;

        this.start = new DateTime();
        this.end = new DateTime();

        this.createDate = new DateTime();
        this.createdBy = createdBy;
        this.lastUpdate = new DateTime();
        this.lastUpdateBy = lastUpdateBy;

        this.start.setTime(start.getTime());
        this.end.setTime(end.getTime());
        this.createDate.setTime(createDate.getTime());
        this.lastUpdate.setTime(lastUpdate.getTime());
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
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
