package Scheduler.Dao;

import Scheduler.Main;
import Scheduler.Models.Appointment;
import Scheduler.Repository.BaseManager;
import Scheduler.Utils.Parser;

import java.sql.ResultSet;
import java.util.GregorianCalendar;

import static Scheduler.Utils.Parser.StringToCalendar;

public class AppointmentManager extends BaseManager<Appointment> {
    @Override
    protected String instanceToInsertQuery(Appointment instance) throws Exception {
        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        start.setTime(instance.getStart().getTime());
        end.setTime(instance.getEnd().getTime());
        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("INSERT INTO appointment VALUES(NULL,")
                .concat(Integer.toString(instance.getCustomerId()) + ",")
                .concat(Integer.toString(instance.getUserId()) + ",")
                .concat("'" + instance.getTitle() + "',")
                .concat("'" + instance.getDescription() + "',")
                .concat("'" + instance.getLocation() + "',")
                .concat("'" + instance.getContact() + "',")
                .concat("'" + instance.getType() + "',")
                .concat("'" + instance.getUrl() + "',")
                .concat("'" + Parser.CalendarToString(start) + "',")
                .concat("'" + Parser.CalendarToString(end) + "',")
                .concat("'" + Parser.CalendarToString(createdOn) + "',")
                .concat("'" + Main.getUser().getUserName() + "',")
                .concat("'" + Parser.CalendarToString(updatedOn) + "',")
                .concat("'" + Main.getUser().getUserName() + "'")
                .concat(");");
    }

    @Override
    protected String instanceToUpdateQuery(Appointment instance) throws Exception {
        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        start.setTime(instance.getStart().getTime());
        end.setTime(instance.getEnd().getTime());
        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("UPDATE appointment SET ")
                .concat("customerId = " + Integer.toString(instance.getCustomerId()) + ",")
                .concat("userId = " + Integer.toString(instance.getUserId()) + ",")
                .concat("title = '" + instance.getTitle() + "',")
                .concat("description = '" + instance.getDescription() + "',")
                .concat("location = '" + instance.getLocation() + "',")
                .concat("contact = '" + instance.getContact() + "',")
                .concat("type = '" + instance.getType() + "',")
                .concat("url = '" + instance.getUrl() + "',")
                .concat("start = '" + Parser.CalendarToString(start) + "',")
                .concat("end = '" + Parser.CalendarToString(end) + "',")
                .concat("createDate ='" + Parser.CalendarToString(createdOn) + "',")
                .concat("createdBy = '" + Main.getUser().getUserName() + "',")
                .concat("lastUpdate = '" + Parser.CalendarToString(updatedOn) + "',")
                .concat("lastUpdateBy = '" + Main.getUser().getUserName() + "'")
                .concat(" WHERE appointmentId = ")
                .concat(Integer.toString(instance.getAppointmentId()));
    }

    @Override
    protected String instanceToDeleteQuery(Appointment instance) throws Exception {
        return ""
                .concat("DELETE FROM appointment WHERE appointmentId = ")
                .concat(Integer.toString(instance.getAppointmentId()));
    }

    @Override
    protected Appointment newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new Appointment(
            resultSet.getInt("appointmentId"),
            resultSet.getInt("customerId"),
            resultSet.getInt("userId"),
            resultSet.getString("title"),
            resultSet.getString("description"),
            resultSet.getString("location"),
            resultSet.getString("contact"),
            resultSet.getString("type"),
            resultSet.getString("url"),
            StringToCalendar(resultSet.getString("start")),
            StringToCalendar(resultSet.getString("end")),
            StringToCalendar(resultSet.getString("createDate")),
            resultSet.getString("createdBy"),
            StringToCalendar(resultSet.getString("lastUpdate")),
            resultSet.getString("lastUpdateBy")
        );
    }
}
